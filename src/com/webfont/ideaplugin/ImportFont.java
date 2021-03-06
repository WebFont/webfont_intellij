package com.webfont.ideaplugin;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.webfont.ideaplugin.model.Font;
import com.webfont.ideaplugin.ui.InsertFontActionGroup;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xVir
 * Date: 26.01.13
 * Time: 22:56
 * Import font Action
 */
public class ImportFont extends FontActionBase {

    @Override
    protected ListPopup createFontsPopup(AnActionEvent anActionEvent, List<Font> fonts) {
        return JBPopupFactory.getInstance().createActionGroupPopup("Fonts",
                new InsertFontActionGroup(fonts), anActionEvent.getDataContext(),
                JBPopupFactory.ActionSelectionAid.SPEEDSEARCH, true);
    }

}
