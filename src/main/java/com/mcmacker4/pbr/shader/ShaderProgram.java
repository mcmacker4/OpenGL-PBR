package com.mcmacker4.pbr.shader;

import com.mcmacker4.pbr.util.FileUtil;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL20.*;

/**
 * Created by McMacker4 on 20/07/2016.
 */
public abstract class ShaderProgram {

    private int program;

    public ShaderProgram(String name) {
        int program = glCreateProgram();
        int vShader = createShader(GL_VERTEX_SHADER, FileUtil.readFile("/shaders/" + name + ".v.glsl"));
        int fShader = createShader(GL_FRAGMENT_SHADER, FileUtil.readFile("/shaders/" + name + ".f.glsl"));
        glAttachShader(program, vShader);
        glAttachShader(program, fShader);
        glLinkProgram(program);
        if(glGetProgrami(program, GL_LINK_STATUS) != GL_TRUE)
            System.err.println(glGetProgramInfoLog(program));
        getUniformLocations();
        glValidateProgram(program);
        glDeleteShader(vShader);
        glDeleteShader(fShader);
    }

    public abstract void getUniformLocations();

    private int createShader(int type, String source) {
        int shader = glCreateShader(type);
        glShaderSource(shader, source);
        glCompileShader(shader);
        if(glGetShaderi(shader, GL_COMPILE_STATUS) != GL_TRUE)
            System.err.println(glGetShaderInfoLog(shader));
        return shader;
    }

    protected int getUniformLocation(String name) {
        return glGetUniformLocation(program, name);
    }

    protected void loadFloat(int location, float value) {
        glUniform1f(location, value);
    }

    protected void loadVector3f(int location, Vector3f vec) {
        glUniform3f(location, vec.x, vec.y, vec.z);
    }

    protected void loadMatrix4f(int location, Matrix4f matrix) {
        FloatBuffer buffer = MemoryUtil.memAllocFloat(16);
        matrix.get(buffer);
        glUniformMatrix4fv(location, false, buffer);
    }

    public void start() {
        glUseProgram(program);
    }

    public void stop() {
        glUseProgram(0);
    }

    public void delete() {
        glDeleteProgram(program);
    }

}
