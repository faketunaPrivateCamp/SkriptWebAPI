package dev.f2a.addon.skriptwebapi.internal.events;

import com.google.api.client.http.HttpResponse;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class HttpResponseEvent extends Event {

    public static final HandlerList HANDLERS = new HandlerList();
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    private final HttpResponse httpResponse;

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public HttpResponseEvent(HttpResponse response) {
        this.httpResponse = response;
    }

    public HttpResponse getHttpResponse() {
        return this.httpResponse;
    }
}
