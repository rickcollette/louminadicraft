package com.louminadicraft.command;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * RiftUpgradeCommand - Upgrades Rift Amulet tiers
 */
public class RiftUpgradeCommand implements CommandExecutor {

    private final JavaPlugin plugin;

    public RiftUpgradeCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Players only.");
            return true;
        }

        Player player = (Player) sender;
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item.getType() != Material.HEART_OF_THE_SEA) {
            player.sendMessage("§cHold your Rift Amulet to upgrade.");
            return true;
        }

        ItemMeta meta = item.getItemMeta();
        if (meta == null || !meta.getPersistentDataContainer().has(new NamespacedKey(plugin, "rift_amulet"), PersistentDataType.INTEGER)) {
            player.sendMessage("§cThat's not a Rift Amulet.");
            return true;
        }

        int essenceCount = countEssence(player);

        if (essenceCount < 10) {
            player.sendMessage("§eYou need 10 Essence Shards to upgrade.");
            return true;
        }

        removeEssence(player, 10);

        // Upgrade logic
        meta.setDisplayName("§5Rift Amulet §7[Upgraded]");
        item.setItemMeta(meta);

        player.sendMessage("§a✅ Your Rift Amulet has been upgraded!");
        return true;
    }

    private int countEssence(Player player) {
        int total = 0;
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.getType() == Material.AMETHYST_SHARD) {
                total += item.getAmount();
            }
        }
        return total;
    }

    private void removeEssence(Player player, int amount) {
        int remaining = amount;
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.getType() == Material.AMETHYST_SHARD) {
                int take = Math.min(item.getAmount(), remaining);
                item.setAmount(item.getAmount() - take);
                remaining -= take;
                if (remaining <= 0) break;
            }
        }
    }
}
