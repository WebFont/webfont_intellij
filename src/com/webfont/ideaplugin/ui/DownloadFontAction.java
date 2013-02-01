package com.webfont.ideaplugin.ui;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.ReadonlyStatusHandler;
import com.intellij.openapi.vfs.VirtualFile;
import com.webfont.ideaplugin.model.Font;
import com.webfont.ideaplugin.util.FontLoader;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: xVir
 * Date: 01.02.13
 * Time: 21:16
 * Action to download font package into selected folder and insert import to current document
 */
public class DownloadFontAction extends AnAction {

    private final Font font;

    private final String COMMON_IMPORT_PATH = "http://webfonts.ru/import/";

    public DownloadFontAction(Font font) {
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
                FileChooserDescriptor fileChooserDescriptor = new FileChooserDescriptor(false,true,false,false,false,false);
                VirtualFile selectedFolder = FileChooser.chooseFile(fileChooserDescriptor, project, null);

                if (selectedFolder != null && selectedFolder.isDirectory())
                {
                    try{
                        FontLoader.UnpackFontToFolder(selectedFolder, font);
                        InsertFontImportIntoDocument(currentDocument, editor, font, selectedFolder);
                    }
                    catch (IOException e) {
                        NotifyUnpackError(e);
                    }
                }

            }
        }
    }

    private void NotifyUnpackError(IOException e) {
        final Notification newEntryNotification = new Notification(
                "webfont",
                "Error",
                "Error while unpacking font: " + e.getMessage(),
                NotificationType.ERROR);

        Notifications.Bus.notify(newEntryNotification);
    }

    private void InsertFontImportIntoDocument(final Document currentDocument,
                                              final Editor editor,
                                              final Font font,
                                              final VirtualFile fontUnpackFolder) {

        String relativeFontPath = getRelativeFontPath(currentDocument, fontUnpackFolder);
        final String cssPath = font.getImp().replace(COMMON_IMPORT_PATH, relativeFontPath);

        CommandProcessor.getInstance().executeCommand(null, new Runnable() {
            @Override
            public void run() {

                ApplicationManager.getApplication().runWriteAction(new Runnable() {
                    @Override
                    public void run() {

                        int position = editor.getCaretModel().getOffset();
                        currentDocument.insertString(position, String.format("%s\n%s\n",cssPath, font.getComments()));
                    }
                });

            }
        },null,null);
    }

    private String getRelativeFontPath(Document document, VirtualFile folder) {
        VirtualFile documentFile = FileDocumentManager.getInstance().getFile(document);
        if (documentFile != null)
        {
            String documentPath = documentFile.getParent().getPath();
            String folderPath = folder.getPath();
            String relativePath = convertToRelativePath(documentPath, folderPath);
            if (relativePath!=null && !relativePath.isEmpty()){
                relativePath = relativePath + "/";
            }

            return relativePath;
        }

        return "";
    }

    public static String convertToRelativePath(String absolutePath, String relativeTo) {
        StringBuilder relativePath = null;

// Thanks to:
// http://mrpmorris.blogspot.com/2007/05/convert-absolute-path-to-relative-path.html
        absolutePath = absolutePath.replaceAll("\\\\", "/");
        relativeTo = relativeTo.replaceAll("\\\\", "/");

        if (absolutePath.equals(relativeTo) == true) {

        } else {
            String[] absoluteDirectories = absolutePath.split("/");
            String[] relativeDirectories = relativeTo.split("/");

//Get the shortest of the two paths
            int length = absoluteDirectories.length < relativeDirectories.length ?
                    absoluteDirectories.length : relativeDirectories.length;

//Use to determine where in the loop we exited
            int lastCommonRoot = -1;
            int index;

//Find common root
            for (index = 0; index < length; index++) {
                if (absoluteDirectories[index].equals(relativeDirectories[index])) {
                    lastCommonRoot = index;
                } else {
                    break;
//If we didn't find a common prefix then throw
                }
            }
            if (lastCommonRoot != -1) {
//Build up the relative path
                relativePath = new StringBuilder();
//Add on the ..
                for (index = lastCommonRoot + 1; index < absoluteDirectories.length; index++) {
                    if (absoluteDirectories[index].length() > 0) {
                        relativePath.append("../");
                    }
                }
                for (index = lastCommonRoot + 1; index < relativeDirectories.length - 1; index++) {
                    relativePath.append(relativeDirectories[index] + "/");
                }
                relativePath.append(relativeDirectories[relativeDirectories.length - 1]);
            }
        }
        return relativePath == null ? "" : relativePath.toString();
    }
}
