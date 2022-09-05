package com.fontstorage.ideaplugin.ui;

import com.fontstorage.ideaplugin.model.Font;
import com.fontstorage.ideaplugin.model.FontsConfig;
import com.intellij.ide.browsers.BrowserLauncher;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Action to subset font on fontstorage.com.
 */
public class SubsetFontAction extends AnAction {
    private final Font font;
    private final FontsConfig fontsConfig;

    public SubsetFontAction(Font font, FontsConfig fontsConfig) {
        super("Subsetting");
        this.font = font;
        this.fontsConfig = fontsConfig;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        try {
            final String fontUrl = font.getSubsettingFontUrl(fontsConfig.getUrlsConfig()) + "?from=jb";
            BrowserLauncher.getInstance().browse(new URI(fontUrl));
        } catch (URISyntaxException e) {
            NotifyError(e);
        }
    }

    private void NotifyError(Exception e) {
        NotificationGroupManager.getInstance()
                .getNotificationGroup("fontstorage")
                .createNotification("Error while opening FontStorage web site: " + e.getMessage(), NotificationType.ERROR)
                .notify();
    }
}
