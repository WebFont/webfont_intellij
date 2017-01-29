package com.fontstorage.ideaplugin.ui;

import com.fontstorage.ideaplugin.model.Font;
import com.intellij.ide.browsers.BrowserLauncher;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created with IntelliJ IDEA.
 * User: xVir
 * Date: 01.02.13
 * Time: 21:16
 * Action to viewing font on fontstorage.com
 */
public class ViewFontAction extends AnAction {

    private final Font font;

    public ViewFontAction(Font font) {
        super(font.getName());
        this.font = font;
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        try {
            final String fontUrl = font.getFontUrl() + "?utm_source=jb";
            BrowserLauncher.getInstance().browse(new URI(fontUrl));
        } catch (URISyntaxException e) {
            NotifyError(e);
        }
    }

    private void NotifyError(Exception e) {
        final Notification newEntryNotification = new Notification(
                "fontstorage",
                "Error",
                "Error while unpacking font: " + e.getMessage(),
                NotificationType.ERROR);

        Notifications.Bus.notify(newEntryNotification);
    }
}
