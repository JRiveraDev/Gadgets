package org.runnerer.gadgets.gadgets;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.runnerer.gadgets.Gadget;
import org.runnerer.gadgets.GadgetManager;

import java.util.List;

public class TNT extends Gadget
{

    public TNT(GadgetManager gadgetManager)
    {
       super(gadgetManager, "Throwing TNT", new String[] { ChatColor.GRAY + "BOOM!" }, Material.TNT);

    }

    @Override
    public void customEnable(Player player)
    {
        final TNTPrimed tnt = player.getWorld().spawn(player.getLocation(), TNTPrimed.class);
        player.playSound(player.getLocation(), Sound.FUSE, 10, 10);
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                player.playSound(player.getLocation(), Sound.EXPLODE, 10, 10);
                double radius = 5D;
                List<Entity> near = player.getNearbyEntities(11.0d, 12.0d, 11.0d);
                for(Entity e : near)
                {
                    e.setVelocity(new Vector(0, 2, 0));
                }
                tnt.remove();
            }
        }.runTaskLater(GadgetManager.getInstance(), 60);
    }

    @Override
    public void customDisable(Player player)
    {

    }
}
