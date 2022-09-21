package com.fontstorage.ideaplugin.model;

import com.google.gson.annotations.SerializedName;

/**
 * Single font config.
 * Sample:
 * "font_slug": "20db",
 * "slug": "20db",
 * "comments": "\n\t20 db, 400\n",
 * "name":"20 db"
 */
public class Font {

    @SerializedName("font_slug")
    private String fontSlug;

    @SerializedName("slug")
    private String slug;

    @SerializedName("comments")
    private String comments;

    @SerializedName("name")
    private String name;

    public String getFontSlug() {
        return fontSlug;
    }

    public void setFontSlug(String fontSlug) {
        this.fontSlug = fontSlug;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSiteFontUrl(UrlsConfig urlsConfig) {
        return urlsConfig.getSiteUrl() + "/font/" + fontSlug + "?from=jb";
    }

    public String getDownloadFontUrl(UrlsConfig urlsConfig) {
        return urlsConfig.getDownloadUrl() + slug + "/" + slug + ".zip";
    }

    public String getImportFontUrl(UrlsConfig urlsConfig) {
        return "@import \"" + urlsConfig.getImportUrl() + slug + ".css\";";
    }

    public String getSubsettingFontUrl(UrlsConfig urlsConfig) {
        return urlsConfig.getConverterUrl() + "#" + fontSlug;
    }
}
