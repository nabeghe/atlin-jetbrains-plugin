package com.herminal.atlin.psi;

import com.intellij.psi.tree.IElementType;
import com.herminal.atlin.AtlinLanguage;

public interface AtlinElementTypes {
    IElementType ENTRY = new IElementType("ATLIN_ENTRY", AtlinLanguage.INSTANCE);
    IElementType FILE  = new IElementType("ATLIN_FILE",  AtlinLanguage.INSTANCE);
}
