package com.xlip.threedtemp.Objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.Disposable;
import com.xlip.threedtemp.Objects.Model.DefaultModels;

import java.util.Random;

/**
 * Created by Arif on 13.07.2017.
 */

public class GameObject<T extends GameObject> extends ModelInstance implements Disposable {
    public Random random;
    private Vector3 position;
    private boolean disposed;
    private Vector3 angle;
    public BoundingBox boundingBox;

    public GameObject(Model model, String node) {
        super(model, node);
        position = new Vector3();
        angle = new Vector3();
        setPosition(0,0,0);
        random = new Random();
        onCreate();
    }

    public GameObject(String node) {
        this(DefaultModels.model, node);
    }





    public void onCreate(){


    }


    public T setPosition(float x,float y,float z) {
        transform.setTranslation(x, y, z);
        position.set(x,y,z);
        return (T)this;
    }

    public GameObject setPosition(Vector3 v){
       return setPosition(v.x,v.y,v.z);
    }

    public GameObject setAngle(Vector3 vec){
        return setAngle(vec.x,vec.y,vec.z);
    }

    public T setAngle(float x,float y,float z){
        transform.setFromEulerAngles(x,y,z);
        this.angle = new Vector3(x,y,z);
        return (T)this;
    }


    public GameObject scaleMatrix(float x){
        transform.scl(x);
        return this;

    }


    public T setColor(Color color){
        ((ColorAttribute) materials.get(0).get(ColorAttribute.Diffuse)).color.set(color);

        ColorAttribute spec = ((ColorAttribute) materials.get(0).get(ColorAttribute.Specular));
        if(spec != null)
            spec.color.set(color);

        return (T)this;
    }


    public T setTexture(TextureRegion texture){
        materials.get(0).set(FloatAttribute.createAlphaTest(0.1f));
        TextureAttribute textureAttribute1 = new TextureAttribute(TextureAttribute.Diffuse, texture);
        materials.get(0).set(textureAttribute1);

        return (T)this;
    }

    public void update(float delta) {

    }

    public boolean interceptWith(BoundingBox other) {
        if(boundingBox != null) {
            return boundingBox.intersects(other);
        }
        return false;
    }


    @Override
    public void dispose() {
        disposed = true;
    }


    // GETTER SETTER


    public Vector3 getAngle() {
        return angle;
    }

    public boolean isDisposed() {
        return disposed;
    }

    public Vector3 getPosition() {
        return position;
    }
}
