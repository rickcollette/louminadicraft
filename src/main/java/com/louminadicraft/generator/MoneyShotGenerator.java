package com.louminadicraft.generator;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.block.Biome;

import java.util.Random;

/**
 * Money Shot Biome Terrain Generator
 * Crumbling cityscape of concrete and gilded ruins.
 */
public class MoneyShotGenerator extends ChunkGenerator {

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        ChunkData chunk = createChunkData(world);

        int worldMinHeight = world.getMinHeight();
        int baseHeight = 70;

        boolean buildRuins = random.nextInt(80) == 0; // about 1 ruin every 80 chunks

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {

                int heightNoise = (int) (Math.sin((chunkX * 16 + x) * 0.01) * 5
                                       + Math.cos((chunkZ * 16 + z) * 0.01) * 5
                                       + random.nextInt(4) - 2);

                int surfaceHeight = baseHeight + heightNoise;

                for (int y = worldMinHeight; y <= surfaceHeight; y++) {
                    if (y < surfaceHeight - 4) {
                        chunk.setBlock(x, y, z, Material.STONE);
                    } else if (y < surfaceHeight - 1) {
                        chunk.setBlock(x, y, z, Material.POLISHED_BLACKSTONE);
                    } else {
                        if (random.nextInt(10) < 2) {
                            chunk.setBlock(x, y, z, Material.GILDED_BLACKSTONE);
                        } else {
                            chunk.setBlock(x, y, z, Material.GRAY_CONCRETE_POWDER);
                        }
                    }

                    biome.setBiome(x, y, z, Biome.BADLANDS);
                }

                // If this chunk is selected to build ruins
                if (buildRuins && x % 5 == 0 && z % 5 == 0) {
                    for (int y = surfaceHeight + 1; y < surfaceHeight + random.nextInt(20) + 10; y++) {
                        chunk.setBlock(x, y, z, Material.CRACKED_STONE_BRICKS);
                    }
                }
            }
        }

        return chunk;
    }
}
