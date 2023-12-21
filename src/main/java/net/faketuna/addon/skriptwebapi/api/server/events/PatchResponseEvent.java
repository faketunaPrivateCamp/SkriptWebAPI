package net.faketuna.addon.skriptwebapi.api.server.events;

import net.faketuna.addon.skriptwebapi.api.server.connection.HttpConnection;
import net.faketuna.addon.skriptwebapi.api.server.connection.HttpConnection;
import net.faketuna.addon.skriptwebapi.api.server.connection.HttpConnection;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PatchResponseEvent extends Event {

    public static final HandlerList HANDLERS = new HandlerList();
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    private final HttpConnection connection;

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public PatchResponseEvent(HttpConnection connection){
        this.connection = connection;
    }

    public HttpConnection getHttpConnection() {
        return this.connection;
    }

}
