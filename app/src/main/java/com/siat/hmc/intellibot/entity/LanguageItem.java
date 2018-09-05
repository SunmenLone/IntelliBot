package com.siat.hmc.intellibot.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class LanguageItem implements Parcelable {

    int picId;

    String word;

    String detail;

    public LanguageItem(int id, String w, String d) {
        picId = id;
        word = w;
        detail = d;
    }

    public int getPicId() {
        return picId;
    }

    public String getWord() {
        return word;
    }

    public String getDetail() {
        return detail;
    }

    protected LanguageItem(Parcel in) {
    }

    public static final Creator<LanguageItem> CREATOR = new Creator<LanguageItem>() {
        @Override
        public LanguageItem createFromParcel(Parcel in) {
            return new LanguageItem(in);
        }

        @Override
        public LanguageItem[] newArray(int size) {
            return new LanguageItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(picId);
        dest.writeString(word);
        dest.writeString(detail);
    }
}
