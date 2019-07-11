package org.runnerer.gadgets;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.runnerer.gadgets.gadgets.Gift;
import org.runnerer.gadgets.gadgets.PaintballGun;
import org.runnerer.gadgets.gadgets.TNT;
import org.runnerer.gadgets.gadgets.TripwireHook;
import org.runnerer.gadgets.gui.InventoryClick;

import java.util.ArrayList;
import java.util.HashMap;

public class GadgetManager extends JavaPlugin implements Listener
{
    private static GadgetManager instance;
    private ArrayList<Gadget> gadgets = new ArrayList<>();

    @Override
    public void onEnable()
    {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(this, this);
        pluginManager.registerEvents(new InventoryClick(), this);

        makeGadgets();
    }

    public static GadgetManager getInstance()
    {
        return instance;
    }

    public void makeGadgets()
    {
        gadgets.add(new Gift(this));
        gadgets.add(new PaintballGun(this));
        gadgets.add(new TNT(this));
        gadgets.add(new TripwireHook(this));
    }

    public void setActive(Player player) { Gadget.active.add(player); }

    public void removeActive(Player player) { Gadget.active.remove(player); }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        event.getPlayer().getInventory().setItem(4, createItem(Material.CHEST, 1, false, ChatColor.GREEN + "" + "Gadgets", ChatColor.WHITE + "Right-click me to open the gadgets menu"));
    }

    public static ItemStack createItem(Material material, int amount, boolean isEnchanted, String name, String lore)
    {
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

        }
        item.setItemMeta(meta);

        return item;

    }
}
