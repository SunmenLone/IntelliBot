package com.siat.hmc.intellibot.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.siat.hmc.intellibot.R;
import com.siat.hmc.intellibot.entity.ReadingItem;
import com.siat.hmc.intellibot.iface.ListItemInterface;

import java.util.ArrayList;

public class MyListAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<ReadingItem> list;
    private LayoutInflater inflater;
    private ListItemInterface lii;

    private View lastClick = null;

    public MyListAdapter(Context ctx, ArrayList<ReadingItem> objs) {
        context = ctx;
        list = objs;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        ReadingItem ri = list.get(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_reading_list, null);
        }
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView description = (TextView) convertView.findViewById(R.id.description);
        name.setText(ri.getName());
        description.setText(ri.getDescription());
        ImageButton opt = (ImageButton) convertView.findViewById(R.id.operation);
        opt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lii.clickOperation(position);
            }
        });
        return convertView;
    }
}