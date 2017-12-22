package com.dada.android.db;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.dada.android.BaseActivity;
import com.dada.android.LoginActivity;
import com.dada.android.MainActivity;
import com.dada.android.R;

import cn.bmob.v3.BmobUser;

public class Main extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.d("1564564564", "1351165311");
        Intent intent = new Intent(Main.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}

