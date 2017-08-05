package com.xlip.threedtemp.Menu.Effects;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.xlip.threedtemp.Menu.Menu;
import com.xlip.threedtemp.Utils.Lerp;

/**
 * Created by Arif on 23.07.2017.
 */

public class MenuEffect {

        public Matrix4 view;
        public Vector3 viewScale = new Vector3();

        public float interpolation;
        public boolean started;


        public Menu.ScreenCallbacks screenCallbacks;

        public MenuEffect() {

        }

        public MenuEffect(float interpolation) {
           this.interpolation = interpolation;
        }

        public void tick(float delta){


        }

        public void start() {
            started = true;
        }



        public void onFinished(){

        }

//GETTER SETTER


    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public Matrix4 getView() {
        return view;
    }

    public void setView(Matrix4 view) {
        this.view = view;
        setViewScale(view.getScale(new Vector3()));

    }

    public float getInterpolation() {
        return interpolation;
    }

    public void setInterpolation(float interpolation) {
        this.interpolation = interpolation;
    }

    public Vector3 getViewScale() {
        return viewScale;
    }

    public void setViewScale(Vector3 viewScale) {
        this.viewScale = viewScale;
    }

    public Menu.ScreenCallbacks getScreenCallbacks() {
        return screenCallbacks;
    }

    public void setScreenCallbacks(Menu.ScreenCallbacks screenCallbacks) {
        this.screenCallbacks = screenCallbacks;
    }
}
