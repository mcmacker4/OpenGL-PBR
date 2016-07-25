package com.mcmacker4.pbr;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

/**
 * Created by McMacker4 on 20/07/2016.
 */
public class PBR {

    private void init() {

    }

    private void update() {
        Input.update();
    }

    private void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    private void destroy() {

    }

    private void start() {
        Display.create("Physically based rendering.", 1280, 720);
        init();
        while(!Display.shouldClose()) {
            glfwPollEvents();
            update();
            render();
            Display.swapBuffers();
        }
        destroy();
        Display.destroy();
        glfwTerminate();
    }

    public static void main(String[] args) {
        new PBR().start();
    }

}
