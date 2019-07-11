package org.runnerer.gadgets.gadgets;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.Vector;
import org.runnerer.gadgets.Gadget;
import org.runnerer.gadgets.GadgetManager;
import org.runnerer.gadgets.utils.UtilBlock;

import java.util.List;

public class PaintballGun extends Gadget
{

    public PaintballGun(GadgetManager gadgetManager)
    {
       super(gadgetManager, "Paintball Gun", new String[] { ChatColor.GRAY + "PAINT PAINT PAINT!" }, Material.IRON_BARDING);

    }

    @Override
    public void customEnable(Player player)
    {
        Projectile proj = player.launchProjectile(Snowball.class);
        proj.setVelocity(proj.getVelocity().multiply(2));
    }

    @Override
    public void customDisable(Player player)
    {

    }

    @EventHandler
    public void paintEffect(ProjectileHitEvent event)
    {
        double random = Math.random() * 2 + 1;

        byte color = 0;

        if (random == 1)
        {
            color = 3;
        } else
        {
            color = 8;
        }

        Location loc = event.getEntity().getLocation().add(event.getEntity().getVelocity());

        for (Block block : UtilBlock.getNearbyBlocks(loc, 2))
        {
            if (block.getType() != Material.WOOL && block.getType() != Material.STAINED_CLAY)
                continue;

            block.setData(color);
        }
    }
}
