package com.android.gsonlistview;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class useradapter extends BaseAdapter {
    public Context con;
    public List<user> list;
    public LayoutInflater inflater;

    public useradapter(Context context, List<user> user) {
        this.con = context;
        this.list = user;
        inflater = LayoutInflater.from(con);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.listview_item, null);
        TextView tv1 = (TextView) view.findViewById(R.id.tv1);
        TextView tv2 = (TextView) view.findViewById(R.id.tv2);
        TextView tv3 = (TextView) view.findViewById(R.id.tv3);
        ImageView img = (ImageView) view.findViewById(R.id.img);
        tv1.setText("部门:" + list.get(position).getTitle());
        tv2.setText("时间:" + list.get(position).getDate());
        tv3.setText("人数:" + list.get(position).getCounnt());
        //判断获取的json中图片是否为空
        if (TextUtils.isEmpty(list.get(position).getPic().toString())) {
            Picasso.with(con)
                    .cancelRequest(img);
            img.setImageDrawable(con.getResources().getDrawable(R.mipmap.ic_launcher));//当图片为空时显示原始机器人图
        } else {
            //图片加载
            Picasso.with(con)
                    .load(list.get(position).getPic().toString())
                    .placeholder(R.mipmap.ic_launcher)//图片加载中时显示原始机器人图
                    .into(img);
        }
        return view;
    }
}


