package com.dada.android;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.dada.android.db.DingDan;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class Manager extends BaseActivity {
    Button chaxun;
    static EditText editText;
    static List list_menu = new ArrayList();
    static List list_id = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        initViews();
        chaxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BmobQuery<DingDan> query = new BmobQuery();
                query.addWhereEqualTo("pid", Manager.editText.getText().toString());
                query.findObjects(new FindListener<DingDan>() {
                    @Override
                    public void done(List<DingDan> list, BmobException e) {
                        if (e == null) {
                            Log.d("查询", "查询成功");
                            for (DingDan a : list) {
                                list_menu.add(a.getCname());
                                list_id.add(a.getObjectId());
                            }
                            ArrayAdapter adapter = new ArrayAdapter(Manager.this, android.R.layout.simple_list_item_1, list_menu);
                            ListView listView = (ListView) findViewById(R.id.lv_chaxun);
                            adapter.notifyDataSetChanged();
                            listView.setAdapter(adapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                    new AlertDialog.Builder(view.getContext())//以用户id为主键删除订单，弹窗
                                            .setTitle("提示")
                                            .setMessage("要删除该订单吗")
                                            .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    DingDan dingDan = new DingDan();
                                                    for (Object a : list_id) {
                                                        dingDan.setObjectId(a.toString());
                                                        dingDan.delete(new UpdateListener() {
                                                            @Override
                                                            public void done(BmobException e) {

                                                                if (e == null) {
                                                                    Log.i("bmob", "成功");
                                                                    list_menu.clear();
                                                                } else {
                                                                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                                                                }

                                                            }
                                                        });
                                                    }

                                                }
                                            })
                                            .setNegativeButton("否", null)
                                            .show();
                                }
                            });
                        }

                    }
                });

            }

        });
    }

    private void initViews() {
        chaxun = (Button) findViewById(R.id.chaxun);
        editText = (EditText) findViewById(R.id.et_id);
    }
}



