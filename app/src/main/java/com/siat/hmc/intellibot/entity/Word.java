package com.siat.hmc.intellibot.entity;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.siat.hmc.intellibot.MyApplication;

public class Word implements Parcelable{

    private static Context ctx = MyApplication.getInstance();

    private int id;

    private String zh;

    private String en;

    private int vid;

    public Word() {

    }

    protected Word(Parcel in) {
        id = in.readInt();
        zh = in.readString();
        en = in.readString();
        vid = in.readInt();
    }

    public static final Creator<Word> CREATOR = new Creator<Word>() {
        @Override
        public Word createFromParcel(Parcel in) {
            return new Word(in);
        }

        @Override
        public Word[] newArray(int size) {
            return new Word[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(String name) {
        this.id = ctx.getResources().getIdentifier(name, "drawable", ctx.getPackageName());
    }

    public String getZh() {
        return zh;
    }

    public void setZh(String zh) {
        this.zh = zh;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(String name) {
        this.vid = ctx.getResources().getIdentifier(name, "raw", ctx.getPackageName());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(zh);
        dest.writeString(en);
        dest.writeInt(vid);
    }
}
