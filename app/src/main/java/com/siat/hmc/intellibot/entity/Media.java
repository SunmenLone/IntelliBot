package com.siat.hmc.intellibot.entity;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.siat.hmc.intellibot.MyApplication;

public class Media implements Parcelable{

    private static Context ctx = MyApplication.getInstance();

    private String name;

    private int cid;

    private int vid;

    private int type;

    public Media() {

    }

    protected Media(Parcel in) {
        cid = in.readInt();
        vid = in.readInt();
        name = in.readString();
        type = in.readInt();
    }

    public static final Creator<Media> CREATOR = new Creator<Media>() {
        @Override
        public Media createFromParcel(Parcel in) {
            return new Media(in);
        }

        @Override
        public Media[] newArray(int size) {
            return new Media[size];
        }
    };

    public int getCid() {
        return cid;
    }

    public void setCid(String name) {
        this.cid = ctx.getResources().getIdentifier(name, "drawable", ctx.getPackageName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(String name) {
        this.vid = ctx.getResources().getIdentifier(name, "raw", ctx.getPackageName());
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(cid);
        dest.writeString(name);
        dest.writeInt(vid);
        dest.writeInt(type);
    }
}
