package com.mcmacker4.pbr;

import org.joml.Vector2d;
import org.lwjgl.system.MemoryUtil;

import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by McMacker4 on 25/07/2016.
 */
public class Input {

    private static Vector2d
            pos = new Vector2d(), delta = new Vector2d();

    private static DoubleBuffer
            xposbfr = MemoryUtil.memAllocDouble(1),
            yposbfr = MemoryUtil.memAllocDouble(1);

    protected static void update() {
        xposbfr.clear(); yposbfr.clear();
        glfwGetCursorPos(Display.getWindow(), xposbfr, yposbfr);
        double xpos = xposbfr.get(), ypos = yposbfr.get();
        delta.set(xpos - pos.x, ypos - pos.y);
        pos.set(xpos, ypos);
    }

    public static boolean isKeyDown(int key) {
        return glfwGetKey(Display.getWindow(), key) != GLFW_RELEASE;
    }

    public static boolean isMouseBtnDown(int btn) {
        return glfwGetMouseButton(Display.getWindow(), btn) != GLFW_RELEASE;
    }

    public static double getMX() {
        return pos.x;
    }

    public static double getMY() {
        return pos.y;
    }

    public static double getDX() {
        return delta.x;
    }

    public static double getDY() {
        return delta.y;
    }

}
