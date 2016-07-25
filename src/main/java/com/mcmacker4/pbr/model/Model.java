package com.mcmacker4.pbr.model;

/**
 * Created by McMacker4 on 20/07/2016.
 */
public class Model {

    private int vao;
    private int vertexCount;

    public Model(int vao, int vertexCount) {
        this.vao = vao;
        this.vertexCount = vertexCount;
    }

    public int getVao() {
        return vao;
    }

    public int getVertexCount() {
        return vertexCount;
    }

}
