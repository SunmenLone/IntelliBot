package com.siat.hmc.intellibot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.support.annotation.ColorRes;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.siat.hmc.intellibot.MyApplication;
import com.siat.hmc.intellibot.util.DensityUtil;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private Thread th;


    private SurfaceHolder sfh;


    private Canvas canvas;


    private Paint paint;


    private boolean flag;


    //固定摇杆背景圆形的X,Y坐标以及半径

    private static Context ctx = MyApplication.getInstance();


    private int RockerCircleX = DensityUtil.dp2px(ctx, 88f);


    private int RockerCircleY = DensityUtil.dp2px(ctx, 88f);


    private int RockerCircleR = DensityUtil.dp2px(ctx, 88f);


    //摇杆的X,Y坐标以及摇杆的半径


    private float SmallRockerCircleX = DensityUtil.dp2px(ctx, 88f);


    private float SmallRockerCircleY = DensityUtil.dp2px(ctx, 88f);


    private float SmallRockerCircleR = DensityUtil.dp2px(ctx, 44f);;


    public MySurfaceView(Context context) {


        super(context);


        this.setKeepScreenOn(true);


        sfh = this.getHolder();


        sfh.addCallback(this);


        paint = new Paint();


        paint.setAntiAlias(true);


        setFocusable(true);


        setFocusableInTouchMode(true);

    }

    public MySurfaceView(Context context, AttributeSet attrs) {

        super(context, attrs);

        this.setKeepScreenOn(true);


        sfh = this.getHolder();


        sfh.addCallback(this);


        paint = new Paint();


        paint.setAntiAlias(true);


        setFocusable(true);


        setFocusableInTouchMode(true);

        setZOrderOnTop(true);

        getHolder().setFormat(PixelFormat.TRANSLUCENT);

    }


    public void surfaceCreated(SurfaceHolder holder) {


        th = new Thread(this);


        flag = true;


        th.start();


    }


    /***

     * 得到两点之间的弧度

     */


    public double getRad(float px1, float py1, float px2, float py2) {


        //得到两点X的距离


        float x = px2 - px1;


        //得到两点Y的距离


        float y = py1 - py2;


        //算出斜边长


        float xie = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));


        //得到这个角度的余弦值（通过三角函数中的定理 ：邻边/斜边=角度余弦值）


        float cosAngle = x / xie;


        //通过反余弦定理获取到其角度的弧度


        float rad = (float) Math.acos(cosAngle);


        //注意：当触屏的位置Y坐标小于摇杆的Y坐标我们要取反值-0~-180


        if (py2 < py1) {


            rad = -rad;


        }


        return rad;


    }


    @Override


    public boolean onTouchEvent(MotionEvent event) {


        if (event.getAction() == MotionEvent.ACTION_DOWN ||


                event.getAction() == MotionEvent.ACTION_MOVE) {


            // 当触屏区域不在活动范围内


            if (Math.sqrt(Math.pow((RockerCircleX - (int) event.getX()), 2)


                    + Math.pow((RockerCircleY - (int) event.getY()), 2)) >= SmallRockerCircleR) {


                //得到摇杆与触屏点所形成的角度


                double tempRad = getRad(RockerCircleX, RockerCircleY, event.getX(), event.getY());


                //保证内部小圆运动的长度限制


                getXY(RockerCircleX, RockerCircleY, SmallRockerCircleR, tempRad);


            } else {//如果小球中心点小于活动区域则随着用户触屏点移动即可


                SmallRockerCircleX = (int) event.getX();


                SmallRockerCircleY = (int) event.getY();


            }


        } else if (event.getAction() == MotionEvent.ACTION_UP) {


            //当释放按键时摇杆要恢复摇杆的位置为初始位置


            SmallRockerCircleX = RockerCircleX;


            SmallRockerCircleY = RockerCircleY;


        }


        return true;


    }


    /**

     *

     * @param R

     *            圆周运动的旋转点

     * @param centerX

     *            旋转点X

     * @param centerY

     *            旋转点Y

     * @param rad

     *            旋转的弧度

     */


    public void getXY(float centerX, float centerY, float R, double rad) {


        //获取圆周运动的X坐标


        SmallRockerCircleX = (float) (R * Math.cos(rad)) + centerX;


        //获取圆周运动的Y坐标


        SmallRockerCircleY = (float) (R * Math.sin(rad)) + centerY;


    }


    public void draw() {


        try {


            canvas = sfh.lockCanvas();

            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

            paint.setShadowLayer(3f, 1, 1, Color.BLACK);

            //设置透明度

            paint.setColor(0x961C384C);

            //绘制摇杆背景

            canvas.drawCircle(RockerCircleX, RockerCircleY, RockerCircleR, paint);

            paint.setColor(0xFF226F98);

            //绘制摇杆

            canvas.drawCircle(SmallRockerCircleX, SmallRockerCircleY, SmallRockerCircleR, paint);


        } catch (Exception e) {


            // TODO: handle exception


        } finally {


            try {

                if (canvas != null)

                    sfh.unlockCanvasAndPost(canvas);

            } catch (Exception e2) {

            }

        }

    }


    public void run() {


        // TODO Auto-generated method stub


        while (flag) {

            draw();

            try {

                Thread.sleep(50);

            } catch (Exception ex) {

            }

        }

    }


    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {


    }


    public void surfaceDestroyed(SurfaceHolder holder) {


        flag = false;

    }

}
