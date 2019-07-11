package org.runnerer.gadgets.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class InventoryClick implements Listener
{
    @EventHandler(priority= EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();
        Inventory inventory = event.getInventory();

        if (inventory.getName().equals("Gadgets")) { // The inventory is our custom Inventory
            if(clicked.getType() != Material.AIR) {
                player.playSound(player.getLocation(), Sound.ORB_PICKUP, 10, 10);
                player.closeInventory();

                if(clicked.getType() == Material.GOLD_BARDING) {
                    player.getInventory().setItem(5, createItem(Material.GOLD_BARDING, 1, false, "Throwing TNT", null));
                }
                if(clicked.getType() == Material.TRIPWIRE_HOOK) {
                    player.getInventory().setItem(5, createItem(Material.TRIPWIRE_HOOK, 1, false, "Tripwire Hook", null));
                }
                if(clicked.getType() == Material.IRON_BARDING) {
                    player.getInventory().setItem(5, createItem(Material.IRON_BARDING, 1, false, "Paintball Gun", null));
                }
                if(clicked.getType() == Material.RED_ROSE) {
                    player.getInventory().setItem(5, createItem(Material.RED_ROSE, 1, false, "Gift", null));
                }
                if(clicked.getType() == Material.BED) {
                    player.closeInventory();
                }
                event.setCancelled(true);
            } else {
                event.setCancelled(true);
            }
        }
    }

    public static ItemStack createItem(Material material, int amount, boolean isEnchanted, String name, String lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setAmount(amount);

        if (lore != null)
        {
            ArrayList<String> Lore = new ArrayList<String>();
            Lore.add(lore);
            meta.setLore(Lore);
        }
        if(isEnchanted) {
            meta.addEnchant(Enchantment.OXYGEN, 1, true);
        } else {
            //Do Nothing
        }
        item.setItemMeta(meta);

        return item;

    }

}
