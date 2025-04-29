package com.louminadicraft.generator;

import org.bukkit.generator.WorldInfo;
import org.bukkit.generator.BiomeProvider;

import java.util.List;


import org.bukkit.block.Biome;

/**
 * Conditions of My Parole Biome
 * Twisted industrial forest: stone, coarse dirt, ruins, mist
 */
public class ConditionsBiome extends BiomeProvider {

    @Override
    public Biome getBiome(WorldInfo worldInfo, int x, int y, int z) {
        return Biome.DARK_FOREST;
    }

    @Override
    public List<Biome> getBiomes(WorldInfo worldInfo) {
        return List.of(Biome.DARK_FOREST);
    }
}
