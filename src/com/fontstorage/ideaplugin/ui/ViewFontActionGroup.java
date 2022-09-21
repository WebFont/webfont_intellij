package com.fontstorage.ideaplugin.ui;

import com.fontstorage.ideaplugin.model.Font;
import com.fontstorage.ideaplugin.model.FontsConfig;
import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Action group for viewing fonts.
 */
public class ViewFontActionGroup extends ActionGroup {

    private final FontsConfig fontsConfig;

    public ViewFontActionGroup(FontsConfig fontsConfig) {
        this.fontsConfig = fontsConfig;
    }

    @NotNull
    @Override
    public AnAction[] getChildren(@Nullable AnActionEvent anActionEvent) {
        List<Font> fonts = fontsConfig.getFonts();
        AnAction[] fontActions = new AnAction[fonts.size()];

        for (int i = 0; i < fonts.size(); i++) {
            fontActions[i] = new FontMenuAction(fonts.get(i), fontsConfig);
        }

        return fontActions;
    }
}