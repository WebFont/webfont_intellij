package com.fontstorage.ideaplugin.ui;

import com.fontstorage.ideaplugin.model.Font;
import com.intellij.ide.browsers.BrowserLauncher;
import com.intellij.ide.browsers.WebBrowserService;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.ReadonlyStatusHandler;
import com.intellij.openapi.vfs.VirtualFile;
import com.fontstorage.ideaplugin.util.FontLoader;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created with IntelliJ IDEA.
 * User: xVir
 * Date: 01.02.13
 * Time: 21:16
 * Action to download font package into selected folder and insert import to current document
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
            BrowserLauncher.getInstance().browse(new URI(font.getFontUrl()));
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
