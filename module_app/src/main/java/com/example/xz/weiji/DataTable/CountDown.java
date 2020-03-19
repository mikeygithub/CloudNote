package com.example.xz.weiji.DataTable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;


public class CountDown extends BmobObject {
    private String text;
    private String laterdate;
    private BmobUser user;

    public BmobUser getUser() {
        return user;
    }

    public void setUser(BmobUser user) {
        this.user = user;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLaterdate() {
        return laterdate;
    }

    public void setLaterdate(String laterdate) {
        this.laterdate = laterdate;
    }
}
