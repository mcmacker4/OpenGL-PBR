package com.mcmacker4.pbr.model;

import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

/**
 * Created by McMacker4 on 20/07/2016.
 */
public class ModelLoader {

    int vao;
    int vertexCount;

    public ModelLoader() {
        vao = glGenVertexArrays();
    }

    public ModelLoader putArray(int index, int size, float[] data) {
        FloatBuffer buffer = MemoryUtil.memAllocFloat(data.length);
        buffer.put(data);
        buffer.flip();
        return putArray(index, size, buffer);
    }

    public ModelLoader putArray(int index, int size, FloatBuffer data) {
        int vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);
        glVertexAttribPointer(index, size, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(index);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        return this;
    }

    public ModelLoader putIndices(int[] data) {
        IntBuffer buffer = MemoryUtil.memAllocInt(data.length);
        buffer.put(data);
        buffer.flip();
        return putIndices(buffer);
    }

    public ModelLoader putIndices(IntBuffer data) {
        int vbo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vbo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, data, GL_STATIC_DRAW);
        return this;
    }

    public ModelLoader vertexCount(int vertexCount) {
        this.vertexCount = vertexCount;
        return this;
    }

    public Model create() {
        if(vertexCount == 0)
            throw new IllegalStateException("Error creating model, Vertex Count is 0");
        return new Model(vao, vertexCount);
    }

}
