package com.herminal.atlin;

import com.herminal.atlin.highlight.AtlinSyntaxHighlighter;
import com.herminal.atlin.psi.AtlinTokenTypes;
import com.intellij.lang.annotation.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class AtlinSeparatorAnnotator implements Annotator {

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element.getNode() == null) return;
        IElementType type = element.getNode().getElementType();

        if (type.equals(AtlinTokenTypes.SEPARATOR)) {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .textAttributes(AtlinSyntaxHighlighter.ATLIN_SEPARATOR)
                    .create();
        }
    }
}
