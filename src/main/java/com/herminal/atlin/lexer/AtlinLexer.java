package com.herminal.atlin.lexer;

import com.herminal.atlin.psi.AtlinTokenTypes;
import com.herminal.atlin.settings.AtlinSettings;
import com.intellij.lexer.LexerBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AtlinLexer extends LexerBase {
    private CharSequence myBuffer;
    private int myStart, myEnd, myBufferEnd;
    private IElementType myTokenType;

    @Override
    public void start(@NotNull CharSequence buffer, int startOffset, int endOffset, int initialState) {
        myBuffer    = buffer;
        myStart     = startOffset;
        myEnd       = startOffset;
        myBufferEnd = endOffset;
        myTokenType = null;
        advance();
    }

    @Override
    public void advance() {
        myStart = myEnd;
        if (myStart >= myBufferEnd) {
            myTokenType = null;
            return;
        }

        // خواندن یک خط کامل (تا \n)
        int pos = myStart;
        while (pos < myBufferEnd && myBuffer.charAt(pos) != '\n') {
            pos++;
        }
        // \n را هم بخور
        if (pos < myBufferEnd) pos++;

        myEnd = pos;

        // محتوای خط بدون \r و \n و فاصله
        String raw = myBuffer.subSequence(myStart, myEnd).toString();
        String trimmed = raw.replace("\r", "").replace("\n", "").trim();

        String prefix = AtlinSettings.getInstance().getKeyPrefix();

        if (trimmed.isEmpty()) {
            myTokenType = AtlinTokenTypes.SEPARATOR;
        } else if (trimmed.startsWith("#")) {
            myTokenType = AtlinTokenTypes.COMMENT;
        } else if (trimmed.startsWith(prefix)) {
            myTokenType = AtlinTokenTypes.KEY;
        } else {
            myTokenType = AtlinTokenTypes.VALUE;
        }
    }

    @Override public int getState() { return 0; }
    @Nullable @Override public IElementType getTokenType() { return myTokenType; }
    @Override public int getTokenStart() { return myStart; }
    @Override public int getTokenEnd() { return myEnd; }
    @NotNull @Override public CharSequence getBufferSequence() { return myBuffer; }
    @Override public int getBufferEnd() { return myBufferEnd; }
}
