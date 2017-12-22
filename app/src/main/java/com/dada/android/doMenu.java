package com.dada.android;

import android.content.DialogInterface;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dada.android.db.Cark;
import com.dada.android.db.DingDan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;

import static android.widget.Toast.LENGTH_SHORT;
import static com.dada.android.doMenu.list_name;

public class doMenu extends BaseActivity {
    private Button show, find, add, delete;
    private SeekBar seekBar;
    private String type;
    private RadioGroup radioGroup;
    private RadioButton rb_jc, rb_kc, rb_kec, rb_hc;
    static private ImageView iv_show;
    static String carType;
    static int price = 0;
    Cark cark = new Cark();
    static Map list_price = new HashMap();

    static List list_name=new ArrayList();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shouye);
        initViews();
        setListener();

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
                String pName = null, place = null;
                Log.d("测试", carType);
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
                            Toast.makeText(view.getContext(), "下载成功，保存路径", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(view.getContext(), "下载成功", Toast.LENGTH_SHORT).show();
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
     * 绑定布局
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
                        list_price.put(cark.getName(),cark.getPrice());
                        price+=cark.getPrice();
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
        if (cark.getName()!=null){
            if (list_name!=null){
                for (Object a:list_name){
                    if (cark.getName().equals(a.toString())){
                        price-=Integer.valueOf(list_price.get(cark.getName()).toString());
                        Log.d("删除车辆成功",String.valueOf(price));
                        list_name.remove(a);
                        break;
                    }else{
                        Log.d("是否添加该车辆","否");
                    }
                }
            }else{
                Log.d("订单","null");
            }
        }else {
            Log.d("车名：","null");
        }
    }

    public void initViews() {
        add = (Button) findViewById(R.id.bt_add);
        delete = (Button) findViewById(R.id.bt_delete);
        show = (Button) findViewById(R.id.button_show);
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        find = (Button) findViewById(R.id.button_find);
        iv_show = (ImageView) findViewById(R.id.iv_show);
        rb_hc = (RadioButton) findViewById(R.id.cb_huoche);
        rb_jc = (RadioButton) findViewById(R.id.cb_jiaoche);
        rb_kec = (RadioButton) findViewById(R.id.cb_gongjiao);
        radioGroup = (RadioGroup) findViewById(R.id.rg_cheku);
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
        SeekBarListener seekBarListener = new SeekBarListener();
        seekBar.setOnSeekBarChangeListener(seekBarListener);
        RadioButtonListener radioButtonListeer = new RadioButtonListener();
        radioGroup.setOnCheckedChangeListener(radioButtonListeer);
    }
}

/**
 * 按钮监听器
 */
class ButtonListener implements View.OnClickListener {
    doMenu makeMenu = new doMenu();

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.button_show:
                AlertDialog.Builder dialog=new AlertDialog.Builder(view.getContext());
                dialog.setTitle("确认订单");
                dialog.setMessage("确认提交订单吗？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DingDan dingDan=new DingDan();
                        for (Object a:list_name){
                            dingDan.setCname(a.toString());
                        }
                    }
                });
                dialog.setNegativeButton("再想想", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.show();
                break;
            case R.id.button_find:
                makeMenu.downloadPic(view);
                break;
            case R.id.bt_add:
                makeMenu.sumPrice(view);
                break;
            case R.id.bt_delete:
                makeMenu.delePrice(view);
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
            default:
                makeMenu.carType = "keche";
        }
    }
}
