package com.louminadicraft.generator;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.block.Biome;

import java.util.Random;

/**
 * ExistentialGenerator - Generates floating islands and void gaps.
 */
public class ExistentialGenerator extends ChunkGenerator {

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        ChunkData chunk = createChunkData(world);

        int worldMinHeight = world.getMinHeight();
        int baseHeight = 80;

        boolean generateIsland = random.nextInt(4) == 0; // About 25% of chunks have islands

        if (!generateIsland) {
            return chunk; // Empty void
        }

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int heightNoise = (int) (Math.sin((chunkX * 16 + x) * 0.01) * 3
                                       + Math.cos((chunkZ * 16 + z) * 0.01) * 3
                                       + random.nextInt(2));

                int surfaceHeight = baseHeight + heightNoise;

                for (int y = surfaceHeight - 5; y <= surfaceHeight; y++) {
                    if (y < surfaceHeight) {
                        chunk.setBlock(x, y, z, Material.END_STONE);
                    } else {
                        chunk.setBlock(x, y, z, Material.GRASS_BLOCK);
                    }

                    biome.setBiome(x, y, z, Biome.PLAINS);
                }
            }
        }

        return chunk;
    }
}
