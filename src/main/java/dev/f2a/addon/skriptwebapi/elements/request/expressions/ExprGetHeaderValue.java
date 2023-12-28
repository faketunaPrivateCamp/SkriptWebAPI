package dev.f2a.addon.skriptwebapi.elements.request.expressions;

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
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Get header value")
@Description("Get header value from web request/response")
@Examples("on web request response:\n" +
        "    set {_response} to event-httpresponse\n" +
        "    set {_server} to {_response}'s header value from key \"server\"")
@Since("0.1.0")
public class ExprGetHeaderValue extends SimpleExpression<String> {

    static {
        Skript.registerExpression(
                ExprGetHeaderValue.class,
                String.class,
                ExpressionType.SIMPLE,
                "[sekb] %httprequest/httpresponse%['s] header value from key %string%"
        );
    }

    private Expression<String> headerProperty;
    private Expression<?> unParsedExpr;

    @Override
    protected @Nullable String[] get(Event e) {
        String[] retr = new String[1];
        Object instance = unParsedExpr.getSingle(e);
        String headerProp = headerProperty.getSingle(e);

        if(instance instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) unParsedExpr.getSingle(e);
            retr[0] = request.getHeaders().getFirstHeaderStringValue(headerProp);
        }
        else if(instance instanceof HttpResponse){
            HttpResponse response = (HttpResponse) unParsedExpr.getSingle(e);
            retr[0] = response.getHeaders().getFirstHeaderStringValue(headerProp);
        }
        else {
            Skript.error("Provided HttpRequest or HttpResponse instance is null!");
            return null;
        }

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
        unParsedExpr = exprs[0];
        headerProperty = (Expression<String>) exprs[1];
        return true;
    }
}
