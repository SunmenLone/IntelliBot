package com.siat.hmc.intellibot.fragment;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.siat.hmc.intellibot.MyApplication;
import com.siat.hmc.intellibot.R;
import com.siat.hmc.intellibot.activity.LanguageActivity;
import com.siat.hmc.intellibot.entity.Word;

public class LanguageFragment extends Fragment{
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */

    private Context ctx = MyApplication.getInstance();

    private Word ww;

    public LanguageFragment() {

    }

    private SoundPool sp = LanguageActivity.getSoundPool();

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        //ri = args.getParcelable("data");
        ww = args.getParcelable("data");
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
        img.setImageResource(ww.getId());
        w.setText(ww.getEn());
        d.setText(ww.getZh());

        ImageButton b = (ImageButton) rootView.findViewById(R.id.cabs_play);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int id = sp.load(ctx, ww.getVid(), 1);
                sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                        AudioManager am = (AudioManager) ctx.getSystemService(Context.AUDIO_SERVICE);
                        float audioMaxVolumn = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                        float volumnCurrent = am.getStreamVolume(AudioManager.STREAM_MUSIC);
                        float volumnRatio = volumnCurrent / audioMaxVolumn;
                        sp.play(id, volumnRatio, volumnRatio, 1, 0, 1);
                    }
                });
            }
        });


        return rootView;
    }




}
