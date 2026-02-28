package com.herminal.atlin;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import javax.swing.*;

public class AtlinFileType extends LanguageFileType {
    public static final AtlinFileType INSTANCE = new AtlinFileType();

    private AtlinFileType() {
        super(AtlinLanguage.INSTANCE);
    }

    @NotNull @Override public String getName() { return "Atlin"; }
    @NotNull @Override public String getDescription() { return "Atlin key-value format"; }
    @NotNull @Override public String getDefaultExtension() { return "atlin"; }
    @Nullable @Override public Icon getIcon() { return null; }
}
