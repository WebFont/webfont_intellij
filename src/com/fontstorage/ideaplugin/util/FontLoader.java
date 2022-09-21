package com.fontstorage.ideaplugin.util;

import com.fontstorage.ideaplugin.model.FontsConfig;
import com.google.gson.Gson;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Utility class to load fonts config.
 */
public class FontLoader {

    private static final String FONTS_URL = "https://fontstorage.com/api/plugins.json";
    private static final Gson gson = new Gson();

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static FontsConfig LoadFontsConfig() throws IOException {
        try (InputStream is = new URL(FONTS_URL).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            FontsConfig fontsConfig = gson.fromJson(jsonText, FontsConfig.class);
            return fontsConfig;
        }
    }
}
