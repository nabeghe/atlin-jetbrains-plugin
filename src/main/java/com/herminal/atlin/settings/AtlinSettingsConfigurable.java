package com.herminal.atlin.settings;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;
import javax.swing.*;
import java.awt.*;

public class AtlinSettingsConfigurable implements Configurable {
    private JTextField prefixField;

    @Nls @Override
    public String getDisplayName() { return "Atlin"; }

    @Nullable @Override
    public JComponent createComponent() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel("Key Prefix Character:"));
        prefixField = new JTextField(AtlinSettings.getInstance().getKeyPrefix(), 5);
        panel.add(prefixField);
        return panel;
    }

    @Override
    public boolean isModified() {
        return !prefixField.getText().equals(AtlinSettings.getInstance().getKeyPrefix());
    }

    @Override
    public void apply() {
        AtlinSettings.getInstance().setKeyPrefix(prefixField.getText());
    }

    @Override
    public void reset() {
        prefixField.setText(AtlinSettings.getInstance().getKeyPrefix());
    }
}
