package com.herminal.atlin;

import com.herminal.atlin.psi.AtlinTokenTypes;
import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.*;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.*;

public class AtlinFoldingBuilder extends FoldingBuilderEx {

    @NotNull @Override
    public FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quick) {
        List<FoldingDescriptor> descriptors = new ArrayList<>();
        PsiElement child = root.getFirstChild();
        PsiElement valueStart = null;
        int valueStartOffset = -1;
        int valueEndOffset = -1;

        while (child != null) {
            ASTNode node = child.getNode();
            if (node != null) {
                if (node.getElementType() == AtlinTokenTypes.KEY) {
                    if (valueStart != null && valueEndOffset > valueStartOffset) {
                        TextRange range = new TextRange(valueStartOffset, valueEndOffset);
                        if (range.getLength() > 0)
                            descriptors.add(new FoldingDescriptor(valueStart.getNode(), range));
                    }
                    valueStart = null; valueStartOffset = -1; valueEndOffset = -1;
                } else if (node.getElementType() == AtlinTokenTypes.VALUE) {
                    if (valueStart == null) { valueStart = child; valueStartOffset = child.getTextOffset(); }
                    valueEndOffset = child.getTextOffset() + child.getTextLength();
                }
            }
            child = child.getNextSibling();
        }
        if (valueStart != null && valueEndOffset > valueStartOffset) {
            TextRange range = new TextRange(valueStartOffset, valueEndOffset);
            if (range.getLength() > 0)
                descriptors.add(new FoldingDescriptor(valueStart.getNode(), range));
        }
        return descriptors.toArray(FoldingDescriptor.EMPTY);
    }

    @Nullable @Override
    public String getPlaceholderText(@NotNull ASTNode node) { return "..."; }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode node) { return false; }
}
