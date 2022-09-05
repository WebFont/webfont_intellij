package com.fontstorage.ideaplugin.model;

import com.google.gson.annotations.SerializedName;

/**
 * Sample config:
 *    "import_url": "https://fonts.fontstorage.com/import/",
 *    "download_url": "https://webfonts.ru/original/",
 *    "converter_url": "https://fontstorage.com/converter/",
 *    "site_url": "https://fontstorage.com"
 */
public class UrlsConfig {
    @SerializedName("import_url")
    private String importUrl;

    @SerializedName("download_url")
    private String downloadUrl;

    @SerializedName("converter_url")
    private String converterUrl;

    @SerializedName("site_url")
    private String siteUrl;

    public String getImportUrl() {
        return importUrl;
    }

    public void setImportUrl(String importUrl) {
        this.importUrl = importUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getConverterUrl() {
        return converterUrl;
    }

    public void setConverterUrl(String converterUrl) {
        this.converterUrl = converterUrl;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }
}
