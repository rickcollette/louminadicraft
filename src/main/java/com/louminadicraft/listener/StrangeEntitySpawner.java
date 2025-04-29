package com.louminadicraft.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

/**
 * StrangeEntitySpawner - Rare hidden bosses near existential rifts
 */
public class StrangeEntitySpawner implements Listener {

    private final JavaPlugin plugin;
    private final Random random = new Random();

    public StrangeEntitySpawner(JavaPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
        if (!event.isNewChunk()) {
            return;
        }

        World world = event.getWorld();
        if (!world.getName().toLowerCase().contains("existential")) {
            return;
        }

        if (random.nextInt(300) != 0) { // Extremely rare
            return;
        }

        int spawnX = (event.getChunk().getX() << 4) + random.nextInt(16);
        int spawnZ = (event.getChunk().getZ() << 4) + random.nextInt(16);
        int spawnY = 70;

        Location loc = new Location(world, spawnX, spawnY, spawnZ);

        ArmorStand entity = (ArmorStand) world.spawnEntity(loc, EntityType.ARMOR_STAND);
        entity.setInvisible(true);
        entity.setGravity(false);
        entity.setCustomName("§5≡ Rift Echo ≡");
        entity.setCustomNameVisible(true);
        entity.setGlowing(true);

        entity.getEquipment().setHelmet(new org.bukkit.inventory.ItemStack(Material.AMETHYST_BLOCK));

        // Particle + sound trail
        new BukkitRunnable() {
            @Override
            public void run() {
                if (entity.isDead() || !entity.isValid()) {
                    cancel();
                    return;
                }

                world.spawnParticle(Particle.PORTAL, entity.getLocation(), 30, 0.5, 0.5, 0.5, 0.05);
                world.playSound(entity.getLocation(), org.bukkit.Sound.ENTITY_ENDERMAN_AMBIENT, 0.5f, 1.2f);
            }
        }.runTaskTimer(plugin, 0L, 100L); // Every 5 seconds
    }
}
