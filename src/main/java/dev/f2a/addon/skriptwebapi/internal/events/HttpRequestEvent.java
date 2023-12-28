package dev.f2a.addon.skriptwebapi.internal.events;

import com.sun.net.httpserver.HttpExchange;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class HttpRequestEvent extends Event {

    public static final HandlerList HANDLERS = new HandlerList();
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    private final HttpExchange httpExchange;

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public HttpRequestEvent(HttpExchange httpExchange) {
        this.httpExchange = httpExchange;
    }

    public HttpExchange getHttpExchange() {
        return this.httpExchange;
    }
}
