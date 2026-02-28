package com.herminal.atlin;

import com.herminal.atlin.psi.AtlinTokenTypes;
import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.openapi.editor.markup.SeparatorPlacement;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class AtlinLineSeparatorProvider implements LineMarkerProvider {

    @Nullable
    @Override
    public LineMarkerInfo<?> getLineMarkerInfo(@NotNull PsiElement element) {
        if (element.getNode() == null) return null;

        // فقط روی KEY‌هایی که قبلشان SEPARATOR (خط خالی) هست
        if (!element.getNode().getElementType().equals(AtlinTokenTypes.KEY)) return null;

        PsiElement prev = element.getPrevSibling();
        boolean separatorFound = false;
        while (prev != null) {
            if (prev.getNode() == null) { prev = prev.getPrevSibling(); continue; }
            if (prev.getNode().getElementType().equals(AtlinTokenTypes.SEPARATOR)) {
                separatorFound = true;
                break;
            }
            // اگر به VALUE یا KEY رسیدیم و separator ندیدیم، متوقف شو
            if (prev.getNode().getElementType().equals(AtlinTokenTypes.VALUE) ||
                prev.getNode().getElementType().equals(AtlinTokenTypes.KEY)) {
                break;
            }
            prev = prev.getPrevSibling();
        }

        if (!separatorFound) return null;

        LineMarkerInfo<PsiElement> marker = new LineMarkerInfo<>(
            element,
            element.getTextRange(),
            null,
            null,
            null,
            com.intellij.openapi.editor.markup.GutterIconRenderer.Alignment.LEFT
        );
        marker.separatorColor = new Color(100, 180, 120); // سبز روشن
        marker.separatorPlacement = SeparatorPlacement.TOP;
        return marker;
    }
}
