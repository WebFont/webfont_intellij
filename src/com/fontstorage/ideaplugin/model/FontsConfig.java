package com.fontstorage.ideaplugin.model;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FontsConfig {
    @SerializedName("fonts")
    private List<Font> fonts;

    @SerializedName("urls")
    private UrlsConfig urlsConfig;

    public List<Font> getFonts() {
        return fonts;
    }

    public void setFonts(List<Font> fonts) {
        this.fonts = fonts;
    }

    public UrlsConfig getUrlsConfig() {
        return urlsConfig;
    }

    public void setUrlsConfig(UrlsConfig urlsConfig) {
        this.urlsConfig = urlsConfig;
    }
}
