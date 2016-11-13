package com.fontstorage.ideaplugin.util;

import com.fontstorage.ideaplugin.model.Font;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xVir
 * Date: 01.02.13
 * Time: 21:08
 * Utility file for working with Fonts
 */
public class FontLoader {

    private static final String FONTS_URL = "https://webfont.ru/api/list.json2";
    private static final Gson gson = new Gson();

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static List<Font> GetFonts() throws IOException {

        InputStream is = new URL(FONTS_URL).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);

            JsonParser parser = new JsonParser();
            JsonArray array = parser.parse(jsonText).getAsJsonArray();

            List<Font> fonts = new ArrayList<Font>();

            for (int i = 0; i < array.size(); i++){
                fonts.add(gson.fromJson(array.get(i), Font.class));
            }


            return fonts;
        } finally {
            is.close();
        }

    }

    public static void UnpackFontToFolder(VirtualFile folder, Font font) throws IOException {
        URL fontPackURL = new URL(font.getPackUrl());
        File targetFolder = new File(folder.getPath());
        Zip.unpackArchive(fontPackURL,targetFolder);
    }
}
