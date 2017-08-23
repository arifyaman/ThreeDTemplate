package com.xlip.threedtemp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.xlip.stickyandspeedy.StickyAndSpeedy;
import com.xlip.threedtemp.Interfaces.AndroidUnit;
import com.xlip.threedtemp.ads.AdInitializer;
import com.xlip.threedtemp.analytics.AnalyticsApplication;
import com.xlip.threedtemp.google.GoogleClient;

import java.io.File;

public class AndroidLauncher extends AndroidApplication implements AndroidUnit {
	private AdInitializer adInitializer;
	private GoogleClient googleClient;
	private Tracker mTracker;



	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		adInitializer = new AdInitializer(this);
		googleClient = new GoogleClient(this);


		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.numSamples = 4;
		config.useImmersiveMode = true;

		View gameView = initializeForView(new StickyAndSpeedy(this), config);
		setContentView(adInitializer.getView(gameView));

		AnalyticsApplication application = (AnalyticsApplication) getApplication();
		mTracker = application.getDefaultTracker();

		Log.e("SetScreenAnalytics", "Setting screen name: ");
		mTracker.setScreenName("Opening");
		mTracker.send(new HitBuilders.ScreenViewBuilder().build());

	}




	@Override
	protected void onActivityResult(int requestCode, final int resultCode, Intent intent) {
		googleClient.onActivityResult(requestCode,resultCode,intent);

	}



	@Override
	public void show_bottom_banner() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				adInitializer.show_bottom_banner();
			}
		});
	}

	@Override
	public void hide_bottom_banner() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				adInitializer.hide_bottom_banner();
			}
		});
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
		googleClient.connect();
	}

	@Override
	public void show_score_table() {

	}

	@Override
	public void sent_score(int score) {

	}
}
