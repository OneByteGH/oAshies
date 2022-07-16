package io.github.onebytegh.oashies;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public final class OAshies extends JavaPlugin {
    private UUID playerUUID;
    private final String[] features = {
        "Flying Boots",
        "Blindness Wand",
        "Levitation Sword",
        "KnockBack Stick",
        "1000 Fake TNTs",
        "Fake Void Trap"
    };
    @Override
    public void onEnable() {
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public String[] getFeatures() {
        return features;
    }
    public UUID getPlayerUUID() {
        return playerUUID;
    }
    public void setPlayerUUID(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }
}
