package com.louminadicraft.generator;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.block.Biome;

import java.util.Random;

/**
 * Billy D Terrain Generator
 * Foggy ancient serpent jungle
 */
public class BillyDGenerator extends ChunkGenerator {

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        ChunkData chunk = createChunkData(world);

        int worldMinHeight = world.getMinHeight();
        int baseHeight = 63; // jungle floor height

        boolean buildTemple = random.nextInt(100) == 0; // About 1 temple every 100 chunks

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {

                int heightNoise = (int) (Math.sin((chunkX * 16 + x) * 0.01) * 3
                                       + Math.cos((chunkZ * 16 + z) * 0.01) * 3
                                       + random.nextInt(2));

                int surfaceHeight = baseHeight + heightNoise;

                for (int y = worldMinHeight; y <= surfaceHeight; y++) {
                    if (y < surfaceHeight - 2) {
                        chunk.setBlock(x, y, z, Material.DIRT);
                    } else if (y < surfaceHeight) {
                        chunk.setBlock(x, y, z, Material.MOSS_BLOCK);
                    } else {
                        chunk.setBlock(x, y, z, Material.GRASS_BLOCK);
                    }

                    biome.setBiome(x, y, z, Biome.JUNGLE);
                }

                // Build ancient serpent temple
                if (buildTemple && x % 4 == 0 && z % 4 == 0) {
                    for (int y = surfaceHeight + 1; y <= surfaceHeight + 6; y++) {
                        chunk.setBlock(x, y, z, Material.MOSSY_COBBLESTONE);
                    }
                    chunk.setBlock(x, surfaceHeight + 7, z, Material.COBBLESTONE_WALL);

                    // Bury a secret chest inside
                    if (x == 8 && z == 8) { // Only once per chunk center
                        chunk.setBlock(x, surfaceHeight - 1, z, Material.CHEST);
                    }
                }
            }
        }

        return chunk;
    }
}
