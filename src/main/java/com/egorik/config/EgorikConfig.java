package com.egorik.config;

import com.google.gson.*;
import java.nio.file.*;

public class EgorikConfig {
    public static boolean fpsBooster     = true;
    public static boolean fullbright     = false;
    public static boolean particles      = true;
    public static boolean clearWater     = false;
    public static boolean noFog          = true;
    public static boolean fastLeaves     = true;
    public static boolean entityCulling  = true;
    public static boolean noHurtCam      = true;
    public static boolean noWeather      = false;

    public static int skyMode = 0;
    public static int hitSoundMode = 1;
    public static int hitParticleMode = 1;
    public static boolean rainbowCrosshair = false;

    private static final Path FILE = Paths.get("config/egorik.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void load() {
        try {
            if (Files.exists(FILE)) {
                JsonObject o = JsonParser.parseString(Files.readString(FILE)).getAsJsonObject();
                fpsBooster     = readBool(o, "fpsBooster", true);
                fullbright     = readBool(o, "fullbright", false);
                particles      = readBool(o, "particles", true);
                clearWater     = readBool(o, "clearWater", false);
                noFog          = readBool(o, "noFog", true);
                fastLeaves     = readBool(o, "fastLeaves", true);
                entityCulling  = readBool(o, "entityCulling", true);
                noHurtCam      = readBool(o, "noHurtCam", true);
                noWeather      = readBool(o, "noWeather", false);
                skyMode        = o.has("skyMode") ? o.get("skyMode").getAsInt() : 0;
                hitSoundMode   = o.has("hitSoundMode") ? o.get("hitSoundMode").getAsInt() : 1;
                hitParticleMode= o.has("hitParticleMode") ? o.get("hitParticleMode").getAsInt() : 1;
                rainbowCrosshair = readBool(o, "rainbowCrosshair", false);
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private static boolean readBool(JsonObject o, String k, boolean def) {
        return o.has(k) ? o.get(k).getAsBoolean() : def;
    }

    public static void save() {
        try {
            JsonObject o = new JsonObject();
            o.addProperty("fpsBooster", fpsBooster);
            o.addProperty("fullbright", fullbright);
            o.addProperty("particles", particles);
            o.addProperty("clearWater", clearWater);
            o.addProperty("noFog", noFog);
            o.addProperty("fastLeaves", fastLeaves);
            o.addProperty("entityCulling", entityCulling);
            o.addProperty("noHurtCam", noHurtCam);
            o.addProperty("noWeather", noWeather);
            o.addProperty("skyMode", skyMode);
            o.addProperty("hitSoundMode", hitSoundMode);
            o.addProperty("hitParticleMode", hitParticleMode);
            o.addProperty("rainbowCrosshair", rainbowCrosshair);
            Files.createDirectories(FILE.getParent());
            Files.writeString(FILE, GSON.toJson(o));
        } catch (Exception e) { e.printStackTrace(); }
    }
}
