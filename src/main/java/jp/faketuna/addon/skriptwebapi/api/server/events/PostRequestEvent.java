package jp.faketuna.addon.skriptwebapi.api.server.events;

import com.sun.net.httpserver.HttpExchange;
import jp.faketuna.addon.skriptwebapi.api.server.objects.*;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class PostRequestEvent extends Event {

    public static final HandlerList HANDLERS = new HandlerList();
    private final Body body;
    private final Header header;
    private final UserAgent userAgent;
    private final TargetHost targetHost;
    private final HttpExchange exchange;
    private final ContextPath contextPath;

    private String bodyData;

    public void setBodyData(String body){
        this.bodyData = body;
    }

    public String getBodyData(){
        return bodyData;
    }


    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public PostRequestEvent(HttpExchange exchange){
        this.exchange = exchange;
        this.body = new Body(new BufferedReader(
                new InputStreamReader(exchange.getRequestBody()))
                .lines()
                .collect(Collectors.joining()));
        this.header = new Header(exchange.getRequestHeaders());
        this.userAgent = new UserAgent(header.getHeader().get("User-Agent").get(0));
        this.targetHost = new TargetHost(header.getHeader().get("host").get(0));
        this.contextPath = new ContextPath(exchange.getRequestURI().getPath());
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

    public TargetHost getTargetHost(){
        return this.targetHost;
    }

    public HttpExchange getExchange() {
        return this.exchange;
    }

    public ContextPath getContextPath() {
        return this.contextPath;
    }

}
