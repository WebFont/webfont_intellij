package com.fontstorage.ideaplugin.ui;

import com.fontstorage.ideaplugin.model.Font;
import com.fontstorage.ideaplugin.model.FontsConfig;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnAction;
import org.jetbrains.annotations.NotNull;

/**
 * Base class for font actions.
 */
public abstract class FontActionBase extends AnAction {
    protected final Font font;
    protected final FontsConfig fontsConfig;

    protected FontActionBase(@NotNull String name, @NotNull Font font, @NotNull FontsConfig fontsConfig) {
        super(name);
        this.font = font;
        this.fontsConfig = fontsConfig;
    }

    protected void ShowErrorNotification(@NotNull String action, @NotNull Exception e) {
        NotificationGroupManager.getInstance()
                .getNotificationGroup("fontstorage")
                .createNotification(String.format("Exception while %s: %s", action, e.getMessage()), NotificationType.ERROR)
                .notify();
    }
}
