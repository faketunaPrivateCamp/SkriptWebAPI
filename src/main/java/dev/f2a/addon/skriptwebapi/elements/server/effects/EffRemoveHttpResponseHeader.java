package dev.f2a.addon.skriptwebapi.elements.server.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.sun.net.httpserver.HttpExchange;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EffRemoveHttpResponseHeader extends Effect {

    static {
        Skript.registerEffect(
                EffRemoveHttpResponseHeader.class,
                "[skeb] remove [the] (http|api) response %httpexchange%['s] header %string%"
        );
    }

    private Expression<HttpExchange> httpExchange;
    private Expression<String>  headerKey;

    @Override
    protected void execute(Event e) {
        HttpExchange exchange = httpExchange.getSingle(e);
        String key = headerKey.getSingle(e);

        if(exchange == null) {
            Skript.error("Provided HttpExchange instance is null!");
            return;
        }

        if(key == null || key.isEmpty()) {
            Skript.error("Header key is should not be blank!");
            return;
        }

        exchange.getResponseHeaders().remove(key);
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
