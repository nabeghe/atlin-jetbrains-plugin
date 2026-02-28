package com.herminal.atlin;

import com.herminal.atlin.settings.AtlinSettings;
import com.intellij.openapi.editor.*;
import com.intellij.openapi.editor.event.*;
import com.intellij.openapi.editor.markup.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AtlinEditorHighlighter implements DocumentListener {

    private static final Color SEPARATOR_BG = new Color(42, 65, 42);

    private final Editor editor;
    private final List<RangeHighlighter> highlighters = new ArrayList<>();

    public AtlinEditorHighlighter(Editor editor) {
        this.editor = editor;
        rehighlight();
        editor.getDocument().addDocumentListener(this);
    }

    @Override
    public void documentChanged(DocumentEvent event) {
        rehighlight();
    }

    public void rehighlight() {
        MarkupModel markup = editor.getMarkupModel();
        for (RangeHighlighter h : highlighters) {
            if (h.isValid()) markup.removeHighlighter(h);
        }
        highlighters.clear();

        Document doc = editor.getDocument();
        String prefix = AtlinSettings.getInstance().getKeyPrefix();
        int lineCount = doc.getLineCount();

        for (int i = 0; i < lineCount; i++) {
            String line = getLine(doc, i);

            // فقط روی خطوط key کار می‌کنیم
            if (!line.trim().startsWith(prefix)) continue;

            // از خط قبل به عقب می‌ریم، کامنت‌ها را نادیده می‌گیریم
            int j = i - 1;
            while (j >= 0 && getLine(doc, j).startsWith("#")) {
                j--; // کامنت است، نادیده بگیر
            }

            // اولین خط غیرکامنت قبل از key
            if (j >= 0 && getLine(doc, j).isEmpty()) {
                // خط خالی پیدا شد => separator
                int start = doc.getLineStartOffset(j);
                int end   = doc.getLineEndOffset(j);
                TextAttributes attrs = new TextAttributes();
                attrs.setBackgroundColor(SEPARATOR_BG);
                RangeHighlighter h = markup.addRangeHighlighter(
                        start, end,
                        HighlighterLayer.ADDITIONAL_SYNTAX,
                        attrs,
                        HighlighterTargetArea.LINES_IN_RANGE
                );
                highlighters.add(h);
            }
        }
    }

    private String getLine(Document doc, int lineIndex) {
        int start = doc.getLineStartOffset(lineIndex);
        int end   = doc.getLineEndOffset(lineIndex);
        return doc.getText().substring(start, end);
    }

    public void dispose() {
        MarkupModel markup = editor.getMarkupModel();
        for (RangeHighlighter h : highlighters) {
            if (h.isValid()) markup.removeHighlighter(h);
        }
        highlighters.clear();
        editor.getDocument().removeDocumentListener(this);
    }
}
