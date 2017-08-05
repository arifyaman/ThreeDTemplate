package com.xlip.threedtemp.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.xlip.threedtemp.Input.MyInputProcessor;
import com.xlip.threedtemp.Menu.Menu;
import com.xlip.threedtemp.Settings.Settings;
import com.xlip.threedtemp.World.World;

import static com.badlogic.gdx.Gdx.gl;

/**
 * Created by Arif on 19.07.2017.
 */

public class Screen implements Menu.ScreenCallbacks,World.ScreenCallbacks {
    private World world;
    private Menu menu;
    private ShaderProgram shaderProgram;
    private FrameBuffer frameBuffer;
    private SpriteBatch spriteBatch;
    private OrthographicCamera orthographicCamera;



    public Screen(World world, Menu menu) {

        this.world = world;
        orthographicCamera=new OrthographicCamera(Settings.orto_width,Settings.orto_height);
        orthographicCamera.update();
        spriteBatch = new SpriteBatch();
        spriteBatch.enableBlending();

        if(menu != null)
            setMenu(menu);

        Gdx.input.setInputProcessor(new MyInputProcessor(this).setMyInputCallback(world));
        //Gdx.input.setInputProcessor(new CameraInputController(world.camera));
    }

    public Screen(World world, Menu menu,ShaderProgram shaderProgram) {
        this(world,menu);
        this.shaderProgram = shaderProgram;
        this.frameBuffer = new FrameBuffer(Pixmap.Format.RGB888,Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),true);
        spriteBatch.setShader(shaderProgram);
    }


    public void render(float delta){
        if(shaderProgram != null)
            frameBuffer.begin();

        gl.glClearColor(1f, 1f, 1f, 1.f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        if(world != null){
            world.render(delta);
        }

        if(menu != null) {
            menu.draw(delta);
        }



        if(shaderProgram != null) {
            frameBuffer.end();
            TextureRegion t = new TextureRegion(frameBuffer.getColorBufferTexture());
            t.flip(false,true);
            spriteBatch.begin();
            spriteBatch.draw(t,0,0);
            spriteBatch.end();

        }

    }


    @Override
    public void setMenu(Menu menu) {
        this.menu = menu;
        this.menu.setScreenCallbacks(this);
        menu.setView(orthographicCamera.combined);
    }

    @Override
    public void disposeCurrentMenu() {
        this.menu = null;
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
