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
import com.xlip.threedtemp.Utils.Lerp;
import com.xlip.threedtemp.World.World;
import com.xlip.threedtemp.Screen.Screen;


public class ThreeDTemp extends ApplicationAdapter {
	private Screen screen;
	private ShaderProgram shaderProgram;
	private AndroidUnit androidUnit;
	Lerp numberLerp;


	public ThreeDTemp(AndroidUnit androidUnit) {
		this.androidUnit = androidUnit;
	}

	@Override
	public void create () {
		Model.init();
		Assets.init();

		shaderProgram = new ShaderProgram(Assets.defaultShaderVertex,Assets.blurSahderFragment);
		if (shaderProgram.isCompiled() == false) throw new IllegalArgumentException("Error compiling shader: " + shaderProgram.getLog());

		screen = new Screen(new World(),setMenu());

		androidUnit.show_bottom_banner();

	}

	NumberRenderer numberRenderer;

	private  Menu setMenu() {
		Menu test = new Menu() {
			@Override
			public void draw(float delta) {
				super.draw(delta);
			}
		};

		MenuObject menuObject = new MenuObject(Assets.i1,new Vector2(-400,-400),new Vector2(150,150)).setFinisher();
		menuObject.setClickedTexture(Assets.i2);

		MenuObject menuObject2 = new MenuObject(Assets.i1,new Vector2(-200,-400),new Vector2(150,150)).setFinisher();
		menuObject2.setClickedTexture(Assets.i2);

		numberRenderer = new NumberRenderer(Assets.atlas,400,0,70,100,new Vector2(0,0),new Vector2(70*1.2f,100*1.2f));

		numberRenderer.setColor(Color.BLACK);

		numberLerp = new Lerp(0,1020,2f);


		test.addMenuObject(menuObject);
		test.addMenuObject(menuObject2);
		test.addMenuObject(numberRenderer);
		test.setMenuOpener(new DefaultMenuOpener(9));
		test.setMenuFinisher(new DefaultMenuFinisher(22));

		return test;
	}


	@Override
	public void render () {
		final float delta = Math.min(1f / 30f, Gdx.graphics.getDeltaTime());
		screen.render(delta);

		numberRenderer.setNumber(Math.round(numberLerp.getValue(delta)));

		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			numberLerp.go(numberLerp.getValue(0)+100);

		}


	}
	
	@Override
	public void dispose () {

	}
}
