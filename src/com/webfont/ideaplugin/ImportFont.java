package com.webfont.ideaplugin;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.webfont.ideaplugin.model.Font;
import com.webfont.ideaplugin.ui.FontsActionGroup;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xVir
 * Date: 26.01.13
 * Time: 22:56
 * Import font Action
 */
public class ImportFont extends AnAction {

    private static final String FONTS_URL = "http://webfonts.ru/api/list.json";
    private static final Gson gson = new Gson();

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {

        Project project = anActionEvent.getData(PlatformDataKeys.PROJECT);
        Editor editor = anActionEvent.getData(PlatformDataKeys.EDITOR);

        if (editor != null){

            try{
                List<Font> fonts = GetFonts();

                ListPopup actionGroupPopup = JBPopupFactory.getInstance().createActionGroupPopup("Fonts",
                        new FontsActionGroup(fonts), anActionEvent.getDataContext(),
                        JBPopupFactory.ActionSelectionAid.SPEEDSEARCH, true);

                actionGroupPopup.showCenteredInCurrentWindow(project);

            }
            catch (IOException e){
                 NotifyError();
            }

        }
    }

    private void NotifyError() {
        final Notification newEntryNotification = new Notification(
                "webfont",
                "Error",
                "Error while downloading fonts list",
                NotificationType.ERROR);

        Notifications.Bus.notify(newEntryNotification);
    }

    private List<Font> GetFonts() throws IOException {

        InputStream is = new URL(FONTS_URL).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);

            JsonParser parser = new JsonParser();
            JsonArray array = parser.parse(jsonText).getAsJsonArray();

            List<Font> fonts = new ArrayList<Font>();

            for (int i = 0; i < array.size(); i++){
                fonts.add(gson.fromJson(array.get(i), Font.class));
            }


            return fonts;
        } finally {
            is.close();
        }

    }

}
