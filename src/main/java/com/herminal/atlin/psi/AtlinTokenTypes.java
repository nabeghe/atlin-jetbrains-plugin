package com.herminal.atlin.psi;

import com.intellij.psi.tree.IElementType;
import com.herminal.atlin.AtlinLanguage;

public interface AtlinTokenTypes {
    IElementType KEY        = new IElementType("ATLIN_KEY",       AtlinLanguage.INSTANCE);
    IElementType VALUE      = new IElementType("ATLIN_VALUE",     AtlinLanguage.INSTANCE);
    IElementType SEPARATOR  = new IElementType("ATLIN_SEPARATOR", AtlinLanguage.INSTANCE);
    IElementType COMMENT    = new IElementType("ATLIN_COMMENT",   AtlinLanguage.INSTANCE);
    IElementType WHITESPACE = new IElementType("ATLIN_WS",        AtlinLanguage.INSTANCE);
}
