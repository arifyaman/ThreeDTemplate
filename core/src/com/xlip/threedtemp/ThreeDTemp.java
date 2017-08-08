package com.xlip.threedtemp;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.xlip.threedtemp.Interfaces.AndroidUnit;
import com.xlip.threedtemp.Objects.Model.DefaultModels;
import com.xlip.threedtemp.Screen.SplashScreen;

import java.io.File;


public abstract class ThreeDTemp extends ApplicationAdapter implements SplashScreen.MainCallBacks {
	private AndroidUnit androidUnit;
	private SplashScreen screen;

	public ThreeDTemp(AndroidUnit androidUnit) {
		this.androidUnit = androidUnit;
	}

	//for desktop mode
	public ThreeDTemp() {
		this.androidUnit = new AndroidUnit() {
			@Override
			public void show_bottom_banner() {

			}

			@Override
			public void hide_bottom_banner() {

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
		};
	}

	@Override
	public void create () {
		Assets.init();
		screen = new SplashScreen(2,this) {
			@Override
			public void doInBackGround() {
				DefaultModels defaultModels = new DefaultModels() {
					@Override
					public void addModelTheseParts() {
						ThreeDTemp.this.addModelTheseParts();
						this.init();
					}
				};
			}
		};
		//onSplashScreenFinished();
	}

	@Override
	public void onSplashScreenFinished() {


	}



	public abstract void addModelTheseParts();


	@Override
	public void render () {
		final float delta = Math.min(1f / 30f, Gdx.graphics.getDeltaTime());
		System.out.println(1/delta);


		if(screen != null)
			screen.render(delta);

	}


	@Override
	public void dispose () {

	}
}
