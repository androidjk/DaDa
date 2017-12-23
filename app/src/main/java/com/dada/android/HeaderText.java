package com.dada.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import cn.bmob.v3.BmobUser;

public class HeaderText extends BaseActivity {
    TextView userName,userEmail;
     String name,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header);
        userName=(TextView)findViewById(R.id.username);
        userEmail=(TextView)findViewById(R.id.mail);
        name=(String) BmobUser.getObjectByKey("username");
        email=(String)BmobUser.getObjectByKey("email");
        Log.d("改Text值2",name);
        Log.d("改Text值2",email);
        userName.setText(name);
        userEmail.setText(email+"");
        Intent intent=new Intent(HeaderText.this,MainActivity.class);
        startActivity(intent);
    }
}
