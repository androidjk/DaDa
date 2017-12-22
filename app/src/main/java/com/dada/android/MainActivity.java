package com.dada.android;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class MainActivity extends BaseActivity {
    private DrawerLayout mDrawLayout;
    public static MainActivity mainActivity;
    doMenu makeMenu = new doMenu();
    LinearLayout linear;
    Button button, button_dingdan;

    /**
     * 首页
     *
     * @param savedInstanceState
     */
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "daca4eedf8c0f12bbdb2143fbb64605b");
        mainActivity = this;
        button = (Button) findViewById(R.id.test);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /**
         * 订单按钮
         */
        button_dingdan = (Button) findViewById(R.id.button_dingdan);
        button_dingdan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity.this,ShoppingAdapter.class);
//                startActivity(intent);
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
         * 增加导航
         */
        mDrawLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
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


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        mDrawLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
//        }
//    }
//
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.toolbar, menu);
//        return true;
//    }
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.back_up:
//                Toast.makeText(this, "You Clicked Backup", Toast.LENGTH_SHORT).show();
//                break;
//            case android.R.id.home:
//                mDrawLayout.openDrawer(GravityCompat.START);
//                break;
//            default:
//        }
//        return true;
}




