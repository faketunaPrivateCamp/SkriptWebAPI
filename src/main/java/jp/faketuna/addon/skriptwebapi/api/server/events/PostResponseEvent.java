
package jp.faketuna.addon.skriptwebapi.api.server.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.net.HttpURLConnection;

public class PostResponseEvent extends Event {

    public static final HandlerList HANDLERS = new HandlerList();
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    private final HttpURLConnection connection;

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public PostResponseEvent(HttpURLConnection connection){
        this.connection = connection;
    }

    public HttpURLConnection getHttpURLConnection() {
        return this.connection;
    }

}
