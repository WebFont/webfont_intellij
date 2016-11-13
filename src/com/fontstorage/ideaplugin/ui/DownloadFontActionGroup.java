package com.fontstorage.ideaplugin.ui;

import com.fontstorage.ideaplugin.model.Font;
import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xVir
 * Date: 01.02.13
 * Time: 21:16
 * Action group for download fonts
 */
public class DownloadFontActionGroup extends ActionGroup {

    private final List<Font> fonts;

    public DownloadFontActionGroup(List<Font> fonts) {
        this.fonts = fonts;
    }


    @NotNull
    @Override
    public AnAction[] getChildren(@Nullable AnActionEvent anActionEvent) {
        AnAction[] fontActions = new AnAction[fonts.size()];

        for (int i=0;i<fonts.size();i++){
            fontActions[i] = new DownloadFontAction(fonts.get(i));
        }

        return fontActions;
    }
}
