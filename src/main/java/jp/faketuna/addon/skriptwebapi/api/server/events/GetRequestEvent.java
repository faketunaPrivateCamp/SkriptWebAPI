package jp.faketuna.addon.skriptwebapi.api.server.events;

import jp.faketuna.addon.skriptwebapi.api.server.objects.Body;
import jp.faketuna.addon.skriptwebapi.api.server.objects.Header;
import jp.faketuna.addon.skriptwebapi.api.server.objects.SenderAddress;
import jp.faketuna.addon.skriptwebapi.api.server.objects.UserAgent;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import javax.xml.ws.spi.http.HttpExchange;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class GetRequestEvent extends Event {

    public static final HandlerList HANDLERS = new HandlerList();
    private final Body body;
    private final Header header;
    private final UserAgent userAgent;
    private final SenderAddress senderAddress;
    private final HttpExchange exchange;


    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public GetRequestEvent(HttpExchange exchange) throws IOException {



        this.exchange = exchange;
        this.body = new Body(new BufferedReader(
                new InputStreamReader(exchange.getRequestBody()))
                .lines()
                .collect(Collectors.joining()));
        this.header = new Header(exchange.getRequestHeaders());
        this.userAgent = new UserAgent(exchange.getRequestHeader("User-Agent"));
        this.senderAddress = new SenderAddress(exchange.getRequestHeader("host"));
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

    public HttpExchange getExchange() {
        return this.exchange;
    }

}
