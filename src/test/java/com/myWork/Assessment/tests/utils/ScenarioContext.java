package com.myWork.Assessment.tests.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private static ScenarioContext scenarioContext;
    private final Map<ContextKey, Object> contextData;
    @Getter
    @Setter
    private String scenarioName;

    private ScenarioContext() {
        contextData = new HashMap<>();
    }

    public static ScenarioContext getInstance() {
        if (scenarioContext == null) {
            scenarioContext = new ScenarioContext();
        }
        return scenarioContext;
    }

    public void set(ContextKey key, Object value) {
        contextData.put(key, value);
    }
    public <T> T get(ContextKey key, Class <T> className) {
        return className.cast(contextData.get(key));
    }

    public void clear() {
        contextData.clear();}
    }