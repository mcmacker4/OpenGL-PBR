package com.mcmacker4.pbr.util;

import com.mcmacker4.pbr.shader.ShaderProgram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by McMacker4 on 20/07/2016.
 */
public class FileUtil {

    public static String readFile(String path) {
        String text = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(ShaderProgram.class.getResourceAsStream(path)));
            String line;
            while((line = reader.readLine()) != null) {
                text += line + "\n";
            }
            reader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return text;
    }

}
