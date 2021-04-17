package com.android.gsonlistview;

import android.app.Activity;
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
    //适配器
public class useradapter extends BaseAdapter {
        /*
        Context是个抽象类，通过类的结构可以看到：Activity、Service、Application都是Context的子类
        Context是一个场景，描述的是一个应用程序环境的信息，即上下文，代表与操作系统的交互的一种过程。
        Context是个抽象类，而Activity、Service、Application等都是该类的一个实现
        应用在三种情况下会创建Context对象（即通常说的context）：
1。 创建Application 对象时，即第一次启动app时。 整个App共一个Application对象，所以也只有一个Application 的Context，Application销毁，它也销毁；
2。 创建Activity对象时。Activity销毁，它也销毁;
3。 创建Service对象时。Service销毁，它也销毁。

        */
    public Context con;

        /*
        List里面的所有对象是User类型的对象
        List<user> 泛型
        java现在的版本，所有的容器类都用改为泛型实现了
        也可以理解为< >里的是一个参数，该参数可以代表任何类，
        也可以限定上限条件，下限条件
        声明的List只能存放User对象了
        */
    public List<user> list;

       /* LayoutInflater 是一个抽象类
        获得 LayoutInflater 实例的三种方式:

                1.LayoutInflater inflater = getLayoutInflater();  //调用Activity的getLayoutInflater()

                2.LayoutInflater localinflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

3. LayoutInflater inflater = LayoutInflater.from(context);

这3中方式本质上是相同的*/

    public LayoutInflater inflater;

    //继承抽象方法 --> 自动生成方法存根
    public useradapter(Context context, List<user> user) {
        this.con = context;
        this.list = user;
        inflater = LayoutInflater.from(con);

    }
     //覆盖
    @Override
    //行数
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
    //convertView 转行视图   有点类似iOS的自定义cell 下面做子控件的赋值
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(R.layout.listview_item, null);
        //加载xml子控件
        TextView tv1 = (TextView) view.findViewById(R.id.tv1);
        TextView tv2 = (TextView) view.findViewById(R.id.tv2);
        TextView tv3 = (TextView) view.findViewById(R.id.tv3);
        ImageView img = (ImageView) view.findViewById(R.id.img);

        //赋值
        tv1.setText("部门:" + list.get(position).getTitle());
        tv2.setText("时间:" + list.get(position).getDate());
        tv3.setText("人数:" + list.get(position).getCounnt());

       // TextUtils系统自带的一个工具类，不能通过new来创建，它的方法都是static类型的，可以直接调用，只占用了一个内存

        //判断获取的json中图片是否为空
        if (TextUtils.isEmpty(list.get(position).getPic().toString())) {
           // Picasso.with(con).cancelRequest(img);
                               Picasso.with(con).cancelRequest(img);
           // img.setImageDrawable(con.getResources().getDrawable(R.mipmap.ic_launcher));//当图片为空时显示原始机器人图
            img.setImageDrawable(con.getResources().getDrawable(R.mipmap.ic_launcher, null));
            //getDrawable(R.mipmap.ic_launcher)已经过时了，现在是要 getDrawable(R.mipmap.ic_launcher, null)，就是加个参数theme,并且为null
        } else {
            //图片加载 类似iOS的sdwemimage
           /*Picasso.with(con)
                    .load(list.get(position).getPic().toString())
                    .placeholder(R.mipmap.ic_launcher)//图片加载中时显示原始机器人图
                    .into(img);        */
              //上面能方法也是可以的，记住 图片地址一定要有用
            Picasso.with(con).load(list.get(position).getPic()).into(img);
        }
        return view;
    }
}


