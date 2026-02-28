package com.herminal.atlin.settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(name = "AtlinSettings", storages = @Storage("AtlinSettings.xml"))
public class AtlinSettings implements PersistentStateComponent<AtlinSettings.State> {

    public static class State {
        public String keyPrefix = "@";
    }

    private State myState = new State();

    public static AtlinSettings getInstance() {
        return ApplicationManager.getApplication().getService(AtlinSettings.class);
    }

    @Nullable @Override
    public State getState() { return myState; }

    @Override
    public void loadState(@NotNull State state) { myState = state; }

    public String getKeyPrefix() { return myState.keyPrefix; }
    public void setKeyPrefix(String prefix) { myState.keyPrefix = prefix; }
}
