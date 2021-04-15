package com.android.gsonlistview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends Activity {



    public String date;
    public ListView lv;
    public ArrayList<user> listuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv= (ListView) findViewById(R.id.lv);
        init();
        //listview点击事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,""+listuser.get(position).getTitle(),Toast.LENGTH_LONG).show();
            }
        });
    }
    //获取json数据部分
    private void init() {
        new Thread(new Runnable() {
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                //服务器返回的地址
                Request request = new Request.Builder()
                        .url("http://10.0.2.2/testServe.json").build();

                try {
                    Response response = okHttpClient.newCall(request).execute();
                    //获取到数据
                    date = response.body().string();
                    //在线程中没有办法实现主线程操作
                    Message message = new Message();
                    message.what = 1;
                    han.sendMessage(message);
                    //把数据传入解析josn数据方法
                    Gsonjx(date);

                } catch (IOException e) {


                    e.printStackTrace();
                }
            }
        }).start();
    }

    public android.os.Handler han = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Toast.makeText(MainActivity.this,""+date,Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    lv.setAdapter(new useradapter(MainActivity.this, listuser));//调用listview适配器
                    break;

            }
        }

    };
    private void Gsonjx(String date) { //gson解析部分
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(date).getAsJsonArray();
        Gson gson = new Gson();
        listuser = new ArrayList<>();
        for (JsonElement user : jsonArray) {
            user userBean = gson.fromJson(user, user.class);
            listuser.add(userBean);
        }
        Message message = new Message();
        message.what = 2;
        han.sendMessage(message);
    }



}