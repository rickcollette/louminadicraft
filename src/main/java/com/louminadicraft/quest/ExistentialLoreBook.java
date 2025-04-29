package com.louminadicraft.quest;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import java.util.ArrayList;
import java.util.List;

/**
 * ExistentialLoreBook - Creates multi-page lore books
 */
public class ExistentialLoreBook {

    public static void giveLoreBook(Player player) {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta) book.getItemMeta();

        meta.setTitle("Fragments of Existence");
        meta.setAuthor("Louminadi");

        int fragments = MemoryQuestManager.getInstance().getFragments(player);
        List<String> pages = new ArrayList<>();

        pages.add("§dYou have recovered §b" + fragments + "§d Fractured Memories.\n\nA vast cosmic tragedy unfolds...");
        
        if (fragments >= 5) {
            pages.add("§7You hear echoes of lost civilizations whisper across shattered skies...");
        }
        if (fragments >= 10) {
            pages.add("§6Rift Shrines hum with power... inviting you deeper into the unknown.");
        }
        if (fragments >= 50) {
            pages.add("§4You approach the Prime Rift. Reality fractures. A choice awaits...");
        }

        meta.setPages(pages);
        book.setItemMeta(meta);

        player.getInventory().addItem(book);
        player.sendMessage("§aA §dFragments of Existence §abook has been updated!");
    }
}
