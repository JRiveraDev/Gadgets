package org.runnerer.gadgets.gadgets;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.Vector;
import org.runnerer.gadgets.Gadget;
import org.runnerer.gadgets.GadgetManager;
import org.runnerer.gadgets.utils.UtilBlock;

import java.util.List;

public class Gift extends Gadget
{

    public Gift(GadgetManager gadgetManager)
    {
       super(gadgetManager, "Gift", new String[] { ChatColor.GRAY + "Gift your love a rose!" }, Material.RED_ROSE);

    }

    @Override
    public void customEnable(Player player)
    {
        List<Entity> near = player.getNearbyEntities(2.5d, 2.5d, 2.5d);
        for(Entity e : near) {
            if(e.getType() == EntityType.PLAYER)
            {
                e.sendMessage("You received a gift of love from " + ChatColor.GREEN + player.getName() + ChatColor.GRAY + "!");
            }
        }
    }

    @Override
    public void customDisable(Player player)
    {

    }
}
