package com.herminal.atlin;

import com.herminal.atlin.highlight.AtlinSyntaxHighlighter;
import com.herminal.atlin.psi.AtlinTokenTypes;
import com.intellij.lang.annotation.*;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class AtlinBackgroundAnnotator implements Annotator {

    public static final TextAttributesKey ATLIN_SEPARATOR_BG =
            TextAttributesKey.createTextAttributesKey("ATLIN_SEPARATOR_BG");

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element.getNode() == null) return;
        if (!element.getNode().getElementType().equals(AtlinTokenTypes.SEPARATOR)) return;

        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .textAttributes(AtlinSyntaxHighlighter.ATLIN_SEPARATOR)
                .create();
    }
}
