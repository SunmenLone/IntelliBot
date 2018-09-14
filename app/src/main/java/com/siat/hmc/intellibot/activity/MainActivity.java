package com.siat.hmc.intellibot.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.siat.hmc.intellibot.R;
import com.siat.hmc.intellibot.iface.FuncSelectInterface;
import com.siat.hmc.intellibot.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FuncSelectInterface {

    class Func {

        private String funcName;
        private int funcImg;

        public Func(String funcName, int funcImg) {
            this.funcName = funcName;
            this.funcImg = funcImg;
        }

        public String getFuncName(){
            return funcName;
        }

        public int getFuncImg(){
            return funcImg;
        }
    }

    private static List<Func> flist =  null;
    private static List<View> list = null;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private FuncPagerAdapter mFuncPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.welcome);

        mViewPager = (ViewPager) findViewById(R.id.container);

        if (flist == null) {
            flist = new ArrayList<>();
            flist.add(new Func("Walking Control", R.drawable.guide_walking));
            flist.add(new Func("Voice Reading", R.drawable.voice_reading));
            flist.add(new Func("Language Learning", R.drawable.language_learning));
            flist.add(new Func("Setting", R.drawable.setting));
        }

        if (list == null) {
            list = new ArrayList<>();
            for(int i = 0; i < flist.size(); i++){
                View v = LayoutInflater.from(mViewPager.getContext()).inflate(R.layout.func_main, null);
                ((ImageView)(v.findViewById(R.id.func_imageview))).setImageResource(flist.get(i).getFuncImg());
                ((TextView)(v.findViewById(R.id.func_textview))).setText(flist.get(i).getFuncName());
                if (i == 0) {
                    ((RelativeLayout)(v.findViewById(R.id.func_layout))).setBackgroundResource(R.drawable.func_background_selected);
                }
                list.add(v);
            }
        }


        mFuncPagerAdapter = new FuncPagerAdapter(this, list);
        mFuncPagerAdapter.setFuncSelectInterface(this);

        mViewPager.setAdapter(mFuncPagerAdapter);
        mViewPager.addOnPageChangeListener(mFuncPagerAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setPageMargin(DensityUtil.dp2px(this, 48));

        RelativeLayout root = (RelativeLayout) findViewById(R.id.main_content);
        root.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mViewPager.dispatchTouchEvent(event);
            }
        });

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public void selectFunc(int position) {

        Intent intent;
        switch(position) {
            case 0:
                intent = new Intent(MainActivity.this, ControlActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(MainActivity.this, ReadingActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(MainActivity.this, LanguageActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_friends) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class FuncPagerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener, ViewPager.PageTransformer {

        private Context mContext = null;
        private List<View> mFuncList = null;
        private float mLastOffset;
        private FuncSelectInterface fs = null;

        public void setFuncSelectInterface(FuncSelectInterface fs) {
            this.fs = fs;
        }

        public FuncPagerAdapter(Context context, List<View> list) {
            mContext = context;
            mFuncList = list;
        }

        @Override
        public int getCount() {
            return mFuncList == null ? 0 : mFuncList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mFuncList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = mFuncList.get(position);
            view.findViewById(R.id.func_layout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fs.selectFunc(position);
                }
            });
            container.addView(view);
            return view;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            int realCurrentPosition;
            int nextPosition;
            float realOffset;
            boolean goingLeft = mLastOffset > positionOffset;

            // If we're going backwards, onPageScrolled receives the last position
            // instead of the current one
            if (goingLeft) {
                realCurrentPosition = position + 1;
                nextPosition = position;
                realOffset = 1 - positionOffset;
            } else {
                nextPosition = position + 1;
                realCurrentPosition = position;
                realOffset = positionOffset;
            }

            // Avoid crash on overscroll
            if (nextPosition > getCount() - 1
                    || realCurrentPosition > getCount() - 1) {
                return;
            }

            View currentCard = mFuncList.get(realCurrentPosition);

            // This might be null if a fragment is being used
            // and the views weren't created yet
            if (currentCard != null) {
                currentCard.setScaleX((float) (1 + 0.2 * (1 - realOffset)));
                currentCard.setScaleY((float) (1 + 0.2 * (1 - realOffset)));
                if (realOffset > 0.3) {
                    currentCard.findViewById(R.id.func_layout).setBackgroundResource(R.drawable.func_background);
                }
            }

            View nextCard = mFuncList.get(nextPosition);

            // We might be scrolling fast enough so that the next (or previous) card
            // was already destroyed or a fragment might not have been created yet
            if (nextCard != null) {
                nextCard.setScaleX((float) (1 + 0.2 * (realOffset)));
                nextCard.setScaleY((float) (1 + 0.2 * (realOffset)));
                if (realOffset > 0.7) {
                    nextCard.findViewById(R.id.func_layout).setBackgroundResource(R.drawable.func_background_selected);
                }
            }

            mLastOffset = positionOffset;

        }


        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

        @Override
        public void transformPage(View page, float position) {



        }
    }

}
