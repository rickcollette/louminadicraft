package com.louminadicraft.generator;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import org.bukkit.block.Biome;

import java.util.Random;

/**
 * Conditions of My Parole Biome Terrain Generator
 */
public class LoumBiomeGenerator extends ChunkGenerator {

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        ChunkData chunk = createChunkData(world);

        int worldMinHeight = world.getMinHeight();
        int baseHeight = 64;

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {

                // Surface noise
                int heightNoise = (int) (Math.sin((chunkX * 16 + x) * 0.01) * 4
                                       + Math.cos((chunkZ * 16 + z) * 0.01) * 4
                                       + random.nextInt(3) - 1);

                int surfaceHeight = baseHeight + heightNoise;

                // Craters
                boolean inCrater = random.nextInt(300) == 0;
                if (inCrater) {
                    surfaceHeight -= random.nextInt(6) + 3;
                }

                // Set blocks
                for (int y = worldMinHeight; y <= surfaceHeight; y++) {
                    if (y < surfaceHeight - 3) {
                        chunk.setBlock(x, y, z, Material.STONE);
                    } else if (y < surfaceHeight - 1) {
                        chunk.setBlock(x, y, z, Material.COARSE_DIRT);
                    } else {
                        chunk.setBlock(x, y, z, Material.MOSS_BLOCK);
                    }

                    // Set biome properly 3D
                    biome.setBiome(x, y, z, Biome.DARK_FOREST);
                }

                // Random ruins
                if (random.nextInt(1000) == 0 && !inCrater) {
                    for (int y = surfaceHeight + 1; y < surfaceHeight + 5; y++) {
                        chunk.setBlock(x, y, z, Material.COBBLESTONE_WALL);
                    }
                }
            }
        }

        return chunk;
    }
}
