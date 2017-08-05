package com.xlip.threedtemp;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.xlip.threedtemp.Interfaces.AndroidUnit;
import com.xlip.threedtemp.Menu.Effects.Defaults.DefaultMenuFinisher;
import com.xlip.threedtemp.Menu.Effects.Defaults.DefaultMenuOpener;
import com.xlip.threedtemp.Menu.Menu;
import com.xlip.threedtemp.Menu.Object.Font.NumberRenderer;
import com.xlip.threedtemp.Menu.Object.MenuObject;
import com.xlip.threedtemp.Objects.Model.Model;
import com.xlip.threedtemp.Settings.Settings;
import com.xlip.threedtemp.Utils.Lerp;
import com.xlip.threedtemp.World.World;
import com.xlip.threedtemp.Screen.Screen;

import java.io.File;


public class ThreeDTemp extends ApplicationAdapter {
	private AndroidUnit androidUnit;


	private Screen screen;
	private ShaderProgram shaderProgram;

	int number;


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
		Model.init();
		Assets.init();

		shaderProgram = new ShaderProgram(Assets.defaultShaderVertex,Assets.blurSahderFragment);
		if (shaderProgram.isCompiled() == false) throw new IllegalArgumentException("Error compiling shader: " + shaderProgram.getLog());

		screen = new Screen(new World(),setMenu());

		androidUnit.show_bottom_banner();
		number = 0;

	}

	NumberRenderer numberRenderer;

	private  Menu setMenu() {
		Menu test = new Menu() {
			@Override
			public void draw(float delta) {
				super.draw(delta);
			}
		};

		MenuObject menuObject = new MenuObject(Assets.i1,new Vector2(-400,-400),new Vector2(222,222)).setFinisher();
		menuObject.setClickedTexture(Assets.i2);

		MenuObject menuObject2 = new MenuObject(Assets.i1,new Vector2(-100,-400),new Vector2(222,222)).setFinisher();
		menuObject2.setClickedTexture(Assets.i2);

		numberRenderer = new NumberRenderer(Assets.atlas,400,0,70,100,new Vector2(0,Settings.appheight-150),new Vector2(70*1.2f,100*1.2f)).setAling(NumberRenderer.ALING_CENTER);

		numberRenderer.setColor(Color.BLACK);




		//test.addMenuObject(menuObject);
		//test.addMenuObject(menuObject2);
		test.addMenuObject(numberRenderer);
		test.setMenuOpener(new DefaultMenuOpener(9));
		test.setMenuFinisher(new DefaultMenuFinisher(22));

		return test;
	}


	@Override
	public void render () {
		final float delta = Math.min(1f / 30f, Gdx.graphics.getDeltaTime());
		screen.render(delta);

		numberRenderer.setNumber(number);

		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){


			if(number >= 10 && number<100)
				number += 10;
			else if(number >= 100 && number<1000)
				number += 100;
			else if(number >= 1000 && number<10000)
				number += 1000;
			else if(number >= 10000 && number<100000)
				number += 10000;
			else if(number >= 100000 && number<1000000)
				number += 100000;
			else if(number >= 1000000 && number<10000000)
				number += 1000000;
			else if(number >= 10000000 && number<100000000)
				number += 10000000;
			else number += 1;


		}


	}
	
	@Override
	public void dispose () {

	}
}
