package com.dada.android;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dada.android.db.Bmobuser;
import com.dada.android.db.Cark;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.BmobUser;

import static com.dada.android.doMenu.list_menu;
import static com.dada.android.doMenu.list_name;

public class User_menu extends BaseActivity{

    private TextView tvSelected,bianji;
    private Button btnAll;
    private Button btnFan;
    private Button btnCancle;
    private ListView lv;
    private List<String> listStr = new ArrayList<String>();
    private String user_PhoneNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);
        InitViews();
        ArrayAdapter adapter=new ArrayAdapter(User_menu.this,android.R.layout.simple_list_item_1,doMenu.list_menu);
        ListView listView=(ListView)findViewById(R.id.lv);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                list_menu.get(i);
                AlertDialog.Builder dialog=new AlertDialog.Builder(view.getContext());
                dialog.setTitle("删除订单");
                dialog.setMessage("确认删除订单吗？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        list_menu.remove(i);
                        Log.d("ceshi22","aaaaa");
                    }
                });
                dialog.setNegativeButton("再想想", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
            }
        });
        listView.setAdapter(adapter);
        String username = (String) BmobUser.getObjectByKey("username");
        String pid = (String) BmobUser.getObjectByKey("mobilePhoneNumber");
    }

    private void InitViews() {
        tvSelected = (TextView) findViewById(R.id.tvselected);
        lv = (ListView) findViewById(R.id.lv);
        bianji=(TextView)findViewById(R.id.bt_header_right) ;
    }





}
