package com.android.gsonlistview;

public class user {
    private String title;//部门
    private String date;//时间
    private String counnt;//人数
    private String pic;//图片

    public user(String title, String date, String counnt,String pic) {
       //Api参数
        this.title = title;
        this.date = date;
        this.counnt = counnt;
        this.pic=pic;
    }
      //get方法    title
    public String getTitle() {

        return title;
    }
    public void setTitle(String title) {

        this.title = title;
    }
                  //pic
    public String getPic() {
        return pic;
    }
    //set方法
    public void setPic(String pic) {

        this.pic = pic;
    }


                 //date
    public String getDate()
    {
        return date;
    }

    public void setDate(String date) {

        this.date = date;
    }

    //              counnt
    public String getCounnt() {
        return counnt;
    }

    public void setCounnt(String counnt)
    {
        this.counnt = counnt;
    }
}


