package com.mcmacker4.pbr;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * Created by McMacker4 on 20/07/2016.
 */
public class Display {

    private static long window;
    private static int width, height;

    protected static void create(String title, int width, int height) {

        Display.width = width;
        Display.height = height;

        if(!glfwInit())
            throw new IllegalStateException("Could not initialize GLFW.");

        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        window = glfwCreateWindow(width, height, title, NULL, NULL);

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window,
                (vidmode.width() - width) / 2,
                (vidmode.height() - height) / 2
        );

        glfwSwapInterval(0);

        glfwMakeContextCurrent(window);

        GL.createCapabilities();

        glfwShowWindow(window);

        glClearColor(0.3f, 0.6f, 0.9f, 1.0f);
        glEnable(GL_DEPTH_TEST);

    }

    protected static long getWindow() {
        return window;
    }

    protected static void destroy() {
        glfwDestroyWindow(window);
    }

    protected static boolean shouldClose() {
        return glfwWindowShouldClose(window);
    }

    protected static void swapBuffers() {
        glfwSwapBuffers(window);
    }

}
