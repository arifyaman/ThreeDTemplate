package com.xlip.threedtemp.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.xlip.threedtemp.Menu.Menu;
import com.xlip.threedtemp.Input.MyInputProcessor;
import com.xlip.threedtemp.Settings.Settings;
import com.xlip.threedtemp.World.World;
import com.xlip.threedtemp.Shader.MyShaderProgram;

import static com.badlogic.gdx.Gdx.gl;

/**
 * Created by Arif on 19.07.2017.
 */

public class Screen implements Menu.ScreenCallbacks,World.ScreenCallbacks {
    private World world;
    private Menu menu;
    private MyShaderProgram myShaderProgram;
    private FrameBuffer frameBuffer;
    public SpriteBatch spriteBatch;
    public OrthographicCamera orthographicCamera;
    private MyInputProcessor myInputProcessor;


    public Screen() {
        orthographicCamera=new OrthographicCamera(Settings.orto_width,Settings.orto_height);
        orthographicCamera.update();

        spriteBatch = new SpriteBatch();
        spriteBatch.enableBlending();
        this.myInputProcessor = new MyInputProcessor(this);
        Gdx.input.setInputProcessor(myInputProcessor);

    }

    public Screen(World world, Menu menu) {
        this();
        this.world = world;

        if(menu != null)
            setMenu(menu);
    }

    public Screen(World world, Menu menu, MyShaderProgram myShaderProgram) {
        this(world,menu);
        this.myShaderProgram = myShaderProgram;
        this.frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888,Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),true);
    }


    public void render(float delta) {
        if(myShaderProgram != null)
            frameBuffer.begin();

        gl.glClearColor(1,1,1,1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        if(world != null){
            world.render(delta);
        }

        if(menu != null) {
            menu.draw(delta);
        }



        if(myShaderProgram != null) {
            frameBuffer.end();
            TextureRegion t = new TextureRegion(frameBuffer.getColorBufferTexture());
            t.flip(false,true);

            spriteBatch.setShader(myShaderProgram);
            spriteBatch.setProjectionMatrix(orthographicCamera.combined);
            spriteBatch.begin();
            spriteBatch.draw(t,-Settings.orto_width/2,-Settings.orto_height/2,Settings.orto_width,Settings.orto_height);
            spriteBatch.end();
            myShaderProgram.update(delta);
        }

    }


    @Override
    public void setMenu(Menu menu) {
        this.menu = menu;
        this.menu.setScreenCallbacks(this);
        this.menu.setView(orthographicCamera.combined);
        this.myInputProcessor.setMenu(menu);
    }

    @Override
    public void disposeCurrentMenu() {
        this.menu = null;
    }

    public void setWorld(World world) {
        this.world = world;
        this.myInputProcessor.setMyInputCallback(world);

    }

    public World getWorld() {
        return world;
    }

    public Menu getMenu() {
        return menu;
    }

    public OrthographicCamera getOrthographicCamera() {
        return orthographicCamera;
    }
}
