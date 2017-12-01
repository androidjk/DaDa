package com.dada.android;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import cn.bmob.v3.Bmob;

public class MainActivity extends BaseActivity {
    private DrawerLayout mDrawLayout;
    public static MainActivity mainActivity;
    MakeMenu makeMenu = new MakeMenu();
    LinearLayout linear;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "daca4eedf8c0f12bbdb2143fbb64605b");
        setContentView(R.layout.activity_main);
        mainActivity = this;
        button = (Button) findViewById(R.id.test);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MakeMenu.class);
                startActivity(intent);
            }
        });

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
}



