package com.xlip.threedtemp.Interfaces;

import java.io.File;

/**
 * Created by Yaman on 20.02.2016.
 */
public interface AndroidUnit {

     void show_bottom_banner();
     void hide_bottom_banner();

     void show_top_banner();
     void hide_top_banner();


     void show_video();
     void load_video_ad();


     void share_score(File f);

     void google_login();

     void show_score_table();

     void sent_score(int score);


}
