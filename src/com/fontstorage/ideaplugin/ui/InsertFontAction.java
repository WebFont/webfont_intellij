package com.fontstorage.ideaplugin.ui;

import com.fontstorage.ideaplugin.model.Font;
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
 * Created with IntelliJ IDEA.
 * User: xVir
 * Date: 28.01.13
 * Time: 22:06
 * Import font action element
 */
public class InsertFontAction extends AnAction {

    private final Font font;

    public InsertFontAction(Font font) {
        super(font.getName());


        this.font = font;
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        Project project = anActionEvent.getData(PlatformDataKeys.PROJECT);

        Editor editor = anActionEvent.getData(PlatformDataKeys.EDITOR);
        if (editor != null){

            Document currentDocument = editor.getDocument();

            if(ReadonlyStatusHandler.ensureDocumentWritable(project, currentDocument))
            {
                InsertFontImportIntoDocument(currentDocument, font, editor);
            }
        }
    }

    private void InsertFontImportIntoDocument(final Document currentDocument, final Font font,final Editor editor) {
        CommandProcessor.getInstance().executeCommand(null, new Runnable() {
            @Override
            public void run() {

                ApplicationManager.getApplication().runWriteAction(new Runnable() {
                    @Override
                    public void run() {

                        int position = editor.getCaretModel().getOffset();
                        currentDocument.insertString(position, String.format("%s\n%s\n",font.getImp(), font.getComments()));
                    }
                });

            }
        },null,null);
    }
}
