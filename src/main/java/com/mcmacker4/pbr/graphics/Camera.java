package com.mcmacker4.pbr.graphics;

import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * Created by McMacker4 on 23/07/2016.
 */
public class Camera {

    private Vector3f position, rotation;

    public Camera(Vector3f position) {
        this.position = position;
    }

    public void update() {

    }

    public Matrix4f getViewMatrix() {
        return new Matrix4f()
                .rotateX(-rotation.x)
                .rotateY(-rotation.y)
                .rotateZ(-rotation.z)
                .translate(-position.x, -position.y, -position.z);
    }

}
