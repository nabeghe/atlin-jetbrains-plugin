package com.herminal.atlin;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.EditorFactoryEvent;
import com.intellij.openapi.editor.event.EditorFactoryListener;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class AtlinEditorFactoryListener implements EditorFactoryListener {

    private final Map<Editor, AtlinEditorHighlighter> highlighters = new HashMap<>();

    @Override
    public void editorCreated(@NotNull EditorFactoryEvent event) {
        Editor editor = event.getEditor();
        VirtualFile file = FileDocumentManager.getInstance()
                .getFile(editor.getDocument());
        if (file != null && "atlin".equals(file.getExtension())) {
            highlighters.put(editor, new AtlinEditorHighlighter(editor));
        }
    }

    @Override
    public void editorReleased(@NotNull EditorFactoryEvent event) {
        Editor editor = event.getEditor();
        AtlinEditorHighlighter h = highlighters.remove(editor);
        if (h != null) h.dispose();
    }
}
