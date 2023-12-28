package dev.f2a.addon.skriptwebapi.elements.server.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.sun.net.httpserver.HttpExchange;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprGetHTTPRequestContextPath extends SimpleExpression<String> {

    static {
        Skript.registerExpression(
                ExprGetHTTPRequestContextPath.class,
                String.class,
                ExpressionType.SIMPLE,
                "[skeb] (http|api) request (context path|context|path|contextpath) of %httpexchange%"
        );
    }


    private Expression<HttpExchange> httpExchange;


    @Override
    protected @Nullable String[] get(Event e) {
        String[] retr = new String[1];
        HttpExchange exchange = httpExchange.getSingle(e);

        if(exchange == null) {
            Skript.error("Provided HttpExchange instance is null!");
            return null;
        }

        retr[0] = exchange.getRequestURI().getPath();
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
        return true;
    }
}
