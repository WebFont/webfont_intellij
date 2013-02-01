package com.webfont.ideaplugin;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.ListPopup;
import com.webfont.ideaplugin.model.Font;
import com.webfont.ideaplugin.util.FontLoader;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xVir
 * Date: 01.02.13
 * Time: 21:10
 * Base class for plugin commands
 */
public abstract class FontActionBase extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {

        Project project = anActionEvent.getData(PlatformDataKeys.PROJECT);
        Editor editor = anActionEvent.getData(PlatformDataKeys.EDITOR);

        if (editor != null){

            try{
                List<Font> fonts = FontLoader.GetFonts();

                ListPopup actionGroupPopup = createFontsPopup(anActionEvent, fonts);

                actionGroupPopup.showCenteredInCurrentWindow(project);

            }
            catch (IOException e){
                NotifyError();
            }

        }
    }

    protected abstract ListPopup createFontsPopup(AnActionEvent anActionEvent, List<Font> fonts);

    private void NotifyError() {
        final Notification newEntryNotification = new Notification(
                "webfont",
                "Error",
                "Error while downloading fonts list",
                NotificationType.ERROR);

        Notifications.Bus.notify(newEntryNotification);
    }


}
