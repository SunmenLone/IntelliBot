package com.siat.hmc.intellibot.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.siat.hmc.intellibot.R;
import com.siat.hmc.intellibot.entity.LanguageItem;

import java.util.ArrayList;

public class LanguageFragment extends Fragment{
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */

    private LanguageItem ri;

    public LanguageFragment() {

    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        ri = args.getParcelable("data");
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static LanguageFragment newInstance(int sectionNumber, Bundle bundle) {
        LanguageFragment fragment = new LanguageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_language, container, false);
        ImageView img = (ImageView) rootView.findViewById(R.id.img_view);
        TextView w = (TextView) rootView.findViewById(R.id.word);
        TextView d = (TextView) rootView.findViewById(R.id.detail);
        img.setImageResource(ri.getPicId());
        w.setText(ri.getWord());
        d.setText(ri.getDetail());

        return rootView;
    }


}
