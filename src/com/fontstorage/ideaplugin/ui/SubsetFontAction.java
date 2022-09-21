package com.fontstorage.ideaplugin.ui;

import com.fontstorage.ideaplugin.model.Font;
import com.fontstorage.ideaplugin.model.FontsConfig;
import com.intellij.ide.browsers.BrowserLauncher;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Action to subset font on fontstorage.com.
 */
public class SubsetFontAction extends FontActionBase {

    public SubsetFontAction(Font font, FontsConfig fontsConfig) {
        super("Subset", font, fontsConfig);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        try {
            final String fontUrl = font.getSubsettingFontUrl(fontsConfig.getUrlsConfig());
            BrowserLauncher.getInstance().browse(new URI(fontUrl));
        } catch (URISyntaxException e) {
            ShowErrorNotification("font subsetting", e);
        }
    }
}
