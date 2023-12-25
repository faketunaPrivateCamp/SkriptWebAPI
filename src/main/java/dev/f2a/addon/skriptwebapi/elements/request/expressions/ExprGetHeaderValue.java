package dev.f2a.addon.skriptwebapi.elements.request.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.google.api.client.http.HttpHeaders;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprGetHeaderValue extends SimpleExpression<String> {

    static {
        Skript.registerExpression(
                ExprGetHeaderValue.class,
                String.class,
                ExpressionType.SIMPLE,
                "[sekb] %httpheader%['s] header value from key %string%"
        );
    }

    private Expression<String> headerProperty;
    private Expression<HttpHeaders> httpHeaders;

    @Override
    protected @Nullable String[] get(Event e) {
        String[] retr = new String[1];
        HttpHeaders headers = httpHeaders.getSingle(e);
        String headerProp = headerProperty.getSingle(e);

        if(headers == null) {
            Skript.error("Provided HttpHeaders instance is null!");
            throw new NullPointerException("Provided HttpHeaders instance is null!");
        }

        retr[0] = headers.getFirstHeaderStringValue(headerProp);

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
        headerProperty = (Expression<String>) exprs[1];
        httpHeaders = (Expression<HttpHeaders>) exprs[0];
        return true;
    }
}
