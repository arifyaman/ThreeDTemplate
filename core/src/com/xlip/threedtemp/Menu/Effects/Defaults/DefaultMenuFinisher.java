package com.xlip.threedtemp.Menu.Effects.Defaults;

import com.badlogic.gdx.math.Matrix4;
import com.xlip.threedtemp.Utils.Lerp;
import com.xlip.threedtemp.Menu.Effects.MenuEffect;

/**
 * Created by Arif on 23.07.2017.
 */

public class DefaultMenuFinisher extends MenuEffect {
    Lerp fx,fy;

    public DefaultMenuFinisher(float interpolation) {
        super(interpolation);
    }


    @Override
    public void setView(Matrix4 view) {
        super.setView(view);
        float xdif = 0.00004f;
        float ydif = 0.000042f;


        fx = new Lerp(viewScale.x, viewScale.x + xdif, interpolation) {
            @Override
            public void onFinished() {
                DefaultMenuFinisher.this.onFinished();
            }
        }.combineWith(0,interpolation);

        fy = new Lerp(viewScale.y, viewScale.y + ydif, interpolation).combineWith(0,interpolation);

    }

    @Override
    public void onFinished() {
        super.onFinished();
        screenCallbacks.disposeCurrentMenu();
    }

    @Override
    public void tick(float delta) {
        view.setToScaling(fx.getValue(delta),fy.getValue(delta),0);
        super.tick(delta);


    }

}
