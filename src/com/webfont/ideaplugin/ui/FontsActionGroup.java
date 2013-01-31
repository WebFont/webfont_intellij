package com.webfont.ideaplugin.ui;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.webfont.ideaplugin.model.Font;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xVir
 * Date: 28.01.13
 * Time: 22:11
 * To change this template use File | Settings | File Templates.
 */
public class FontsActionGroup extends ActionGroup {

    private final List<Font> fonts;

    public FontsActionGroup(List<Font> fonts) {

        this.fonts = fonts;
    }

    @NotNull
    @Override
    public AnAction[] getChildren(@Nullable AnActionEvent anActionEvent) {
        AnAction[] fontActions = new AnAction[fonts.size()];

        for (int i=0;i<fonts.size();i++){
            fontActions[i] = new InsertFontAction(fonts.get(i));
        }

        return fontActions;
    }


}
