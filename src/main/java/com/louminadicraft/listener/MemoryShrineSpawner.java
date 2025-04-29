package com.louminadicraft.listener;

import com.louminadicraft.quest.MemoryQuestManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.Location;

import java.util.Random;

/**
 * Spawns Memory Shrines after enough fragments collected
 */
public class MemoryShrineSpawner {

    private final JavaPlugin plugin;
    private final Random random = new Random();

    public MemoryShrineSpawner(JavaPlugin plugin) {
        this.plugin = plugin;
        startShrineCheck();
    }

    private void startShrineCheck() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (var player : Bukkit.getOnlinePlayers()) {
                    World world = player.getWorld();

                    if (!world.getName().toLowerCase().contains("existential")) {
                        continue;
                    }

                    int fragments = MemoryQuestManager.getInstance().getFragments(player);

                    if (fragments >= 5 && random.nextInt(100) == 0) {
                        spawnSmallShrine(world, player.getLocation());
                    } else if (fragments >= 10 && random.nextInt(200) == 0) {
                        spawnMajorShrine(world, player.getLocation());
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 600L); // Check every 30 seconds
    }

    private void spawnSmallShrine(World world, Location baseLoc) {
        int baseX = baseLoc.getBlockX() + random.nextInt(50) - 25;
        int baseZ = baseLoc.getBlockZ() + random.nextInt(50) - 25;
        int baseY = world.getHighestBlockYAt(baseX, baseZ);

        Block center = world.getBlockAt(baseX, baseY, baseZ);
        center.setType(Material.END_STONE);

        world.getBlockAt(baseX, baseY + 1, baseZ).setType(Material.AMETHYST_BLOCK);
        world.getBlockAt(baseX, baseY + 2, baseZ).setType(Material.CANDLE);
    }

    private void spawnMajorShrine(World world, Location baseLoc) {
        int baseX = baseLoc.getBlockX() + random.nextInt(80) - 40;
        int baseZ = baseLoc.getBlockZ() + random.nextInt(80) - 40;
        int baseY = world.getHighestBlockYAt(baseX, baseZ);

        Block center = world.getBlockAt(baseX, baseY, baseZ);
        center.setType(Material.OBSIDIAN);

        world.getBlockAt(baseX, baseY + 1, baseZ).setType(Material.RESPAWN_ANCHOR);
        world.getBlockAt(baseX, baseY + 2, baseZ).setType(Material.CANDLE);
    }
}
