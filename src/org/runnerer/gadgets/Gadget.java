package org.runnerer.gadgets;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.runnerer.gadgets.events.GadgetActivateEvent;
import org.runnerer.gadgets.gui.GadgetsMenu;

import java.util.HashSet;

public abstract class Gadget
{
    private GadgetManager gadgetManager;
    private String gadgetName;
    private String[] gadgetDescription;
    private Material material;

    public static HashSet<Player> active = new HashSet<Player>();

    public Gadget(GadgetManager gadgetManager, String gadgetName, String[] gadgetDescription, Material material)
    {
        this.gadgetManager = gadgetManager;
        this.gadgetName = gadgetName;
        this.gadgetDescription = gadgetDescription;
        this.material = material;
    }

    public String getGadgetName()
    {
        return gadgetName;
    }

    public String[] getGadgetDescription()
    {
        return gadgetDescription;
    }

    public Material getMaterial()
    {
        return material;
    }

    public HashSet<Player> getActive()
    {
        return active;
    }

    public boolean isActive(Player player)
    {
        return active.contains(player);
    }

    public abstract void customEnable(Player player);
    public abstract void customDisable(Player player);

    public void enable(Player player)
    {
        GadgetActivateEvent gadgetEvent = new GadgetActivateEvent(this);
        Bukkit.getServer().getPluginManager().callEvent(gadgetEvent);

        if (gadgetEvent.isCancelled())
        {
            player.sendMessage(ChatColor.BLUE + "Gadget> " + ChatColor.GRAY + "That gadget isn't enabled!");
            return;
        }

        player.sendMessage(ChatColor.BLUE + "Gadget> " + ChatColor.GRAY + "You are now using that gadget!");
        ItemStack gadget = new ItemStack(material);
        ItemMeta meta = gadget.getItemMeta();
        meta.setDisplayName(getGadgetName());
        gadget.setItemMeta(meta);

        player.getInventory().setItem(5, gadget);
        gadgetManager.setActive(player);
    }

    public void disable(Player player)
    {
        ItemStack gadget = new ItemStack(material);
        ItemMeta meta = gadget.getItemMeta();
        meta.setDisplayName(getGadgetName());
        gadget.setItemMeta(meta);
        player.getInventory().remove(gadget);

        customDisable(player);
        gadgetManager.removeActive(player);
    }

    @EventHandler
    public void quit(PlayerQuitEvent event)
    {
        if (isActive(event.getPlayer()))
        {
            gadgetManager.removeActive(event.getPlayer());
        }
    }

    @EventHandler
    public void interact(PlayerInteractEvent event)
    {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
        {
            if (event.getItem().getType() == material)
            {
                if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(getGadgetName())) customEnable(event.getPlayer());
            } else if (event.getItem().getType() == Material.CHEST)
            {
                if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Gadgets"))
                {
                    GadgetsMenu.openInventory(event.getPlayer());
                }
            }
        }
    }
}
