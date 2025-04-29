package com.louminadicraft.generator;

import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.WorldInfo;
import org.bukkit.block.Biome;

import java.util.List;

/**
 * Billy D Biome Provider
 * Deep jungle base
 */
public class BillyDBiome extends BiomeProvider {

    @Override
    public Biome getBiome(WorldInfo worldInfo, int x, int y, int z) {
        return Biome.JUNGLE;
    }

    @Override
    public List<Biome> getBiomes(WorldInfo worldInfo) {
        return List.of(Biome.JUNGLE);
    }
}
