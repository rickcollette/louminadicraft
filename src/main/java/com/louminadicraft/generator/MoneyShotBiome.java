package com.louminadicraft.generator;

import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.WorldInfo;
import org.bukkit.block.Biome;

import java.util.List;

/**
 * Money Shot Biome
 * Decayed megacity wasteland with broken riches.
 */
public class MoneyShotBiome extends BiomeProvider {

    @Override
    public Biome getBiome(WorldInfo worldInfo, int x, int y, int z) {
        // We use a hot/dry biome as a visual base
        return Biome.BADLANDS;
    }

    @Override
    public List<Biome> getBiomes(WorldInfo worldInfo) {
        return List.of(Biome.BADLANDS);
    }
}
