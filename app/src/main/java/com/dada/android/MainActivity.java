package com.dada.android;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class MainActivity extends BaseActivity {
    private DrawerLayout mDrawLayout;
    public static MainActivity mainActivity;
    private boolean quit=false;
    Button button, button_dingdan;
    Toolbar toolbar;
    NavigationView navView;
    /**
     * 首页
     * @param savedInstanceState
     */
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "daca4eedf8c0f12bbdb2143fbb64605b");
        mainActivity = this;
        initViews();
        setSupportActionBar(toolbar);

        /**
         * 订单按钮
         */
        button_dingdan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,User_menu.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, doMenu.class);
                startActivity(intent);
            }
        });
        /**
         * 滑动菜单项点击事项
         */
        navView.setCheckedItem(R.id.nav_call);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_id:
                        BmobUser.logOut();   //清除缓存用户对象
                        BmobUser currentUser = BmobUser.getCurrentUser();  //清除缓存用户对象
                        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent);
                        ActivityCollector.finishAll();
                        break;
                }
//               mDrawLayout.closeDrawers();
                return true;
            }
        });
        /**
         * 增加导航
         */
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }
    public void initViews(){
        button = (Button) findViewById(R.id.test);
        button_dingdan = (Button) findViewById(R.id.button_dingdan);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        navView=(NavigationView)findViewById(R.id.nav_view);
        mDrawLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }
    public void onBackPressed(){
        if (quit == false) {    //询问退出程序
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            new Timer(true).schedule(new TimerTask() {   //启动定时任务
                @Override
                public void run() {
                    quit = false;  //重置退出标识
                }
            }, 2000);        //2秒后运行run()方法
            quit = true;
        } else {          //确认退出程序
            super.onBackPressed();
            ActivityCollector.finishAll();
        }
    }
}




