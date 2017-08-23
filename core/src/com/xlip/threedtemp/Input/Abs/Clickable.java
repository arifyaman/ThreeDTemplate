package com.xlip.threedtemp.Input.Abs;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Arif on 15.07.2017.
 */

public abstract class Clickable {
    private Rectangle wh;
    private boolean onIt = false;
    private boolean disabled = false;
    private boolean preClick = false;

    public Clickable(Vector2 wh, Vector2 position) {
        this.wh = new Rectangle(position.x,position.y,wh.x,wh.y);
    }

    public void touchDown(Vector2 point) {
       if(wh.contains(point) && !disabled) {
           onIt = true;
           preClick = true;
       } else {
           preClick = false;
           onIt = false;
       }

    }


    public void touchUp(Vector2 point){
        if(wh.contains(point) && preClick){
            click();
        }
        preClick = false;
        onIt = false;
    }

    public void touchDraged(Vector2 point){
        onIt = wh.contains(point) && preClick;

    }


    private void click() {

        clicked();
    }


    public abstract void clicked();


    //GETTER SETTER


    public Rectangle getWh() {
        return wh;
    }

    public boolean isOnIt() {
        return onIt;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
