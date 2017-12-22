package com.dada.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dada.android.db.Bmobuser;
import com.dada.android.db.Person;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.CountListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends BaseActivity {
    private EditText account;
    private EditText password;
    private Button denglu;
    private Button zhuce;
    Bmobuser user=new Bmobuser();
    /**
     * 用户登录
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "daca4eedf8c0f12bbdb2143fbb64605b");
        setContentView(R.layout.activity_login);
        BmobUser bmobUser = BmobUser.getCurrentUser();
        account = (EditText) findViewById(R.id.et_account);
        password = (EditText) findViewById(R.id.et_password);
        denglu = (Button) findViewById(R.id.button_denglu);
        zhuce = (Button) findViewById(R.id.button_zhuce);
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, Zhuce.class);
                startActivity(intent);
            }
        });
        if(bmobUser == null){
            // 允许用户使用应用

            denglu.setOnClickListener(new View.OnClickListener() {
                @Override
                //登录
                public void onClick(View view) {

                    user.setUsername(account.getText().toString());
                    user.setPassword(password.getText().toString());
                    user.login(new SaveListener<Person>() {
                        @Override
                        public void done(Person person, BmobException e) {
                            if (e==null){
                                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                BmobUser use=BmobUser.getCurrentUser();
                                if (use!=null){
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }else {
                                    Toast.makeText(LoginActivity.this, "不存在该用户，请注册", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Log.d("登录失败",e.getMessage());
                            }
                        }
                    });
                }
            });
        }
        else{
            //缓存用户对象为空时， 可打开用户注册界面…
            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

}}
