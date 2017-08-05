package com.xlip.threedtemp.World;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;

/**
 * Created by Arif on 13.07.2017.
 */

public class Environment extends com.badlogic.gdx.graphics.g3d.Environment {

    public Environment() {
        super();
        DirectionalLight dlight = new DirectionalLight().set(0.8f, 0.8f,0.8f, 1f, -0.8f, -0.2f);
        DirectionalLight dlight2 = new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, 0.8f, 0.2f);
        set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));

        ColorAttribute fog = new ColorAttribute(ColorAttribute.Fog, Color.SKY);

        set(fog);
        // environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.9f, 0.9f, 0.9f, 1f));

        add(dlight);
        add(dlight2);


    }
}
