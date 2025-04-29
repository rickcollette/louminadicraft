package com.louminadicraft.structure;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.Chunk;

import java.util.Random;

/**
 * RuinedCathedralGenerator - Adds large broken cathedral ruins in MoneyShot
 */
public class RuinedCathedralGenerator extends BlockPopulator {

    private final Random random = new Random();

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        if (!world.getName().toLowerCase().contains("moneyshot")) {
            return;
        }
    
        if (random.nextInt(200) != 0) {
            return;
        }
    
        int startX = chunk.getX() * 16 + 4;
        int startZ = chunk.getZ() * 16 + 4;
        int baseY = 65;
    
        for (int x = -10; x <= 10; x++) {
            for (int z = -20; z <= 20; z++) {
                if (Math.abs(x) == 10 || Math.abs(z) == 20 || (x % 5 == 0 && z % 5 == 0)) {
                    buildBrokenColumn(world, startX + x, baseY, startZ + z);
                }
            }
        }
    
        world.getBlockAt(startX, baseY, startZ).setType(Material.LODESTONE);
    }

    

    private void buildBrokenColumn(World world, int x, int y, int z) {
        int height = 5 + random.nextInt(6); // 5-10 blocks tall
        for (int i = 0; i < height; i++) {
            if (random.nextInt(5) != 0) { // Some missing blocks
                world.getBlockAt(x, y + i, z).setType(Material.CRACKED_STONE_BRICKS);
            }
        }
    }
}
