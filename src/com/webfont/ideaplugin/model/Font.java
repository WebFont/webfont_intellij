package com.webfont.ideaplugin.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created with IntelliJ IDEA.
 * User: Danil
 * Date: 26.01.13
 * Time: 23:33
 * Font model
 */
public class Font {

    @SerializedName("import")
    private String imp;

    private String name;
    private String comments;

    @SerializedName("pack_url")
    private String packUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImp() {
        return imp;
    }

    public void setImp(String imp) {
        this.imp = imp;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPackUrl() {
        return packUrl;
    }

    public void setPackUrl(String packUrl) {
        this.packUrl = packUrl;
    }
}
