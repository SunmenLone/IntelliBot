package com.siat.hmc.intellibot.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.siat.hmc.intellibot.MyApplication;
import com.siat.hmc.intellibot.R;
import com.siat.hmc.intellibot.adapter.MyListAdapter;
import com.siat.hmc.intellibot.entity.ReadingItem;
import com.siat.hmc.intellibot.iface.ListItemInterface;

import java.util.ArrayList;

public class ReadingFragment extends Fragment implements ListItemInterface{
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */

    private ListView listView;

    private View lastClick = null;

    private ArrayList<ReadingItem> list;

    public ReadingFragment() {

    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        list = args.getParcelableArrayList("data");
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ReadingFragment newInstance(int sectionNumber, Bundle bundle) {
        ReadingFragment fragment = new ReadingFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reading, container, false);
        listView = (ListView) rootView.findViewById(R.id.list_reading);
        MyListAdapter adapter = new MyListAdapter(MyApplication.getInstance(), list);
        adapter.setLii(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (lastClick == null || lastClick != view) {
                    if (lastClick != null) {
                        lastClick.setSelected(false);
                        lastClick.setActivated(false);
                    }
                    view.setSelected(true);
                    view.setActivated(true);
                    lastClick = view;
                } else {
                    lastClick.setActivated(!lastClick.isActivated());
                }
            }
        });
        return rootView;
    }

    @Override
    public void clickOperation(int position) {
        listView.performItemClick(listView.getChildAt(position), position, listView.getItemIdAtPosition(position));
    }
}
