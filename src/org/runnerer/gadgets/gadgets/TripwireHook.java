package org.runnerer.gadgets.gadgets;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.runnerer.gadgets.Gadget;
import org.runnerer.gadgets.GadgetManager;

import java.util.List;

public class TripwireHook extends Gadget
{

    public TripwireHook(GadgetManager gadgetManager)
    {
       super(gadgetManager, "Tripwire Hook", new String[] { ChatColor.GRAY + "Swing right back here!" }, Material.TRIPWIRE_HOOK);

    }

    @Override
    public void customEnable(Player player)
    {
        player.playSound(player.getLocation(), Sound.ITEM_BREAK, 10, 10);
        double radius = 5D;
        List<Entity> near = player.getNearbyEntities(10.0d, 10.0d, 10.0d);
        for(Entity e : near) {
            if(e.getType() == EntityType.PLAYER) {
                player.playSound(player.getLocation(), Sound.LAVA_POP, 10, 10);
            }
            e.setVelocity(new Vector(0, 3, 0));
        }
    }

    @Override
    public void customDisable(Player player)
    {

    }
}
