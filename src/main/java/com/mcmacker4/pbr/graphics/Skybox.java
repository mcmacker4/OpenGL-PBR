package com.mcmacker4.pbr.graphics;

import com.mcmacker4.pbr.shader.SkyboxShader;
import org.joml.Matrix4f;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.GL_TEXTURE_CUBE_MAP;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

/**
 * Created by McMacker4 on 23/07/2016.
 */
public class Skybox {

    private static int boxVAO = 0;
    private int textureID;

    private SkyboxShader shader;

    public Skybox(String name, SkyboxShader shader) {
        if(boxVAO == 0)
            loadBox();
        textureID = Texture.loadSkybox("skyboxes/" + name);
        this.shader = shader;
    }

    public void draw(Camera camera) {
        shader.start();
        shader.loadView(camera.getViewMatrix());
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_CUBE_MAP, textureID);
        glDrawArrays(GL_TRIANGLES, 0, cubeCoords.length / 3);
        shader.stop();
    }

    public void setProjection(Matrix4f projection) {
        shader.start();
        shader.loadProjection(projection);
        shader.stop();
    }

    private void loadBox() {
        boxVAO = glGenVertexArrays();
        int vbo = glGenBuffers();
        glBindVertexArray(boxVAO);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        FloatBuffer buffer = MemoryUtil.memAllocFloat(cubeCoords.length);
        buffer.put(cubeCoords); buffer.flip();
        glBufferData(GL_ARRAY_BUFFER, cubeCoords.length, GL_STATIC_DRAW);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    private static final float[] cubeCoords = new float[] {
            -100.0f,-100.0f,-100.0f, // triangle 1 : begin
            -100.0f,-100.0f, 100.0f,
            -100.0f, 100.0f, 100.0f, // triangle 1 : end
            100.0f, 100.0f,-100.0f, // triangle 2 : begin
            -100.0f,-100.0f,-100.0f,
            -100.0f, 100.0f,-100.0f, // triangle 2 : end
            100.0f,-100.0f, 100.0f,
            -100.0f,-100.0f,-100.0f,
            100.0f,-100.0f,-100.0f,
            100.0f, 100.0f,-100.0f,
            100.0f,-100.0f,-100.0f,
            -100.0f,-100.0f,-100.0f,
            -100.0f,-100.0f,-100.0f,
            -100.0f, 100.0f, 100.0f,
            -100.0f, 100.0f,-100.0f,
            100.0f,-100.0f, 100.0f,
            -100.0f,-100.0f, 100.0f,
            -100.0f,-100.0f,-100.0f,
            -100.0f, 100.0f, 100.0f,
            -100.0f,-100.0f, 100.0f,
            100.0f,-100.0f, 100.0f,
            100.0f, 100.0f, 100.0f,
            100.0f,-100.0f,-100.0f,
            100.0f, 100.0f,-100.0f,
            100.0f,-100.0f,-100.0f,
            100.0f, 100.0f, 100.0f,
            100.0f,-100.0f, 100.0f,
            100.0f, 100.0f, 100.0f,
            100.0f, 100.0f,-100.0f,
            -100.0f, 100.0f,-100.0f,
            100.0f, 100.0f, 100.0f,
            -100.0f, 100.0f,-100.0f,
            -100.0f, 100.0f, 100.0f,
            100.0f, 100.0f, 100.0f,
            -100.0f, 100.0f, 100.0f,
            100.0f,-100.0f, 100.0f
    };

}
