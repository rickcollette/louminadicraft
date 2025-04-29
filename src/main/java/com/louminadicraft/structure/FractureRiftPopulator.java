package com.louminadicraft.structure;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

/**
 * FractureRiftPopulator - Generates reality fractures (rifts) in the Existential Reckoning world.
 */
public class FractureRiftPopulator extends BlockPopulator {

    private final JavaPlugin plugin;
    private final Random random = new Random();

    public FractureRiftPopulator(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        if (!world.getName().toLowerCase().contains("existential")) {
            return;
        }

        if (random.nextInt(150) != 0) { // Very rare (~1 rift every 150 chunks)
            return;
        }

        int centerX = (chunk.getX() << 4) + 8;
        int centerZ = (chunk.getZ() << 4) + 8;
        int centerY = 70;

        // Create a circular fractured hole
        int radius = 4 + random.nextInt(3); // radius 4-6
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                if (x * x + z * z <= radius * radius) {
                    for (int y = centerY; y >= centerY - 8; y--) {
                        Block block = world.getBlockAt(centerX + x, y, centerZ + z);
                        if (random.nextBoolean()) {
                            block.setType(Material.AIR);
                        } else if (y == centerY - 8 && random.nextInt(4) == 0) {
                            block.setType(Material.BEDROCK);
                        }
                    }
                }
            }
        }

        // Place rift core
        world.getBlockAt(centerX, centerY - 8, centerZ).setType(Material.RESPAWN_ANCHOR);

        // Play glitch particle effects
        for (int i = 0; i < 4; i++) {
            world.spawnParticle(
                Particle.PORTAL,
                centerX + random.nextDouble() * 8 - 4,
                centerY - 4 + random.nextDouble() * 4,
                centerZ + random.nextDouble() * 8 - 4,
                30,
                1, 1, 1,
                0.2
            );
        }

        // Play sound effect
        world.playSound(
            chunk.getBlock(8, centerY, 8).getLocation(),
            Sound.BLOCK_BEACON_DEACTIVATE,
            1.0f,
            0.5f + random.nextFloat() * 0.5f
        );
    }
}
