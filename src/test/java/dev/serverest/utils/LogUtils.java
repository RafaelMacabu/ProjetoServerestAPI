package dev.serverest.utils;

import dev.serverest.trowables.ValidationException;

public class LogUtils {
    public static void logInfo(String value) {
        System.out.println(value);
        if (!value.contains("{}")) {
            ScenarioUtils.addText(value);
        }
    }

    public static void logError(String value) {
        System.out.println(value);
        if (!value.contains("{}")) {
            ScenarioUtils.addText(value);
            throw new ValidationException(value);
        }
    }
}
