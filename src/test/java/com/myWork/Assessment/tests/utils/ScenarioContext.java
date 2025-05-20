package com.myWork.Assessment.tests.utils;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private static ScenarioContext instance;
    private final Map<ContextKey, Object> contextData;

    private ScenarioContext() {
        contextData = new HashMap<>();
    }

    public static ScenarioContext getInstance() {
        if (instance == null) {
            instance = new ScenarioContext();
        }
        return instance;
    }

    public void set(ContextKey key, Object value) {
        contextData.put(key, value);
    }
    public <T> T get(ContextKey key, Class <T> className) {
        return className.cast(contextData.get(key));
    }

    public boolean contains(ContextKey key) {
        return contextData.containsKey(key);
    }

    public void clear() {
        contextData.clear();
    }
}
