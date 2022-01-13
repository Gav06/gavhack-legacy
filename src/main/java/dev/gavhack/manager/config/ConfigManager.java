package dev.gavhack.manager.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.gavhack.Gavhack;
import dev.gavhack.util.internal.Wrapper;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ConfigManager implements Wrapper {

    private final Gson gson;
    private final JsonParser parser;
    private final File configFolder;
    private final Set<Configurable> configurables;

    public ConfigManager() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        parser = new JsonParser();
        configurables = new HashSet<>();
        configFolder = new File(mc.mcDataDir, "/" + Gavhack.NAME);

        if (!configFolder.exists())
            configFolder.mkdir();
    }

    public void saveAllSafe() {
        try {
            saveAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadAllSafe() {
        try {
            loadAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAll() throws IOException {
        for (Configurable configurable : configurables) {
            final File configFile = new File(configFolder, configurable.getConfigName() + ".json");
            if (!configFile.exists())
                configFile.createNewFile();

            final FileWriter writer = new FileWriter(configFile);
            final JsonObject object = configurable.writeToJsonObject();

            gson.toJson(object, writer);

            writer.flush();
            writer.close();
        }
    }

    public void loadAll() throws IOException {
        for (Configurable configurable : configurables) {
            final File configFile = new File(configFolder, configurable.getConfigName() + ".json");
            if (!configFile.exists())
                continue;

            final FileReader reader = new FileReader(configFile);
            JsonObject object = parser.parse(reader).getAsJsonObject();
            configurable.readFromJsonObject(object);

            reader.close();
        }
    }

    public Set<Configurable> getConfigurables() {
        return configurables;
    }
}
