package com.fontstorage.ideaplugin.ui;

import com.fontstorage.ideaplugin.model.Font;
import com.fontstorage.ideaplugin.model.FontsConfig;
import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class FontMenuActionGroup extends ActionGroup {

    private final Font font;
    private final FontsConfig fontsConfig;

    public FontMenuActionGroup(Font font, FontsConfig fontsConfig) {
        this.font = font;
        this.fontsConfig = fontsConfig;
    }

    @Override
    @NotNull
    public AnAction[] getChildren(@Nullable AnActionEvent anActionEvent) {
        var fontActions = new ArrayList<AnAction>();
        fontActions.add(new ImportFontAction("Import", font, fontsConfig));
        fontActions.add(new DownloadFontAction("Download", font, fontsConfig));
        fontActions.add(new ViewFontAction("View", font, fontsConfig));
        fontActions.add(new SubsetFontAction(font, fontsConfig));
        return fontActions.toArray(new AnAction[0]);
    }
}
