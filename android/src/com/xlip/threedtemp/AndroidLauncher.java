package com.xlip.threedtemp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.xlip.threedtemp.Interfaces.AndroidUnit;
import com.xlip.threedtemp.ThreeDTemp;
import com.xlip.threedtemp.ads.AdInitializer;
import com.xlip.threedtemp.google.GoogleClient;

import java.io.File;

public class AndroidLauncher extends AndroidApplication implements AndroidUnit {
	private AdInitializer adInitializer;
	private GoogleClient googleClient;



	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		adInitializer = new AdInitializer(this);
		googleClient = new GoogleClient(this);


		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.numSamples = 4;
		config.useImmersiveMode = true;

		View gameView = initializeForView(new ThreeDTemp(this), config);
		setContentView(adInitializer.getView(gameView));


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
