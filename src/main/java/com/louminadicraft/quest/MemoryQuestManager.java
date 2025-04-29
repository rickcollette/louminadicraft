package com.louminadicraft.quest;

import org.bukkit.entity.Player;
import com.louminadicraft.quest.ExistentialLoreBook;

import java.util.HashMap;
import java.util.UUID;

/**
 * MemoryQuestManager - Tracks player Memory Fragment collection
 */
public class MemoryQuestManager {

    private static MemoryQuestManager instance;
    private final HashMap<UUID, Integer> memoryCounts = new HashMap<>();

    private MemoryQuestManager() {}

    public static MemoryQuestManager getInstance() {
        if (instance == null) {
            instance = new MemoryQuestManager();
        }
        return instance;
    }

    public void addMemoryFragment(Player player) {
        UUID id = player.getUniqueId();
        int current = memoryCounts.getOrDefault(id, 0);
        int newTotal = current + 1;
        memoryCounts.put(id, newTotal);

        player.sendMessage("§b[Memory Quest] Fragments collected: §d" + newTotal);

        if (newTotal == 5) {
            player.sendMessage("§6⚡ A faint rift pulse echoes across the world...");
            ExistentialLoreBook.giveLoreBook(player);
        } else if (newTotal == 10) {
            player.sendMessage("§5⚡ A Major Rift Shrine has appeared!");
            ExistentialLoreBook.giveLoreBook(player);
        } else if (newTotal == 50) {
            player.sendMessage("§c⚡ You have gathered enough memories to stabilize the Prime Rift!");
            ExistentialLoreBook.giveLoreBook(player);
        }
    }

    public int getFragments(Player player) {
        return memoryCounts.getOrDefault(player.getUniqueId(), 0);
    }
}
