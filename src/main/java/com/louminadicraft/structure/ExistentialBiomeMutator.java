package com.louminadicraft.structure;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.Chunk;

import java.util.Random;

/**
 * ExistentialBiomeMutator - Random glitches in the existential world terrain
 */
public class ExistentialBiomeMutator extends BlockPopulator {

    private final Random random = new Random();

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        if (!world.getName().toLowerCase().contains("existential")) {
            return;
        }

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = world.getMinHeight(); y < world.getMaxHeight(); y++) {
                    var block = chunk.getBlock(x, y, z);

                    if (block.getType() == Material.GRASS_BLOCK) {
                        if (random.nextInt(40) == 0) {
                            block.setType(Material.PURPLE_CONCRETE);
                        }
                    }

                    if (block.getType() == Material.STONE) {
                        if (random.nextInt(100) == 0) {
                            block.setType(Material.OBSIDIAN);
                        }
                    }

                    if (block.getType() == Material.COARSE_DIRT) {
                        if (random.nextInt(50) == 0) {
                            block.setType(Material.AMETHYST_BLOCK);
                        }
                    }
                }
            }
        }
    }
}
