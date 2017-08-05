package com.xlip.threedtemp.shader;

import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.shaders.BaseShader;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

/**
 * Created by Arif on 16.07.2017.
 */

public class Shader extends BaseShader {

    @Override
    public void init() {

    }

    @Override
    public int compareTo(com.badlogic.gdx.graphics.g3d.Shader other) {
        return 0;
    }

    @Override
    public boolean canRender(Renderable instance) {
        return false;
    }
}
