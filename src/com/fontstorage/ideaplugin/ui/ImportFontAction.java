package com.fontstorage.ideaplugin.ui;

import com.fontstorage.ideaplugin.model.Font;
import com.fontstorage.ideaplugin.model.FontsConfig;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.ReadonlyStatusHandler;

/**
 * Import font action element.
 */
public class ImportFontAction extends AnAction {

    private static final String DOWNLOAD_WARNING = "/* Please do not use this import in production. You can download this font from here %s. */";

    private final Font font;
    private final FontsConfig fontsConfig;

    public ImportFontAction(Font font, FontsConfig fontsConfig) {
        super(font.getName());
        this.font = font;
        this.fontsConfig = fontsConfig;
    }

    public ImportFontAction(String name, Font font, FontsConfig fontsConfig) {
        super(name);
        this.font = font;
        this.fontsConfig = fontsConfig;
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        Project project = anActionEvent.getData(PlatformDataKeys.PROJECT);
        Editor editor = anActionEvent.getData(PlatformDataKeys.EDITOR);
        if (project != null && editor != null){
            Document currentDocument = editor.getDocument();
            if(ReadonlyStatusHandler.ensureDocumentWritable(project, currentDocument))
            {
                InsertFontImportIntoDocument(currentDocument, font, editor);
            }
        }
    }

    private void InsertFontImportIntoDocument(final Document currentDocument, final Font font,final Editor editor) {
        CommandProcessor.getInstance().executeCommand(null, () -> ApplicationManager.getApplication().runWriteAction(() -> {
            int position = editor.getCaretModel().getOffset();
            currentDocument.insertString(position, String.format("%s\n%s\n%s",
                    String.format(DOWNLOAD_WARNING, font.getSiteFontUrl(fontsConfig.getUrlsConfig())),
                    font.getImportFontUrl(fontsConfig.getUrlsConfig()),
                    font.getComments()));
        }),null,null);
    }
}
