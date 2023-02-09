package net.faketuna.addon.skriptwebapi.api.server.events;

import com.sun.net.httpserver.HttpExchange;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PostRequestEvent extends Event {

    public static final HandlerList HANDLERS = new HandlerList();
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    private final HttpExchange exchange;

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public PostRequestEvent(HttpExchange exchange){
        this.exchange = exchange;
    }

    public HttpExchange getExchange() {
        return this.exchange;
    }

}
