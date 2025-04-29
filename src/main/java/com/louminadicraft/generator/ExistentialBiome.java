package com.louminadicraft.generator;

import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.WorldInfo;
import org.bukkit.block.Biome;

import java.util.List;

/**
 * ExistentialBiome - A surreal existential world.
 */
public class ExistentialBiome extends BiomeProvider {

    @Override
    public Biome getBiome(WorldInfo worldInfo, int x, int y, int z) {
        // Blend plains and void vibes
        return (y > 80) ? Biome.THE_VOID : Biome.PLAINS;
    }

    @Override
    public List<Biome> getBiomes(WorldInfo worldInfo) {
        return List.of(Biome.PLAINS, Biome.THE_VOID);
    }
}
