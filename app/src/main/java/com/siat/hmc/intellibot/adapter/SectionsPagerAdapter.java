package com.siat.hmc.intellibot.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.siat.hmc.intellibot.entity.ReadingItem;
import com.siat.hmc.intellibot.fragment.PlaceholderFragment;

import java.util.ArrayList;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<ReadingItem> books = null;
    private ArrayList<ReadingItem> songs = null;

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
        if (books == null) {
            books = new ArrayList<>();
            books.add(new ReadingItem("Witch Hunt", "恐怖，独立，冒险，血腥"));
            books.add(new ReadingItem("Witch Hunt", "恐怖，独立，冒险，血腥"));
            books.add(new ReadingItem("Witch Hunt", "恐怖，独立，冒险，血腥"));
        }

        if (songs == null) {
            songs = new ArrayList<>();
            songs.add(new ReadingItem("Shadows: Awakening", "角色扮演，动作，暴力，单人"));
            songs.add(new ReadingItem("Shadows: Awakening", "角色扮演，动作，暴力，单人"));
            songs.add(new ReadingItem("Shadows: Awakening", "角色扮演，动作，暴力，单人"));
        }
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        Bundle bundle = new Bundle();
        if (position == 0) {
            bundle.putParcelableArrayList("data", books);
        } else {
            bundle.putParcelableArrayList("data", songs);
        }
        return PlaceholderFragment.newInstance(position + 1, bundle);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 2;
    }
}
