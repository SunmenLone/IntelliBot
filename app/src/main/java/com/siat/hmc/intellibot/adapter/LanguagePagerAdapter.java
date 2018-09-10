package com.siat.hmc.intellibot.adapter;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.siat.hmc.intellibot.MyApplication;
import com.siat.hmc.intellibot.R;
import com.siat.hmc.intellibot.entity.Word;
import com.siat.hmc.intellibot.fragment.LanguageFragment;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LanguagePagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Word> cabs = new ArrayList<>();

    public LanguagePagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a ReadingFragment (defined as a static inner class below).

        Bundle bundle = new Bundle();
        bundle.putParcelable("data", cabs.get(position));

        return LanguageFragment.newInstance(position + 1, bundle);
    }

    @Override
    public int getCount() {
        return cabs != null ? cabs.size() : 0;
    }

    public void setDataSourse(String name) {

        int resId = -1;

        switch(name){
            case "numbers":
                resId = R.raw.numbers;
                break;
            case "animals":
                resId = R.raw.animals;
                break;
            case "fruits":
                resId = R.raw.fruits;
                break;
            case "eats":
                resId = R.raw.eats;
                break;
        }

        cabs.clear();

        try {

            InputStream jsonStream = MyApplication.getInstance().getResources().openRawResource(resId);
            JSONObject jsonObject = new JSONObject(IOUtils.toString(jsonStream, "UTF-8"));
            JSONArray jsonWords = jsonObject.getJSONArray(name);

            for (int i = 0; i < jsonWords.length(); i++) {
                JSONObject jsonWord = jsonWords.getJSONObject(i);
                Word w = new Word();
                w.setZh(jsonWord.getString("zh"));
                w.setEn(jsonWord.getString("en"));
                w.setId(jsonWord.getString("name"));
                w.setVid(jsonWord.getString("name"));
                cabs.add(w);
            }

        } catch (IOException ioe) {

        } catch (JSONException je) {

        } catch (Exception e) {

        }

    }

}
