package com.xlip.threedtemp.Input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.xlip.threedtemp.Menu.Menu;
import com.xlip.threedtemp.Screen.Screen;

/**
 * Created by Arif on 15.07.2017.
 */

public class MyInputProcessor implements InputProcessor {
    OrthographicCamera camera;
    Menu menu;

    private MyInputCallback myInputCallback;

    public MyInputProcessor(Screen screen) {
        this.camera = screen.orthographicCamera;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 converted = converted(screenX,screenY);
        if(menu != null)
            menu.touchDown(converted);
        if(myInputCallback != null)
            myInputCallback.touchDown(converted);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector2 converted = converted(screenX,screenY);
        if(menu != null)
            menu.touchUp(converted);
        if(myInputCallback != null)
            myInputCallback.touchUp(converted);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 converted = converted(screenX,screenY);
        if(menu != null)
            menu.touchDragged(converted);
        if(myInputCallback != null)
            myInputCallback.touchDragged(converted);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Vector2 converted = converted(screenX,screenY);

        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public Vector2 converted(float x, float y) {
        Vector3 vector3 = camera.unproject(new Vector3(x,y,0));
        return new Vector2(vector3.x,vector3.y);
    }


    public interface MyInputCallback {
        boolean touchDown(Vector2 cxy);
        boolean touchUp(Vector2 cxy);
        boolean touchDragged(Vector2 cxy);
    }

    public InputProcessor setMyInputCallback(MyInputCallback myInputCallback) {
        this.myInputCallback = myInputCallback;
        return this;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
