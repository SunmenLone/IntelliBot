package com.siat.hmc.intellibot.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.siat.hmc.intellibot.R;
import com.siat.hmc.intellibot.entity.Media;
import com.siat.hmc.intellibot.iface.ListItemInterface;

import java.util.ArrayList;

public class MyListAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<Media> list;
    private ListItemInterface lii;

    public MyListAdapter(Context ctx, ArrayList<Media> objs) {
        context = ctx;
        list = objs;
    }

    public void setLii(ListItemInterface ctx) {
        lii = ctx;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Media m = list.get(position);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_reading_list, null);
            viewHolder.cover = (ImageView) convertView.findViewById(R.id.cover);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.imgBtn = (ImageButton) convertView.findViewById(R.id.operation);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (m.getType() == 1) {
            viewHolder.cover.setVisibility(View.VISIBLE);
            viewHolder.cover.setImageResource(m.getCid());
            viewHolder.cover.setScaleX(.618f);
            viewHolder.cover.setScaleY(.618f);
            viewHolder.cover.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        } else {
            viewHolder.cover.setVisibility(View.GONE);
        }
        viewHolder.name.setText(m.getName());
        viewHolder.imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lii.clickOperation(position);
            }
        });

        return convertView;
    }

    private class ViewHolder {
        private ImageView cover;
        private TextView name;
        private ImageButton imgBtn;
    }
}