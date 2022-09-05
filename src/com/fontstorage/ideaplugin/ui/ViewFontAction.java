package com.fontstorage.ideaplugin.ui;

import com.fontstorage.ideaplugin.model.Font;
import com.fontstorage.ideaplugin.model.FontsConfig;
import com.intellij.ide.browsers.BrowserLauncher;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Action to viewing font on fontstorage.com.
 */
public class ViewFontAction extends AnAction {

    private final Font font;
    private final FontsConfig fontsConfig;

    public ViewFontAction(Font font, FontsConfig fontsConfig) {
        super(font.getName());
        this.font = font;
        this.fontsConfig = fontsConfig;
    }

    public ViewFontAction(String name, Font font, FontsConfig fontsConfig) {
        super(name);
        this.font = font;
        this.fontsConfig = fontsConfig;
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        try {
            final String fontUrl = font.getSiteFontUrl(fontsConfig.getUrlsConfig());
            BrowserLauncher.getInstance().browse(new URI(fontUrl));
        } catch (URISyntaxException e) {
            NotifyError(e);
        }
    }

    private void NotifyError(Exception e) {
        final Notification newEntryNotification = new Notification("fontstorage", "Error", "Error while unpacking font: " + e.getMessage(), NotificationType.ERROR);
        Notifications.Bus.notify(newEntryNotification);
    }
}
