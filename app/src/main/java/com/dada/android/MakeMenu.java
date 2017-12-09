package com.dada.android;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dada.android.db.Cark;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;

import static android.widget.Toast.*;
import static com.dada.android.R.id.cancel_action;
import static com.dada.android.R.id.iv_show;

/**
 * Created by asus1 on 2017/11/27.
 */

public class MakeMenu extends AppCompatActivity {
    private Button show, find;
    private SeekBar seekBar;
    private String type;
    private RadioGroup radioGroup;
    private RadioButton rb_jc, rb_kc, rb_kec, rb_hc;
    static private ImageView iv_show;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.shouye);
        initNews();
        setListener();
    }

    public void downloadPic(final View view) {
        final BmobFile bmobfile = new BmobFile("aodia4.png", "", "http://bmob-cdn-15323.b0.upaiyun.com/2017/12/05/3d424f3140bde715809cc98804ba7110.png");
        bmobfile.download(new DownloadFileListener() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    Glide.with(view.getContext())
                            .load(s)
                            .placeholder(R.drawable.audia4)
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

    public void initNews() {
        show = (Button) findViewById(R.id.button_show);
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        find = (Button) findViewById(R.id.button_find);
        iv_show = (ImageView) findViewById(R.id.iv_show);
        rb_hc = (RadioButton) findViewById(R.id.cb_huoche);
        rb_jc = (RadioButton) findViewById(R.id.cb_jiaoche);
        rb_kec = (RadioButton) findViewById(R.id.cb_gongjiao);
        radioGroup = (RadioGroup) findViewById(R.id.rg_cheku);
        //jinaki
        //复习
    }

    public void setListener() {
        ButtonListener buttonListener = new ButtonListener();
        show.setOnClickListener(buttonListener);
        find.setOnClickListener(buttonListener);
        SeekBarListener seekBarListener = new SeekBarListener();
        seekBar.setOnSeekBarChangeListener(seekBarListener);
        RadioButtonListeer radioButtonListeer=new RadioButtonListeer();
        radioGroup.setOnCheckedChangeListener(radioButtonListeer);
    }
}

class ButtonListener implements OnClickListener {

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.button_show:
                Toast.makeText(MainActivity.mainActivity, "show", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_find:
                MakeMenu makeMenu = new MakeMenu();
                makeMenu.downloadPic(view);
                break;
        }
    }
}

class SeekBarListener implements OnSeekBarChangeListener {

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

        Toast.makeText(MainActivity.mainActivity, seekBar.getProgress() + "", LENGTH_SHORT).show();

    }
}

class RadioButtonListeer implements RadioGroup.OnCheckedChangeListener {

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        switch (i){
            case R.id.cb_jiaoche:
                Log.d("RadioGroup","轿车");
                break;
            case R.id.cb_gongjiao:
                Log.d("RadioGroup","公交");
                break;
            case R.id.cb_huoche:
                Log.d("RadioGroup","货车");
                break;
            case R.id.cb_pika:
                Log.d("RadioGroup","皮卡");
                break;
        }
    }
}

