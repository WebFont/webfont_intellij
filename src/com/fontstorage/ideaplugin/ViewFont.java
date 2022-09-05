package com.fontstorage.ideaplugin;

import com.fontstorage.ideaplugin.model.FontsConfig;
import com.fontstorage.ideaplugin.ui.ViewFontActionGroup;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;

/**
 * Action for viewing the list of fonts from fontstorage.com.
 */
public class ViewFont extends FontsPopupAction {

    @Override
    protected ListPopup createFontsPopup(AnActionEvent anActionEvent, FontsConfig fontsConfig) {
        return JBPopupFactory.getInstance().createActionGroupPopup("Fonts",
                new ViewFontActionGroup(fontsConfig), anActionEvent.getDataContext(),
                JBPopupFactory.ActionSelectionAid.SPEEDSEARCH, true);
    }
}
