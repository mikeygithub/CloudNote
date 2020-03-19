package com.example.xz.weiji.AppActivity;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xz.weiji.Fragment.MyFragment;
import com.example.xz.weiji.Fragment.NoteListFragment;
import com.example.xz.weiji.R;
import com.example.xz.weiji.Utils.Utils;

public class NoteActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener, View.OnClickListener {
    private Toolbar toolbar;
    private ViewPager view_pager;
    private NoteListFragment noteListFragment1;
    private MyFragment myFragment;
    private PagerAdapter pagerAdapter;
    private TabLayout tab_layout;
    private TextView date;
    private DrawerLayout drawer_layout;
    private LinearLayout ll_user;
    private LinearLayout ll_classify;
    private LinearLayout ll_money;
    private long exitTime = 0;
    AlertDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage_withleftmenu);
        initView();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次  退出云笔记", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                onBackPressed();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initView() {
        // head_layout = (LinearLayout) findViewById(R.id.head_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            toolbar.getLayoutParams().height = Utils.getAppBarHeight(this);
            toolbar.setPadding(toolbar.getPaddingLeft(),
                    Utils.getStatusBarHeight(this),
                    toolbar.getPaddingRight(),
                    toolbar.getPaddingBottom());
        }
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //设置标题栏及其导航菜单,菜单点击事件
        toolbar.setTitle("云笔记");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置左边侧滑栏
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer_layout, toolbar,
                R.string.action_ok, R.string.action_ok);
        toggle.syncState();
        drawer_layout.setDrawerListener(toggle);

        drawer_layout.setScrimColor(Color.TRANSPARENT);


    }


    private void initFragment() {
        if (noteListFragment1 == null) {
            noteListFragment1 = new NoteListFragment();
        }
        if (myFragment == null) {
            myFragment = new MyFragment();
        }
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        view_pager.setAdapter(pagerAdapter);

        view_pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_layout));
        tab_layout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(view_pager));

        tab_layout.setupWithViewPager(view_pager);
        tab_layout.getTabAt(0).setIcon(R.drawable.icon1_selector);
        tab_layout.getTabAt(0).setText("笔 记");
//        tab_layout.getTabAt(1).setIcon(R.drawable.icon2_seclector);
//        tab_layout.getTabAt(1).setText("账 本");
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(NoteActivity.this, "等待开发者开发...", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
    }


    //viewpager适配器
    public class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return noteListFragment1;
            } else if (position == 1) {
                return myFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void notifyTongzhilan(String s) {
        if (TextUtils.isEmpty(s)) {
            Toast.makeText(this, "输入不能为空", Toast.LENGTH_SHORT).show();
        } else {
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            int icon = R.mipmap.ic_event_note_blue_300_24dp;
            long time = System.currentTimeMillis();

            Intent notificationIntent = new Intent(this, NoteActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
            Notification notification = new Notification.Builder(this).setContentTitle("云笔记")
                    .setContentText(s).setSmallIcon(icon).setWhen(time).setContentIntent(contentIntent)
                    .build();
            notification.flags = Notification.FLAG_NO_CLEAR;
            manager.notify(0, notification);

            dialog.dismiss();


        }


    }


}
