package com.fontstorage.ideaplugin;

import com.fontstorage.ideaplugin.model.FontsConfig;
import com.fontstorage.ideaplugin.ui.ImportFontActionGroup;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;

/**
 * Import font Action.
 */
public class ImportFont extends FontsPopupAction {

    @Override
    protected ListPopup createFontsPopup(AnActionEvent anActionEvent, FontsConfig fontsConfig) {
        return JBPopupFactory.getInstance().createActionGroupPopup("Fonts",
                new ImportFontActionGroup(fontsConfig), anActionEvent.getDataContext(),
                JBPopupFactory.ActionSelectionAid.SPEEDSEARCH, true);
    }

}
