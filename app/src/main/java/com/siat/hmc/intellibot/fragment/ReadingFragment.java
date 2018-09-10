package com.siat.hmc.intellibot.fragment;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.siat.hmc.intellibot.MyApplication;
import com.siat.hmc.intellibot.R;
import com.siat.hmc.intellibot.activity.ReadingActivity;
import com.siat.hmc.intellibot.adapter.MyListAdapter;
import com.siat.hmc.intellibot.entity.Media;
import com.siat.hmc.intellibot.iface.ListItemInterface;

import java.util.ArrayList;

public class ReadingFragment extends Fragment implements ListItemInterface{
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */

    private ListView listView;

    private View lastClick = null;

    private ArrayList<Media> list;

    private MediaPlayer mp = ReadingActivity.getMediaPlayer();

    private Context ctx = MyApplication.getInstance();

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
        final MyListAdapter adapter = new MyListAdapter(MyApplication.getInstance(), list);
        adapter.setLii(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                if (lastClick == null || lastClick != view) {
                    try {
                        Uri uri = Uri.parse("android.resource://" + ctx.getPackageName() + "/" + list.get(position).getVid());
                        mp.reset();
                        mp.setDataSource(ctx, uri);
                        mp.setLooping(false);
                        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                mp.start();
                            }
                        });
                        mp.prepare();
                        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                mp.stop();
                                view.setActivated(false);
                            }
                        });
                        if (lastClick != null) {
                            lastClick.setSelected(false);
                            lastClick.setActivated(false);
                        }
                        view.setSelected(true);
                        view.setActivated(true);
                        lastClick = view;
                    } catch (Exception e) {
                        Toast.makeText(ctx, "不存在音频文件", Toast.LENGTH_LONG).show();
                    }
                } else {
                    try {
                        if (lastClick.isActivated()) {
                            mp.stop();
                        } else {
                            Uri uri = Uri.parse("android.resource://" + ctx.getPackageName() + "/" + list.get(position).getVid());
                            mp.reset();
                            mp.setDataSource(ctx, uri);
                            mp.setLooping(false);
                            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                @Override
                                public void onPrepared(MediaPlayer mp) {
                                    mp.start();
                                }
                            });
                            mp.prepare();
                            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    mp.stop();
                                    view.setActivated(false);
                                }
                            });
                        }
                    } catch (Exception e) {
                        Toast.makeText(ctx, "不存在音频文件", Toast.LENGTH_LONG).show();
                    }
                    lastClick.setActivated(!lastClick.isActivated());
                }
            }
        });
        return rootView;
    }

    @Override
    public void clickOperation(int position) {
        listView.performItemClick(listView.getChildAt(position-listView.getFirstVisiblePosition()), position, listView.getItemIdAtPosition(position));
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {

        } else {
            try {
                mp.stop();
            } catch (Exception e) {

            }
        }

    }
}
