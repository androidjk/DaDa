package com.dada.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User_menu extends BaseActivity implements View.OnClickListener{

    private TextView tvSelected;
    private Button btnAll;
    private Button btnFan;
    private Button btnCancle;
    private ListView lv;
    private List<HashMap<String,Object>> list;
    private CheckBoxAdapter cbAdapter;
    private List<String> listStr = new ArrayList<String>();
    private String user_PhoneNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);
        InitViews();
    }

    private void InitViews() {
        tvSelected = (TextView) findViewById(R.id.tvselected);
        btnAll = (Button) findViewById(R.id.btn_all);
        btnFan = (Button) findViewById(R.id.btn_fan);
        btnCancle = (Button) findViewById(R.id.btn_cancle);
        lv = (ListView) findViewById(R.id.lv);
        list = new ArrayList<HashMap<String,Object>>();
        for(int i=0;i<10;i++){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("name", "G"+i);
            map.put("boolean", false);//初始化为未选
            list.add(map);
        }//初始化数据

        cbAdapter = new CheckBoxAdapter(this,list);
        lv.setAdapter(cbAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                CheckBoxAdapter.ViewCache viewCache = (CheckBoxAdapter.ViewCache) view.getTag();
                viewCache.cb.toggle();
                list.get(position).put("boolean", viewCache.cb.isChecked());

                cbAdapter.notifyDataSetChanged();

                if(viewCache.cb.isChecked()){//被选中状态
                    listStr.add(list.get(position).get("name").toString());
                }else//从选中状态转化为未选中
                {
                    listStr.remove(list.get(position).get("name").toString());
                }

                tvSelected.setText("已选择了:"+listStr.size()+"项");
            }
        });

        btnAll.setOnClickListener(this);
        btnCancle.setOnClickListener(this);
        btnFan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_all://全选,修改值为true
                for(int i=0;i<list.size() && !(Boolean)list.get(i).get("boolean") ;i++){
                    list.get(i).put("boolean", true);
                    listStr.add(list.get(i).get("name").toString());
                }
                cbAdapter.notifyDataSetChanged();

                tvSelected.setText("已选择了:"+listStr.size()+"项");
                break;
            case R.id.btn_fan:
                for(int i=0;i<list.size();i++){
                    if((Boolean)list.get(i).get("boolean")){//为true
                        list.get(i).put("boolean", false);
                        listStr.remove(list.get(i).get("name").toString());
                    }
                    else
                    {
                        list.get(i).put("boolean", true);
                        listStr.add(list.get(i).get("name").toString());
                    }
                }
                cbAdapter.notifyDataSetChanged();

                tvSelected.setText("已选择了:"+listStr.size()+"项");
                break;
            case R.id.btn_cancle://取消已选
                for(int i=0;i<list.size();i++){
                    if((Boolean)list.get(i).get("boolean")){
                        list.get(i).put("boolean", false);
                        listStr.remove(list.get(i).get("name").toString());
                    }
                }
                cbAdapter.notifyDataSetChanged();

                tvSelected.setText("已选择了:"+listStr.size()+"项");
                break;
        }
    }
}
