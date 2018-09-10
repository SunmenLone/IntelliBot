package com.siat.hmc.intellibot.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.siat.hmc.intellibot.MyApplication;
import com.siat.hmc.intellibot.R;
import com.siat.hmc.intellibot.entity.Media;
import com.siat.hmc.intellibot.fragment.ReadingFragment;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ReadingPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Media> books = new ArrayList<>();
    private ArrayList<Media> songs = new ArrayList<>();

    public ReadingPagerAdapter(FragmentManager fm) {
        super(fm);

        try {

            InputStream jsonStream = MyApplication.getInstance().getResources().openRawResource(R.raw.medias);
            JSONObject jsonObject = new JSONObject(IOUtils.toString(jsonStream, "UTF-8"));
            JSONArray jsonWords = jsonObject.getJSONArray("medias");

            for (int i = 0; i < jsonWords.length(); i++) {
                JSONObject jsonWord = jsonWords.getJSONObject(i);
                Media m = new Media();
                m.setName(jsonWord.getString("name"));
                m.setCid(jsonWord.getString("src"));
                m.setVid(jsonWord.getString("src"));
                m.setType(jsonWord.getInt("type"));
                if (m.getType() == 1) {
                    songs.add(m);
                } else {
                    books.add(m);
                }
            }

        } catch (IOException ioe) {

        } catch (JSONException je) {

        } catch (Exception e) {

        }
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a ReadingFragment (defined as a static inner class below).

        Bundle bundle = new Bundle();
        if (position == 0) {
            bundle.putParcelableArrayList("data", books);
        } else {
            bundle.putParcelableArrayList("data", songs);
        }
        return ReadingFragment.newInstance(position + 1, bundle);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 2;
    }
}
