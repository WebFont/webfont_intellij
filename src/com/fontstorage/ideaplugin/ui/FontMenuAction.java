package com.fontstorage.ideaplugin.ui;

import com.fontstorage.ideaplugin.model.Font;
import com.fontstorage.ideaplugin.model.FontsConfig;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import org.jetbrains.annotations.NotNull;

/**
 * Creates a menu for the given font.
 */
public class FontMenuAction extends FontActionBase {
    public FontMenuAction(Font font, FontsConfig fontsConfig) {
        super(font.getName(), font, fontsConfig);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        if (font == null || fontsConfig == null) {
            return;
        }

        Project project = anActionEvent.getData(PlatformDataKeys.PROJECT);
        Editor editor = anActionEvent.getData(PlatformDataKeys.EDITOR);

        if (project != null && editor != null) {
            try {
                ListPopup fontMenuPopup = createFontMenuPopup(anActionEvent);
                fontMenuPopup.showCenteredInCurrentWindow(project);
            } catch (Exception e) {
                ShowErrorNotification("creating fonts popup", e);
            }
        }
    }

    protected ListPopup createFontMenuPopup(AnActionEvent anActionEvent) {
        return JBPopupFactory.getInstance().createActionGroupPopup(font.getName(),
                new FontMenuActionGroup(font, fontsConfig), anActionEvent.getDataContext(),
                JBPopupFactory.ActionSelectionAid.SPEEDSEARCH, true);
    }
}