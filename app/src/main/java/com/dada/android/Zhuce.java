package com.dada.android;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.dada.android.db.Bmobuser;
import com.dada.android.db.Person;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import static android.widget.RadioGroup.*;

public class Zhuce extends ActivityCollector {
    private Person person;
    private EditText et_name, et_phone, et_mail, et_getPassword1, et_getPassword2;
    private RadioGroup sex;
    private RadioButton man, woman;
    private Button button_send, button_shangchuan, button_chongchuan;
    private ImageView image_head;
    String sex1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "daca4eedf8c0f12bbdb2143fbb64605b");
        setContentView(R.layout.activity_zhuce);
        initNews();
        person = new Person();
        //选择性别
        sex.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.rb_man:
                        sex1 = "男";
                        break;
                    case R.id.rb_woman:
                        sex1 = "女";
                        break;
                }
            }
        });
        //注册
        button_send.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Bmobuser user = new Bmobuser();
                if (et_getPassword1.getText().toString().equals(et_getPassword2.getText().toString())) {
                    user.setPassword(et_getPassword1.getText().toString());
                } else Toast.makeText(Zhuce.this, "密码前后输入不一致", Toast.LENGTH_SHORT).show();
                user.setUsername(et_name.getText().toString());
                user.setEmail(et_mail.getText().toString());
                user.setSex(sex1);
                user.setMobilePhoneNumber(et_phone.getText().toString());
                user.signUp(new SaveListener<Person>() {
                    @Override
                    public void done(Person person, BmobException e) {
                        if (e == null) {
                            Toast.makeText(Zhuce.this, "注册成功" + person.toString(), Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d("注册异常", e.getMessage());
                        }
                    }
                });
            }
        });
//        button_send.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (et_getPassword1.getText().toString().equals(et_getPassword2.getText().toString())) {
//                    if (et_name.getText().toString() != null && et_id.getText().toString() != null && et_phone
//                            .getText().toString() != null && sex1 != null) {
//                        person.setName(et_name.getText().toString());
//                        person.setId(et_id.getText().toString());
//                        person.setPhone(et_phone.getText().toString());
//                        person.setSex(sex1);
//                        person.setPassword(et_getPassword1.getText().toString());
//                        person.save(new SaveListener<String>() {
//                                        @Override
//                                        public void done(String s, BmobException e) {
//                                            if (e == null) {
//                                                Toast.makeText(Zhuce.this, "创建数据成功", Toast.LENGTH_SHORT).show();
//                                            } else {
//                                                Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
//                                                Toast.makeText(Zhuce.this, "该身份证号已经被注册", Toast.LENGTH_SHORT).show();
//                                            }
//                                        }
//                                    }
//                        );
//                    } else {
//                        Toast.makeText(Zhuce.this, "有重要信息未填，请输入重试", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(Zhuce.this, "前后密码不一致。请重新输入", Toast.LENGTH_SHORT).show();
//                    }
//            Intent intent=new Intent(Zhuce.this,MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
    }

    public void initNews() {
        button_send = (Button) findViewById(R.id.button_send);
        sex = (RadioGroup) findViewById(R.id.rg_sex);
        man = (RadioButton) findViewById(R.id.rb_man);
        woman = (RadioButton) findViewById(R.id.rb_woman);
        et_mail = (EditText) findViewById(R.id.et_mail);
        et_name = (EditText) findViewById(R.id.et_getName);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_getPassword1 = (EditText) findViewById(R.id.et_getPassword1);
        et_getPassword2 = (EditText) findViewById(R.id.et_getPassword2);
    }
}

