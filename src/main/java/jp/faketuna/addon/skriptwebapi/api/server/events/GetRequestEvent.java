package jp.faketuna.addon.skriptwebapi.api.server.events;

import jp.faketuna.addon.skriptwebapi.api.server.objects.Body;
import jp.faketuna.addon.skriptwebapi.api.server.objects.Header;
import jp.faketuna.addon.skriptwebapi.api.server.objects.SenderAddress;
import jp.faketuna.addon.skriptwebapi.api.server.objects.UserAgent;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GetRequestEvent extends Event {

    public static final HandlerList HANDLERS = new HandlerList();
    private final Body body;
    private final Header header;
    private final UserAgent userAgent;
    private final SenderAddress senderAddress;


    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public GetRequestEvent(String body,
                           String header,
                           String userAgent,
                           String senderAddress){
        this.body = new Body(body);
        this.header = new Header(header);
        this.userAgent = new UserAgent(userAgent);
        this.senderAddress = new SenderAddress(senderAddress);
    }

    public Body getBody(){
        return this.body;
    }

    public Header getHeader(){
        return this.header;
    }

    public UserAgent getUserAgent(){
        return this.userAgent;
    }

    public SenderAddress getSenderAddress(){
        return this.senderAddress;
    }

}
