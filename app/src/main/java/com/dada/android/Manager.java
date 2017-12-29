package com.dada.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dada.android.db.DingDan;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class Manager extends AppCompatActivity {
    static Button chaxun;
    static EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        initViews();
        setListener();
    }

    private void initViews() {
        chaxun=(Button)findViewById(R.id.chaxun);
        editText=(EditText)findViewById(R.id.et_id);
    }
    public void setListener(){
        ButtonListener buttonListener=new ButtonListener();
        chaxun.setOnClickListener(buttonListener);
    }
}
class ButtonListener implements View.OnClickListener{

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.chaxun:
                BmobQuery<DingDan>query=new BmobQuery();
                query.addWhereEqualTo("objectid",Manager.editText.getText());
                query.findObjects(new FindListener<DingDan>() {
                    @Override
                    public void done(List<DingDan> list, BmobException e) {
                        
                    }
                });
                break;
        }
    }
}
