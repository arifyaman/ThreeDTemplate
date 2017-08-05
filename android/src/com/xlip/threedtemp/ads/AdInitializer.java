package com.xlip.threedtemp.ads;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.xlip.threedtemp.Interfaces.AndroidUnit;
import com.xlip.threedtemp.R;
import com.xlip.threedtemp.ThreeDTemp;

import java.io.File;

/**
 * Created by Arif on 5.08.2017.
 */

public class AdInitializer implements AndroidUnit {
    private Context context;

    private String bottom_banner_id;
    private String interstitial_id;
    private AdView bottom_banner;


    public AdInitializer(Context context) {
        this.context = context;
        this.bottom_banner_id = context.getResources().getString(R.string.bottom_banner_id);
        this.interstitial_id = context.getResources().getString(R.string.interstitial_id);
    }


    public View getView(View gameView){
        setUpAds();
        RelativeLayout layout = new RelativeLayout(context);
        layout.addView(gameView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        layout.addView(bottom_banner, params);

        return layout;
    }

    private void setUpAds(){
        bottom_banner = new AdView(context);
        bottom_banner.setVisibility(View.VISIBLE);
        bottom_banner.setBackgroundColor(Color.TRANSPARENT);
        bottom_banner.setAdUnitId(bottom_banner_id);
        bottom_banner.setAdSize(AdSize.SMART_BANNER);
        bottom_banner.setVisibility(View.INVISIBLE);
    }




    @Override
    public void show_bottom_banner() {

        bottom_banner.setVisibility(View.VISIBLE);
        AdRequest.Builder builder = new AdRequest.Builder();
        AdRequest ad = builder.build();
        bottom_banner.loadAd(ad);


    }

    @Override
    public void hide_bottom_banner() {
        bottom_banner.setVisibility(View.INVISIBLE);
    }

    @Override
    public void show_top_banner() {

    }

    @Override
    public void hide_top_banner() {

    }

    @Override
    public void show_video() {

    }

    @Override
    public void load_video_ad() {

    }

    @Override
    public void share_score(File f) {

    }

    @Override
    public void google_login() {

    }

    @Override
    public void show_score_table() {

    }

    @Override
    public void sent_score(int score) {

    }
}
