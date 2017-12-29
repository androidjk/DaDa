package com.dada.android;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dada.android.db.Bmobuser;
import com.dada.android.db.Cark;
import com.dada.android.db.DingDan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

import static android.widget.Toast.LENGTH_SHORT;

public class doMenu extends BaseActivity {
    private Button show, find, add, delete, back;
    private SeekBar seekBar;
    private String type;
    private RadioGroup radioGroup;
    private TextView textName,textSex,textPid;
    static private ImageView iv_show;
    static String carType;
    static int price = 0;
    static String id;
    static Cark cark = new Cark();
    static Map list_price = new HashMap();
    static List list_name = new ArrayList();
    static List list_menu = new ArrayList();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        initViews();
        setListener();
        textName.setText((String) BmobUser.getObjectByKey("username"));
        textPid.setText((String)BmobUser.getObjectByKey("mobilePhoneNumber"));
        textSex.setText((String)BmobUser.getObjectByKey("sex"));
    }


    /**
     * 下载图片
     */
    public void downloadPic(final View view) {
        BmobQuery<Cark> query = new BmobQuery<>();
        query.addWhereEqualTo("type", carType);
        query.findObjects(new FindListener<Cark>() {
            @Override

            public void done(List<Cark> list, BmobException e) {
                String pName = "keche", place = "http://bmob-cdn-15323.b0.upaiyun.com/2017/12/07/ff2a007a405ade6b80074cfc3184faea.png";
                if (e == null) {
                    if (carType == "keche") {
                        if (price <= 500) {
                            cark.setName("马自达");
                            pName = "mazida.png";
                            place = "http://bmob-cdn-15323.b0.upaiyun.com/2017/12/07/ff2a007a405ade6b80074cfc3184faea.png";
                        } else if (price > 500) {
                            cark.setName("奥迪A4");
                            pName = "aodia4";
                            place = "http://bmob-cdn-15323.b0.upaiyun.com/2017/12/05/3d424f3140bde715809cc98804ba7110.png";
                        }
                    } else if (carType == "gongjiao") {
                        cark.setName("金龙");
                        pName = "jinlong.png";
                        place = "http://bmob-cdn-15323.b0.upaiyun.com/2017/12/07/bdd0b68240bc074f80c312fe26d16d35.png";
                    } else if (carType == "pika") {
                        cark.setName("皮卡雪");
                        pName = "pikaxue.png";
                        place = "http://bmob-cdn-15323.b0.upaiyun.com/2017/12/07/4ec4f4cb40631fe880b7d4a472246c87.png";
                    } else if (carType == "huoche") {
                        if (price <= 500) {
                            cark.setName("五菱宏光");
                            pName = "wuling.png";
                            place = "http://bmob-cdn-15323.b0.upaiyun.com/2017/12/07/f1a13a65408205a780359c7b787c7e98.png";
                        } else if (price > 500) {
                            cark.setName("依维柯");
                            pName = "yiweike.png";
                            place = "http://bmob-cdn-15323.b0.upaiyun.com/2017/12/07/43f65e4d40adb3848047e18dcabf5d92.png";
                        }
                    }
                }
                final BmobFile bmobfile = new BmobFile(pName, "", place);
                bmobfile.download(new DownloadFileListener() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null) {
                            Glide.with(view.getContext())
                                    .load(s)
                                    .into(iv_show);
                            Log.d("图片下载","下载成功，保存路径");
//                            Toast.makeText(view.getContext(), "下载成功，保存路径", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d("图片下载","下载成功");
//                            Toast.makeText(view.getContext(), "下载成功", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onProgress(Integer integer, long l) {
                        Log.i("bmob", "下载进度：" + integer + "," + l);
                    }
                });
            }
        });
    }

    /**
     * 增加价格
     */
    public void sumPrice(final View view) {
        BmobQuery<Cark> query = new BmobQuery<Cark>();
        if (cark.getName() != null) {
            query.addWhereEqualTo("name", cark.getName());
            query.findObjects(new FindListener<Cark>() {
                @Override
                public void done(List<Cark> list, BmobException e) {
                    for (Cark cark : list) {
                        list_name.add(cark.getName());
                        list_price.put(cark.getName(), cark.getPrice());
                        price += cark.getPrice();
                    }
                    Log.d("价格：", String.valueOf(price));

                    Log.d("price集合中元素个数：", String.valueOf(list_price.size()));
                    Log.d("list集合中元素个数：", String.valueOf(list.size()));
                }
            });
        } else {
            Log.d("价格：", "为空值");
        }

    }

    public void delePrice(final View view) {
        if (cark.getName() != null) {
            if (list_name != null) {
                for (Object a : list_name) {
                    if (cark.getName().equals(a.toString())) {
                        price -= Integer.valueOf(list_price.get(cark.getName()).toString());
                        Log.d("删除车辆成功", String.valueOf(price));
                        list_name.remove(a);
                        break;
                    } else {
                        Log.d("是否添加该车辆", "否");
                    }
                }
            } else {
                Log.d("订单", "null");
            }
        } else {
            Log.d("车名：", "null");
        }
    }

    public void initViews() {
        add = (Button) findViewById(R.id.bt_add);
        delete = (Button) findViewById(R.id.bt_delete);
        show = (Button) findViewById(R.id.button_show);
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        find = (Button) findViewById(R.id.button_find);
        iv_show = (ImageView) findViewById(R.id.iv_show);
        radioGroup = (RadioGroup) findViewById(R.id.rg_cheku);
        back = (Button) findViewById(R.id.btn_back);
        textName=(TextView)findViewById(R.id.tv_getName);
        textSex=(TextView)findViewById(R.id.tv_getSex);
        textPid=(TextView)findViewById(R.id.tv_getId);
    }

    /**
     * 设置监听
     */
    public void setListener() {
        ButtonListener buttonListener = new ButtonListener();
        show.setOnClickListener(buttonListener);
        find.setOnClickListener(buttonListener);
        add.setOnClickListener(buttonListener);
        delete.setOnClickListener(buttonListener);
        back.setOnClickListener(buttonListener);
        SeekBarListener seekBarListener = new SeekBarListener();
        seekBar.setOnSeekBarChangeListener(seekBarListener);
        RadioButtonListener radioButtonListeer = new RadioButtonListener();
        radioGroup.setOnCheckedChangeListener(radioButtonListeer);
    }

    public void alertShow() {

        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("请先添加订单")
                .setPositiveButton("是", null)
                .setNegativeButton("不添加了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent in = new Intent(doMenu.this, MainActivity.class);
                        startActivity(in);
                    }
                })
                .show();

    }

    /**
     * 按钮监听器
     */
    class ButtonListener implements View.OnClickListener {
        doMenu makeMenu = new doMenu();

        @Override
        public void onClick(final View view) {
            switch (view.getId()) {
                case R.id.button_show://确认框
                    if (cark.getName() != null) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                        dialog.setTitle("确认订单");
                        dialog.setMessage("确认提交订单吗？");
                        dialog.setCancelable(false);
                        dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                list_menu = list_name;
                                for (Object a : list_name) {
                                    DingDan dingDan = new DingDan();
                                    dingDan.setPid((String) Bmobuser.getObjectByKey("mobilePhoneNumber"));
                                    dingDan.setCname(a.toString());
                                    BmobQuery<Cark> query = new BmobQuery<Cark>();
                                    query.addWhereEqualTo("name", a.toString());
                                    query.findObjects(new FindListener<Cark>() {
                                        @Override
                                        public void done(List<Cark> list, BmobException e) {
                                            for (Cark cark : list) {
                                                doMenu.id = cark.getObjectId();
                                            }
                                        }
                                    });
                                    dingDan.setCid(doMenu.id);
                                    dingDan.save(new SaveListener<String>() {//存储数据
                                        @Override
                                        public void done(String s, BmobException e) {
                                            if (e == null) {
                                                Toast.makeText(makeMenu, "订单提交成功！", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                                            }
                                        }
                                    });
                                }
                            }
                        });
                        dialog.setNegativeButton("再想想", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        dialog.show();
                    } else {
                        alertShow();
                    }

                    break;
                case R.id.button_find:
                    makeMenu.downloadPic(view);
                    cark.setName("马自达");
                    break;
                case R.id.bt_add:
                    makeMenu.sumPrice(view);
                    break;
                case R.id.bt_delete:
                    makeMenu.delePrice(view);
                    break;
                case R.id.btn_back:
                    Intent in = new Intent(doMenu.this, MainActivity.class);
                    startActivity(in);
                    break;
            }
        }
    }

    /**
     * 拉条监听器
     */
    class SeekBarListener implements SeekBar.OnSeekBarChangeListener {
        doMenu makeMenu = new doMenu();

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            Log.d("MakeMenu", seekBar.getProgress() + "2333");
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            Log.d("MakeMenu", seekBar.getProgress() + "3444");
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            makeMenu.price = seekBar.getProgress();
            Toast.makeText(MainActivity.mainActivity, seekBar.getProgress() + "", LENGTH_SHORT).show();

        }
    }

    /**
     * 单选框监听器
     */
    class RadioButtonListener implements RadioGroup.OnCheckedChangeListener {
        doMenu makeMenu = new doMenu();

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            switch (i) {
                case R.id.cb_jiaoche:
                    makeMenu.carType = "keche";
                    Log.d("RadioGroup", "轿车");
                    break;
                case R.id.cb_gongjiao:
                    makeMenu.carType = "gongjiao";
                    Log.d("RadioGroup", "公交");
                    break;
                case R.id.cb_huoche:
                    makeMenu.carType = "huoche";
                    Log.d("RadioGroup", "货车");
                    break;
                case R.id.cb_pika:
                    makeMenu.carType = "pika";
                    Log.d("RadioGroup", "皮卡");
                    break;
            }
        }
    }
}
