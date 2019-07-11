package org.runnerer.gadgets.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class GadgetsMenu
{
    public static Inventory inventory = Bukkit.createInventory(null, 54, "Gadgets");

    static {
        createDisplay(Material.BED, 1, false, inventory, 4, ChatColor.GRAY + "Close", "");
        createDisplay(Material.ENDER_PEARL, 1, true, inventory, 19, ChatColor.GREEN + "" + ChatColor.BOLD + "Gift", ChatColor.WHITE + "Awhh, I love you!");
        createDisplay(Material.FIREWORK, 1, false, inventory, 20, ChatColor.GREEN + "" + ChatColor.BOLD + "Paintball Gun", ChatColor.WHITE + "Pew pew pew!");
        createDisplay(Material.TNT, 1, false, inventory, 21, ChatColor.GREEN + "" + ChatColor.BOLD + "TNT", ChatColor.WHITE + "KABOOM!");
        createDisplay(Material.MELON_BLOCK, 1, false, inventory, 22, ChatColor.GREEN + "" + ChatColor.BOLD + "Tripwire Hook", ChatColor.WHITE + "Pull ya right towards me!");
    }

    public static void createDisplay(Material material, int amount, boolean isEnchanted, Inventory inv, int Slot, String name, String lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setAmount(amount);
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.add(lore);
        meta.setLore(Lore);
        if(isEnchanted) {
            meta.addEnchant(Enchantment.OXYGEN, 1, true);
        } else {
            //Do Nothing
        }
        item.setItemMeta(meta);

        inv.setItem(Slot, item);

    }

    public static void openInventory(Player player) {
        player.openInventory(inventory);
    }
}
