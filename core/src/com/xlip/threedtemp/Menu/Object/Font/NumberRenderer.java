package com.xlip.threedtemp.Menu.Object.Font;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.xlip.threedtemp.Menu.Object.MenuObject;


/**
 * Created by Arif on 29.07.2017.
 */

public class NumberRenderer extends MenuObject {
    private Array<FontObject> objects;

    private boolean toRight;
    private int number;
    private String tempNumber;
    private Color color;
    private Array<FontObject> renderingObjects;


    private Texture atlas;
    private int x,y,tw,th;
    private Vector2 position;
    private Vector2 wh;



    public NumberRenderer(Texture texture, int x,int y, int tw,int th, Vector2 position, Vector2 wh) {
        super(null,position,wh);
        this.atlas = texture;
        this.wh = wh;
        this.position = position;
        this.x = x;
        this.y = y;
        this.tw = tw;
        this.th = th;

        objects = new Array<FontObject>();
        renderingObjects = new Array<FontObject>();

        this.toRight = true;
        this.number = 0;
        this.tempNumber ="0";
    }


    @Override
    public void draw(SpriteBatch batch,float delta) {
        String str = String.valueOf(number);
        Color existing = batch.getColor();
        if(color != null)
            batch.setColor(color);

        for (int i = 0; i < str.length(); i++) {
            int a = Character.getNumericValue(str.charAt(i));
            FontObject obj =  new FontObject(new TextureRegion(atlas,x+tw*a,y,tw,th),new Vector2(position).add(wh.x*i,50),wh);
            obj.goToPosition(position.x+wh.x*i,position.y);
            if(objects.size - 1 < i) {
                objects.add(obj);
            }else if(str.charAt(i) != tempNumber.charAt(i)){
                objects.removeIndex(i);
                objects.insert(i,obj);
            }
        }

        for (FontObject f :
                objects) {
            f.draw(batch, delta);
            f.update(delta);
        }

        batch.setColor(existing);
        this.tempNumber = str;
    }



    @Override
    public void update(float delta) {
        super.update(delta);
    }

    //GETTER SETTER


    public boolean isToRight() {
        return toRight;
    }

    public void setToRight(boolean toRight) {
        this.toRight = toRight;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
