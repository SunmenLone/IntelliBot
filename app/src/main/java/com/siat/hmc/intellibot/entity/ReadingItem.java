package com.siat.hmc.intellibot.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class ReadingItem implements Parcelable{

    private String name;
    private String description;

    public ReadingItem(String name, String description) {
        this.name = name;
        this.description = description;
    }

    protected ReadingItem(Parcel in) {
        name = in.readString();
        description = in.readString();
    }

    public static final Creator<ReadingItem> CREATOR = new Creator<ReadingItem>() {
        @Override
        public ReadingItem createFromParcel(Parcel in) {
            return new ReadingItem(in);
        }

        @Override
        public ReadingItem[] newArray(int size) {
            return new ReadingItem[size];
        }
    };

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
    }
}
