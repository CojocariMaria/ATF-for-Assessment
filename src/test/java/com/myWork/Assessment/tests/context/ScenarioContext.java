package com.myWork.Assessment.tests.context;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton class used for storing and sharing scenario-specific data
 * across different steps during Cucumber test execution.
 * <p>
 * Data is stored using {@link ContextKey} enum keys to ensure type safety.
 */
public class ScenarioContext {

    private static ScenarioContext scenarioContext;
    private final Map<ContextKey, Object> contextData;
    @Getter
    @Setter
    private String scenarioName;
    private ScenarioContext() {
        contextData = new HashMap<>();
    }

    /**
     * Returns the singleton instance of ScenarioContext.
     *
     * @return the singleton {@link ScenarioContext} instance
     */
    public static ScenarioContext getInstance() {
        if (scenarioContext == null) {
            scenarioContext = new ScenarioContext();
        }
        return scenarioContext;
    }

    public void set(ContextKey key, Object value) {
        contextData.put(key, value);
    }
    public <T> T get(ContextKey key, Class<T> className) {
        return className.cast(contextData.get(key));
    }

    public void clear() {
        contextData.clear();
    }
}
