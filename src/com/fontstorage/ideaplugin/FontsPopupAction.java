package com.fontstorage.ideaplugin;

import com.fontstorage.ideaplugin.model.FontsConfig;
import com.fontstorage.ideaplugin.util.FontLoader;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.ListPopup;

import java.io.IOException;

/**
 * Base class for plugin commands.
 */
public abstract class FontsPopupAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        Project project = anActionEvent.getData(PlatformDataKeys.PROJECT);
        Editor editor = anActionEvent.getData(PlatformDataKeys.EDITOR);

        if (project != null && editor != null) {
            try {
                FontsConfig fontsConfig = FontLoader.LoadFontsConfig();
                ListPopup actionGroupPopup = createFontsPopup(anActionEvent, fontsConfig);
                actionGroupPopup.showCenteredInCurrentWindow(project);
            } catch (IOException e) {
                NotifyError(e);
            }
        }
    }

    protected abstract ListPopup createFontsPopup(AnActionEvent anActionEvent, FontsConfig fontsConfig);

    private void NotifyError(IOException e) {
        NotificationGroupManager.getInstance()
                .getNotificationGroup("fontstorage")
                .createNotification("Error while downloading fonts list: " + e.getMessage(), NotificationType.ERROR)
                .notify();
    }
}
