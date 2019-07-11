package org.runnerer.gadgets.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.runnerer.gadgets.Gadget;

public class GadgetActivateEvent extends Event
{

    private static final HandlerList handlers = new HandlerList();
    private Gadget gadget;

    private boolean cancelled = false;

    public GadgetActivateEvent(Gadget gadget)
    {
        this.gadget = gadget;
    }

    public HandlerList getHandlers()
    {
        return handlers;
    }

    public Gadget getGadget()
    {
        return this.gadget;
    }

    public void setCancelled(boolean cancel)
    {
        cancelled = cancel;
    }

    public boolean isCancelled()
    {
        return cancelled;
    }

}
