package com.mcmacker4.pbr.shader;

import com.mcmacker4.pbr.shader.ShaderProgram;
import org.joml.Matrix4f;

/**
 * Created by McMacker4 on 23/07/2016.
 */
public class SkyboxShader extends ShaderProgram {

    private int LOC_PROJECTION;
    private int LOC_VIEW;

    public SkyboxShader() {
        super("skybox");
    }

    @Override
    public void getUniformLocations() {
        LOC_PROJECTION = getUniformLocation("projection");
        LOC_VIEW = getUniformLocation("view");
    }

    public void loadProjection(Matrix4f projection) {
        loadMatrix4f(LOC_PROJECTION, projection);
    }

    public void loadView(Matrix4f view) {
        loadMatrix4f(LOC_VIEW, view);
    }

}
