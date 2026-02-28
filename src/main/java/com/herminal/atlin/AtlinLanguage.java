package com.herminal.atlin;

import com.intellij.lang.Language;

public class AtlinLanguage extends Language {
    public static final AtlinLanguage INSTANCE = new AtlinLanguage();

    private AtlinLanguage() {
        super("Atlin");
    }
}
