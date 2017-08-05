package com.xlip.threedtemp.Menu.Effects.Defaults;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.xlip.threedtemp.Menu.Effects.MenuEffect;
import com.xlip.threedtemp.Menu.Menu;
import com.xlip.threedtemp.Utils.Lerp;

/**
 * Created by Arif on 23.07.2017.
 */

public class DefaultMenuOpener extends MenuEffect {
    Lerp fx,fy;

    public DefaultMenuOpener(float interpolation) {
        super(interpolation);
    }
    

    @Override
    public void setView(Matrix4 view) {
        super.setView(view);
        float xdif = 0.0005f;
        float ydif = 0.0002f;


        fx = new Lerp(viewScale.x, viewScale.x + xdif, interpolation) {
            @Override
            public void onFinished() {
                DefaultMenuOpener.this.onFinished();
            }
        }.combineWith(viewScale.x,interpolation);

        fy = new Lerp(viewScale.y, viewScale.y + ydif, interpolation).combineWith(viewScale.y,interpolation);

    }

    @Override
    public void tick(float delta) {
        view.setToScaling(fx.getValue(delta),fy.getValue(delta),0);

    }
}
