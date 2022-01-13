package dev.gavhack.manager.config;

import com.google.gson.JsonObject;

public interface Configurable {

    String getConfigName();

    JsonObject writeToJsonObject();

    void readFromJsonObject(JsonObject jsonObject);
}
