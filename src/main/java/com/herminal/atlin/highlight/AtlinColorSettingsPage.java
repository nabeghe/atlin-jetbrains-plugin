package com.herminal.atlin.highlight;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import javax.swing.*;
import java.util.Map;

public class AtlinColorSettingsPage implements ColorSettingsPage {

    private static final AttributesDescriptor[] DESCRIPTORS = {
        new AttributesDescriptor("Key",       AtlinSyntaxHighlighter.ATLIN_KEY),
        new AttributesDescriptor("Value",     AtlinSyntaxHighlighter.ATLIN_VALUE),
        new AttributesDescriptor("Separator (blank line)", AtlinSyntaxHighlighter.ATLIN_SEPARATOR),
        new AttributesDescriptor("Comment",   AtlinSyntaxHighlighter.ATLIN_COMMENT),
    };

    @Nullable @Override public Icon getIcon() { return null; }
    @NotNull @Override public SyntaxHighlighter getHighlighter() { return new AtlinSyntaxHighlighter(); }

    @NotNull @Override
    public String getDemoText() {
        return "# This is a comment\n@name\nHadi Akbarzadeh\n\n@bio\nA software developer.\n\n@age\n30\n";
    }

    @Nullable @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() { return null; }
    @NotNull @Override public AttributesDescriptor[] getAttributeDescriptors() { return DESCRIPTORS; }
    @NotNull @Override public ColorDescriptor[] getColorDescriptors() { return ColorDescriptor.EMPTY_ARRAY; }
    @NotNull @Override public String getDisplayName() { return "Atlin"; }
}
