package com.xlip.threedtemp;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.xlip.threedtemp.Interfaces.AndroidUnit;
import com.xlip.threedtemp.Objects.Model.DefaultModels;
import com.xlip.threedtemp.Screen.Screen;
import com.xlip.threedtemp.Screen.SplashScreen;

import java.io.File;


public abstract class ThreeDTemp extends ApplicationAdapter implements SplashScreen.MainCallBacks {
	private AndroidUnit androidUnit;
	public Screen screen;

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
					public void addModelTheseParts(ModelBuilder modelBuilder) {
						ThreeDTemp.this.addModelTheseParts(modelBuilder);
					}
				};
				defaultModels.init();

				doInSplashScreenBackground();
			}
		};
		//onSplashScreenFinished();
	}

	@Override
	public void onSplashScreenFinished() {


	}


	public abstract void doInSplashScreenBackground();
	public abstract void addModelTheseParts(ModelBuilder modelBuilder);


	@Override
	public void render () {
		final float delta = Math.min(1f / 30f, Gdx.graphics.getDeltaTime());

		if(screen != null)
			screen.render(delta);

	}


	@Override
	public void dispose () {

	}

}
