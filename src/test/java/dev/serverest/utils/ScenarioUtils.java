package dev.serverest.utils;

import io.cucumber.java.Scenario;

import java.util.HashMap;
import java.util.Map;

public class ScenarioUtils {
    private final static Map<Long, Scenario> repository = new HashMap<>();

    public static void add(Scenario scenario) {
        if (get() == null)
            repository.put(getId(), scenario);
    }

    public static void remove() {
        if (get() != null)
            repository.remove(getId());
    }

    public static Long getId() {
        return Thread.currentThread().getId();
    }

    public static Scenario get() {
        return repository.get(getId());
    }

    public static void addText(String text) {
        if(get() != null){
            get().log(text);
        }
    }
}
