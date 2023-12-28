package dev.f2a.addon.skriptwebapi.elements.server.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.sun.net.httpserver.HttpExchange;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Get HTTP request header value")
@Description("Retrieves header value from HTTP request")
@Examples("on http request received:\n" +
        "    set {_request} to event-httpexchange\n" +
        "    set {_userAgent} to http request {_request}'s header value from key \"User-Agent\"")
@Since("0.1.0")
public class ExprGetHTTPRequestHeaderValue extends SimpleExpression<String> {

    static {
        Skript.registerExpression(
                ExprGetHTTPRequestHeaderValue.class,
                String.class,
                ExpressionType.SIMPLE,
                "[skeb] (http|api) request %httpexchange%['s] [request] header value from key %string%"
        );
    }


    private Expression<HttpExchange> httpExchange;
    private Expression<String> headerKey;


    @Override
    protected @Nullable String[] get(Event e) {
        String[] retr = new String[1];
        String key = headerKey.getSingle(e);
        HttpExchange exchange = httpExchange.getSingle(e);

        if(exchange == null) {
            Skript.error("Provided HttpExchange instance is null!");
            return null;
        }

        retr[0] = exchange.getRequestHeaders().getFirst(key);
        return retr;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        httpExchange = (Expression<HttpExchange>) exprs[0];
        headerKey = (Expression<String>) exprs[1];
        return true;
    }
}
