package com.fontstorage.ideaplugin.ui;

import com.fontstorage.ideaplugin.model.Font;
import com.fontstorage.ideaplugin.model.FontsConfig;
import com.intellij.ide.browsers.BrowserLauncher;
import com.intellij.openapi.actionSystem.AnActionEvent;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Action to viewing font on fontstorage.com.
 */
public class ViewFontAction extends FontActionBase {

    public ViewFontAction(String name, Font font, FontsConfig fontsConfig) {
        super(name, font,fontsConfig);
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        try {
            final String fontUrl = font.getSiteFontUrl(fontsConfig.getUrlsConfig());
            BrowserLauncher.getInstance().browse(new URI(fontUrl));
        } catch (URISyntaxException e) {
            ShowErrorNotification("opening font", e);
        }
    }
}
