package com.xlip.threedtemp.Screen;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.xlip.threedtemp.Assets;
import com.xlip.threedtemp.Menu.Menu;
import com.xlip.threedtemp.Menu.Object.MenuObject;
import com.xlip.threedtemp.Settings.Settings;

/**
 * Created by Arif on 6.08.2017.
 */

public class SplashScreen extends Screen {
    MainCallBacks mainCallBacks;

    public SplashScreen(float delaySeconds, final MainCallBacks mainCallBacks) {
        super(null, null);
        Menu imageAsset = new Menu();
        MenuObject splash = new MenuObject(new TextureRegion(Assets.splash),new Vector2(-Settings.orto_width/2,-Settings.orto_height/2),new Vector2(Settings.orto_width,Settings.orto_height));
        imageAsset.addMenuObject(splash);

        setMenu(imageAsset);

        new Timer().scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                mainCallBacks.onSplashScreenFinished();
            }
        },delaySeconds);



    }


    public interface MainCallBacks{
        void onSplashScreenFinished();

    }

}
