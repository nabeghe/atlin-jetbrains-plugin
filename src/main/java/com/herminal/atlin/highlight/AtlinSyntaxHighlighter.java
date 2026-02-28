package com.herminal.atlin.highlight;

import com.herminal.atlin.lexer.AtlinLexer;
import com.herminal.atlin.psi.AtlinTokenTypes;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.intellij.lexer.Lexer;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class AtlinSyntaxHighlighter extends SyntaxHighlighterBase {

    public static final TextAttributesKey ATLIN_KEY =
        createTextAttributesKey("ATLIN_KEY", DefaultLanguageHighlighterColors.KEYWORD);

    public static final TextAttributesKey ATLIN_VALUE =
        createTextAttributesKey("ATLIN_VALUE", DefaultLanguageHighlighterColors.STRING);

    public static final TextAttributesKey ATLIN_SEPARATOR =
        createTextAttributesKey("ATLIN_SEPARATOR", DefaultLanguageHighlighterColors.LINE_COMMENT);

    public static final TextAttributesKey ATLIN_COMMENT =
        createTextAttributesKey("ATLIN_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);

    private static final TextAttributesKey[] KEY_KEYS       = {ATLIN_KEY};
    private static final TextAttributesKey[] VALUE_KEYS     = {ATLIN_VALUE};
    private static final TextAttributesKey[] SEPARATOR_KEYS = {ATLIN_SEPARATOR};
    private static final TextAttributesKey[] COMMENT_KEYS   = {ATLIN_COMMENT};
    private static final TextAttributesKey[] EMPTY_KEYS     = {};

    @NotNull @Override
    public Lexer getHighlightingLexer() { return new AtlinLexer(); }

    @NotNull @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(AtlinTokenTypes.KEY))       return KEY_KEYS;
        if (tokenType.equals(AtlinTokenTypes.VALUE))     return VALUE_KEYS;
        if (tokenType.equals(AtlinTokenTypes.SEPARATOR)) return SEPARATOR_KEYS;
        if (tokenType.equals(AtlinTokenTypes.COMMENT))   return COMMENT_KEYS;
        return EMPTY_KEYS;
    }
}
