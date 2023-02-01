package jp.faketuna.addon.skriptwebapi.api.server.events;

import jp.faketuna.addon.skriptwebapi.api.server.connection.HttpConnection;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DeleteResponseEvent extends Event {

    public static final HandlerList HANDLERS = new HandlerList();
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    private final HttpConnection connection;

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public DeleteResponseEvent(HttpConnection connection){
        this.connection = connection;
    }

    public HttpConnection getHttpConnection() {
        return this.connection;
    }

}
