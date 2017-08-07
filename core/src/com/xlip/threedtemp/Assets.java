package com.xlip.threedtemp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Arif on 15.07.2017.
 */

public class Assets {
    public static Texture atlas;
    public static String blurSahderFragment;
    public static String testFragment;
    public static String barrelBlurFragment;
    public static String defaultShaderVertex;

    //region items
    public static TextureRegion  i1;
    public static TextureRegion  i2;
    public static TextureRegion  i3;
    public static Texture splash;



    //endregion
    
    
    public static void init() {

        atlas = new Texture("tex.png");
        atlas.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        splash = new Texture("splash.png");
        splash.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        i1 = new TextureRegion(atlas,0,0,200,200);
        i2 = new TextureRegion(atlas,200,0,200,200);
        i3 = new TextureRegion(atlas,0,201,50,50);






        blurSahderFragment = Gdx.files.internal("blur.frag.glsl").readString();
        barrelBlurFragment = Gdx.files.internal("barrelBlur.glsl").readString();
        testFragment = Gdx.files.internal("testFragment.glsl").readString();
        defaultShaderVertex = Gdx.files.internal("default.vertex.glsl").readString();
    }




}
