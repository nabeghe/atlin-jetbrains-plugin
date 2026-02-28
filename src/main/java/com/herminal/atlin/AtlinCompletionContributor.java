package com.herminal.atlin;

import com.herminal.atlin.psi.AtlinTokenTypes;
import com.herminal.atlin.settings.AtlinSettings;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiFile;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import java.util.*;

public class AtlinCompletionContributor extends CompletionContributor {

    public AtlinCompletionContributor() {
        extend(CompletionType.BASIC,
            PlatformPatterns.psiElement(AtlinTokenTypes.KEY),
            new CompletionProvider<>() {
                @Override
                protected void addCompletions(@NotNull CompletionParameters parameters,
                                              @NotNull ProcessingContext context,
                                              @NotNull CompletionResultSet result) {
                    String prefix = AtlinSettings.getInstance().getKeyPrefix();
                    // collect existing keys from file
                    PsiFile file = parameters.getOriginalFile();
                    Set<String> seen = new LinkedHashSet<>();
                    for (var child : file.getChildren()) {
                        if (child.getNode() != null &&
                            child.getNode().getElementType() == AtlinTokenTypes.KEY) {
                            String text = child.getText().trim();
                            if (text.startsWith(prefix)) seen.add(text);
                        }
                    }
                    for (String key : seen) {
                        result.addElement(LookupElementBuilder.create(key));
                    }
                }
            });
    }
}
