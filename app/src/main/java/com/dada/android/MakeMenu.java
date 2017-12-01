package com.dada.android;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

import com.dada.android.db.Cark;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;

import static android.widget.Toast.*;

/**
 * Created by asus1 on 2017/11/27.
 */

public class MakeMenu extends AppCompatActivity {
    private Button show, find;
    private SeekBar seekBar;
    private String type;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shouye);
        initNews();
        setListener();
        BmobFile bmobfile =new BmobFile("xxx.png","","http://bmob-cdn-15323.b0.upaiyun.com/2017/11/29/cc9ac3e340cbcd098056465c6e35369f.png");
//        bmobfile.download(new DownloadFileListener() {
//            @Override
//            public void done(String s, BmobException e) {
//                if (e==null){
//                    String url=s;
//
//                    Toast.makeText(MakeMenu.this, "下载成功，保存路径", Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(MakeMenu.this, "下载成功", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onProgress(Integer integer, long l) {
//                Log.i("bmob", "下载进度：" + integer + "," +l);
//            }
//        });
        BmobQuery<Cark> query1 = new BmobQuery<Cark>();

    }


    public void downloadFile(final BmobFile file) {
        //允许设置下载文件的存储路径，默认下载文件的目录为：context.getApplicationContext().getCacheDir()+"/bmob/"
        file.download(new DownloadFileListener() {

            @Override
            public void onStart() {
                Toast.makeText(MakeMenu.this, "开始下载：", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void done(String savePath, BmobException e) {
                if (e == null) {
                    String s=file.getFileUrl();
                    Toast.makeText(MakeMenu.this, "下载成功，保存路径", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MakeMenu.this, "下载成功", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onProgress(Integer value, long newworkSpeed) {
                Log.i("bmob", "下载进度：" + value + "," + newworkSpeed);
            }
        });
    }

    public void initNews() {
        show = (Button) findViewById(R.id.button_show);
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        find = (Button) findViewById(R.id.button_find);
    }

    public void setListener() {
        ButtonListener buttonListener = new ButtonListener();
        show.setOnClickListener(buttonListener);
        find.setOnClickListener(buttonListener);
        SeekBarListener seekBarListener = new SeekBarListener();
        seekBar.setOnSeekBarChangeListener(seekBarListener);
    }
}

class ButtonListener implements OnClickListener {

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_show:
                Toast.makeText(MainActivity.mainActivity, "show", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_find:

                final MakeMenu makeMenu = new MakeMenu();
                BmobQuery<Cark> query = new BmobQuery<Cark>();
                query.findObjects(new FindListener<Cark>() {
                    @Override
                    public void done(List<Cark> list, BmobException e) {
                        if (e == null) {
                            for (Cark cark : list) {
                                BmobFile file = cark.getDrawable();
                                if (file != null) {
                                    makeMenu.downloadFile(file);
                                    Log.d("MakeMenu", "Find!!！");
                                }
                            }
                        } else {
                            Toast.makeText(makeMenu, "查询失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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

