package com.herminal.atlin;

import com.herminal.atlin.psi.AtlinTokenTypes;
import com.herminal.atlin.settings.AtlinSettings;
import com.intellij.lang.annotation.*;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class AtlinAnnotator implements Annotator {

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element.getNode() == null) return;
        if (!element.getNode().getElementType().equals(AtlinTokenTypes.KEY)) return;

        String prefix = AtlinSettings.getInstance().getKeyPrefix();
        String text = element.getText().trim();
        if (!text.startsWith(prefix)) {
            holder.newAnnotation(HighlightSeverity.WARNING,
                "Key should start with '" + prefix + "'")
                .range(element.getTextRange())
                .create();
        }
        if (text.equals(prefix) || text.equals(prefix + " ")) {
            holder.newAnnotation(HighlightSeverity.ERROR,
                "Key name cannot be empty")
                .range(element.getTextRange())
                .create();
        }
    }
}
