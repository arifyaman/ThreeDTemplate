package com.xlip.threedtemp.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelBatch;

import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.graphics.g3d.utils.DefaultTextureBinder;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.g3d.utils.TextureBinder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import com.xlip.threedtemp.Assets;
import com.xlip.threedtemp.Input.MyInputProcessor;
import com.xlip.threedtemp.Menu.Menu;
import com.xlip.threedtemp.Objects.GameObject;
import com.xlip.threedtemp.Objects.partials.bubbles.Bubbles;
import com.xlip.threedtemp.Utils.Lerp;

/**
 * Created by Arif on 13.07.2017.
 */

public class World implements MyInputProcessor.MyInputCallback {
    public PerspectiveCamera camera;
    public ModelBatch modelBatch;
    public Array<GameObject> objects;

    public Environment environment;
    Lerp lerp,bx,by;

    GameObject box;
    Bubbles bubbles;

    AnimationController animationController;

    public World() {
        camera=new PerspectiveCamera(67, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.near=1.5f;
        camera.far=100;
        camera.position.set(-15,10,0);
        camera.lookAt(0,0,0);
        camera.update();
        environment = new Environment();


        objects=new Array<GameObject>();

        modelBatch = new ModelBatch();



        box = new GameObject("box").setColor(Color.valueOf("#63CE7A"));

        objects.add(box);


        bubbles = new Bubbles(10,new Vector3(0,3,0),6,Assets.i3,1.3f);


        objects.addAll(bubbles);


        bx = new Lerp(0,1,11);
        by = new Lerp(0,1,11);
            
    }

    public void render(float delta) {
        modelBatch.begin(camera);
        modelBatch.render(objects,environment);
        modelBatch.end();

        for (GameObject g :
                objects) {
            g.update(delta);
        }

        float byv = by.getValue(delta);
        if(byv < 0){
            byv = 0.01f;
            by.go(0.01f);
        }
        float bxv = bx.getValue(delta);
        if(bxv < 0){
            bxv = 0.01f;
            bx.go(0.01f);
        }

            box.transform.setToScaling(byv,1,bxv);

        //box.setPosition(by.getValue(delta),box.getPosition().y, bx.getValue(delta));


        for (GameObject g :
                objects) {
            if(g.isDisposed())
             objects.removeValue(g,false);
        }

    }


    //region Input

    Vector2 down;
    
    
    @Override
    public boolean touchDown(Vector2 cxy) {
        down = new Vector2(cxy);
        //objects.addAll(new Bubbles(10,new Vector3(-5,1,0),6,Assets.i3,1.3f));
        return false;
    }

    @Override
    public boolean touchUp(Vector2 cxy) {

        return false;
    }

    @Override
    public boolean touchDragged(Vector2 cxy) {
        float deg = 20;
        float diffx = (cxy.x - down.x)/deg;
        float diffy = (cxy.y - down.y)/deg;


        //box.setPosition((cxy.y - down.y)/deg,box.getPosition().y,(cxy.x - down.x)/deg);

        bx.addToEnd(diffx);
        by.addToEnd(diffy);


        //objects.addAll(new Bubbles(10,box.getPosition(),6,Assets.i3,3f));
        down = cxy;
        return false;
    }


    //endregion


    public interface ScreenCallbacks {
        Menu getMenu();

    }
}
