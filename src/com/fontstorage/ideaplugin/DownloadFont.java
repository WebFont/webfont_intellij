package com.fontstorage.ideaplugin;

import com.fontstorage.ideaplugin.model.Font;
import com.fontstorage.ideaplugin.ui.DownloadFontActionGroup;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xVir
 * Date: 01.02.13
 * Time: 21:02
 * Action for downloading font
 */
public class DownloadFont extends FontActionBase {

    @Override
    protected ListPopup createFontsPopup(AnActionEvent anActionEvent, List<Font> fonts) {
        return JBPopupFactory.getInstance().createActionGroupPopup("Fonts",
                new DownloadFontActionGroup(fonts), anActionEvent.getDataContext(),
                JBPopupFactory.ActionSelectionAid.SPEEDSEARCH, true);
    }
}
