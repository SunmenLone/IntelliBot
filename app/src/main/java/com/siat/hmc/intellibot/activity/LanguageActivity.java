package com.siat.hmc.intellibot.activity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.siat.hmc.intellibot.R;
import com.siat.hmc.intellibot.adapter.LanguagePagerAdapter;

public class LanguageActivity extends AppCompatActivity implements View.OnClickListener, PopupMenu.OnMenuItemClickListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private LanguagePagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private TextView lang;

    static private SoundPool soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(R.string.language);

        LinearLayout l = (LinearLayout) findViewById(R.id.spinner);
        l.setOnClickListener(this);

        lang = (TextView) findViewById(R.id.lang_text);

        ImageButton pre = (ImageButton) findViewById(R.id.previous);
        pre.setOnClickListener(this);
        ImageButton nex = (ImageButton) findViewById(R.id.next);
        nex.setOnClickListener(this);

        String name = "numbers";
        try {
            Intent intent = getIntent();
            if (intent != null && intent.getStringExtra("name") != null) {
                name = intent.getStringExtra("name");
                lang.setText(name);
            }
        } catch (Exception e) {

        }

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new LanguagePagerAdapter(getSupportFragmentManager());
        mSectionsPagerAdapter.setDataSourse(name);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Intent intent = new Intent(this, LanguageActivity.class);
        switch (item.getItemId()) {
            case R.id.numbers:
                intent.putExtra("name", "numbers");
                break;
            case R.id.animals:
                intent.putExtra("name", "animals");
                break;
            case R.id.fruits:
                intent.putExtra("name", "fruits");
                break;
            case R.id.eats:
                intent.putExtra("name", "eats");
                break;
                default:
                    break;
        }
        startActivity(intent);
        finish();
        return false;
    }

    @Override
    public void onClick(View v) {
        int cur, next;
        switch(v.getId()){
            case R.id.spinner:
                PopupMenu popup = new PopupMenu(this, v);//第二个参数是绑定的那个view
                //获取菜单填充器
                MenuInflater inflater = popup.getMenuInflater();
                //填充菜单
                inflater.inflate(R.menu.menu_language, popup.getMenu());
                //绑定菜单项的点击事件
                popup.setOnMenuItemClickListener(this);
                //显示(这一行代码不要忘记了)
                popup.show();
                break;
            case R.id.previous:
                cur = mViewPager.getCurrentItem();
                if (cur - 1 < 0) {
                    next = mSectionsPagerAdapter.getCount() - 1;
                } else {
                    next = cur - 1;
                }
                mViewPager.setCurrentItem(next);
                break;
            case R.id.next:
                cur = mViewPager.getCurrentItem();
                next =  (cur + 1) % mSectionsPagerAdapter.getCount();
                mViewPager.setCurrentItem(next);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static SoundPool getSoundPool(){
        return soundPool;
    }
}
