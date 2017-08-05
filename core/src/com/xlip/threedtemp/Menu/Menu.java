package com.xlip.threedtemp.Menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.xlip.threedtemp.Menu.Effects.MenuEffect;
import com.xlip.threedtemp.Menu.Object.MenuObject;
import com.xlip.threedtemp.Utils.Lerp;
import com.xlip.threedtemp.World.World;

/**
 * Created by Arif on 14.07.2017.
 */

public class Menu implements MenuObject.MenuObjectCallBacks {
    private SpriteBatch spriteBatch;
    private Array<MenuObject> menuObjects;
    private Matrix4 view;
    private ScreenCallbacks screenCallbacks;

    private MenuEffect menuFinisher;
    private MenuEffect menuOpener;


   public Menu() {
        this.spriteBatch = new SpriteBatch();
        spriteBatch.enableBlending();
        this.menuObjects = new Array<MenuObject>();
    }

    public void setView(Matrix4 view) {
        this.view = view;
        if(menuFinisher != null) {
            menuFinisher.setView(view);
            menuFinisher.setScreenCallbacks(screenCallbacks);
        }

        if(menuOpener != null) {
            menuOpener.setView(view);
            menuOpener.setScreenCallbacks(screenCallbacks);
        }


    }



    public void addMenuObject(MenuObject menuObject) {
        menuObject.setMenuObjectCallBacks(this);
        this.menuObjects.add(menuObject);
    }


    public void draw(float delta) {
        if(menuOpener != null && menuOpener.isStarted()){
            menuOpener.tick(delta);
        }

        if(menuFinisher != null &&  menuFinisher.isStarted()){
            menuFinisher.tick(delta);
        }

        if(menuObjects.size > 0) {

            spriteBatch.setProjectionMatrix(view);
            spriteBatch.begin();
            for(MenuObject object : menuObjects) {
               object.draw(spriteBatch,delta);
            }
            spriteBatch.end();

            for (MenuObject m :
                    menuObjects) {
                m.update(delta);
            }

        }
    }


    public boolean touchDown(Vector2 v){
        for(MenuObject object : menuObjects) {
            object.touchDown(v);
        }
        return true;
    }

    public boolean touchUp(Vector2 v){
        for(MenuObject object : menuObjects) {
            object.touchUp(v);
        }

        return true;
    }

    public boolean touchDragged(Vector2 v) {
        for(MenuObject object : menuObjects) {
            object.touchDraged(v);
        }

        return true;

    }

    @Override
    public boolean finish() {
        if(menuFinisher == null)
            screenCallbacks.disposeCurrentMenu();
        else
            menuFinisher.start();

        return false;
    }





    public interface ScreenCallbacks {
        void setMenu(Menu menu);
        void disposeCurrentMenu();
        World getWorld();

    }




    //GETTER SETTER


    public Matrix4 getView() {
        return view;
    }



    public ScreenCallbacks getScreenCallbacks() {
        return screenCallbacks;
    }

    public void setScreenCallbacks(ScreenCallbacks screenCallbacks) {
        this.screenCallbacks = screenCallbacks;
    }

    public MenuEffect getMenuFinisher() {
        return menuFinisher;
    }

    public void setMenuFinisher(MenuEffect menuFinisher) {
        this.menuFinisher = menuFinisher;
    }

    public MenuEffect getMenuOpener() {
        return menuOpener;
    }

    public void setMenuOpener(MenuEffect menuOpener) {
        this.menuOpener = menuOpener;
        this.menuOpener.start();
    }
}
