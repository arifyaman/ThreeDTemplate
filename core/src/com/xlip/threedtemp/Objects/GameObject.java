package com.xlip.threedtemp.Objects;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.model.data.ModelMaterial;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.xlip.threedtemp.Assets;
import com.xlip.threedtemp.Objects.Model.Model;

import java.util.Random;

/**
 * Created by Arif on 13.07.2017.
 */

public class GameObject extends ModelInstance implements Disposable {
    public Random random;
    private Vector3 position;
    private boolean disposed;


    public GameObject(String node) {
        super(Model.model, node);
        position = new Vector3();
        setPosition(0,0,0);
        random = new Random();
        onCreate();

    }

    public void onCreate(){


    }


    public GameObject setPosition(float x,float y,float z) {
        transform.setTranslation(x, y, z);
        position.set(x,y,z);
        return this;
    }

    public GameObject setPosition(Vector3 v){
        setPosition(v.x,v.y,v.z);
        return this;
    }

    public GameObject setAngle(float x,float y,float z){
        transform.setFromEulerAngles(x, y, z);
        return this;
    }
    public GameObject scaleMatrix(float x){
        transform.scl(x);
        return this;

    }


    public GameObject setColor(Color color){
        ((ColorAttribute) materials.get(0).get(ColorAttribute.Diffuse)).color.set(color);

        ColorAttribute spec = ((ColorAttribute) materials.get(0).get(ColorAttribute.Specular));
        if(spec != null)
            spec.color.set(color);

        return this;
    }


    public GameObject setTexture(TextureRegion texture){
        materials.get(0).set(FloatAttribute.createAlphaTest(0.1f));
        TextureAttribute textureAttribute1 = new TextureAttribute(TextureAttribute.Diffuse, texture);
        materials.get(0).set(textureAttribute1);

        return this;
    }



    public void update(float delta){

    }


    @Override
    public void dispose() {
        disposed = true;
    }


    // GETTER SETTER


    public boolean isDisposed() {
        return disposed;
    }

    public Vector3 getPosition() {
        return position;
    }
}
