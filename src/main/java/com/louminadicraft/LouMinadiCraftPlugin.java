package com.louminadicraft;

import com.louminadicraft.generator.*;
import com.louminadicraft.command.*;
import com.louminadicraft.listener.*;
import com.louminadicraft.quest.MemoryQuestManager;
import com.louminadicraft.mobs.WealthWraithSpawner;
import com.louminadicraft.listener.MemoryShrineSpawner;
import com.louminadicraft.listener.ExistentialMusicListener;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * LouMinadiCraft Main Plugin
 */
public class LouMinadiCraftPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("LouMinadiCraft is powering up!");

        // Commands
        getCommand("warp").setExecutor(new WarpCommand());
        getCommand("biomeinfo").setExecutor(new BiomeInfoCommand());
        getCommand("lorebook").setExecutor(new LoreBookCommand());

        // Biome/Weather/World managers
        new MoneyShotParticleListener(this);
        new MoneyShotSoundListener(this);
        new BillyDJungleSoundListener(this);
        new BillyDFogPatches(this);
        new AcidStormManager(this);
        new AcidRainDamageManager(this);
        new PhantomStormManager(this);
        new WorldBuffManager(this);
        new AmbientThunderListener(this);
        new TempleLootPopulator(this);
        new TempleTrapPopulator(this);
        new TempleBossSpawner(this);
        new GhostFogManager(this);

        // New Memory Quest system
        new MemoryFragmentSpawner(this);
        new MemoryFragmentCollector(this);
        new MemoryShrineSpawner(this);
        new ExistentialMusicListener(this);

        // Mobs
        
        getServer().getPluginManager().registerEvents(new WealthWraithSpawner(this), this);

        getLogger().info("LouMinadiCraft loaded successfully!");
    }

    @Override
    public void onDisable() {
        getLogger().info("LouMinadiCraft is shutting down.");
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        getLogger().info("Providing world generator for: " + worldName);

        if (worldName.toLowerCase().contains("moneyshot")) {
            return new MoneyShotGenerator();
        } else if (worldName.toLowerCase().contains("conditions")) {
            return new LoumBiomeGenerator();
        } else if (worldName.toLowerCase().contains("billyd")) {
            return new BillyDGenerator();
        }

        return new LoumBiomeGenerator();
    }
}
