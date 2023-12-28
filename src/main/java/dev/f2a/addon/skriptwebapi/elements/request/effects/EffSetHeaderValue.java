package dev.f2a.addon.skriptwebapi.elements.request.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Set web request header value")
@Description("Set the web request header to specified key and value")
@Examples("set {_request} to new http request with method \"GET\"\n" +
        "set {_request}'s target url to \"https://domain/\"\n" +
        "set {_request}'s header \"Custom\" to \"Test\"")
@Since("0.1.0")
public class EffSetHeaderValue extends Effect {

    static {
        Skript.registerEffect(
                EffSetHeaderValue.class,
                "[skeb] set [the] %httprequest/httpresponse%['s] (header|header properties|header prop) %string% to %objects%"
        );
    }

    private Expression<String> headerKey;
    private Expression<?> unParsedExpr;
    private Expression<?> unParsedExprWebReqs;

    @Override
    protected void execute(Event e) {
        String key = headerKey.getSingle(e);
        Object value = unParsedExpr.getSingle(e);
        Object instance = unParsedExprWebReqs.getSingle(e);

        if(key == null || key.isEmpty()) {
            Skript.error("Header key is cannot be blank!");
            return;
        }

        if(instance instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) unParsedExprWebReqs.getSingle(e);
            request.getHeaders().set(key, value);
        }
        else if(instance instanceof HttpResponse){
            HttpResponse response = (HttpResponse) unParsedExprWebReqs.getSingle(e);
            response.getHeaders().set(key, value);
        }
        else {
            Skript.error("Provided HttpRequest or HttpResponse instance is null!");
        }
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        unParsedExprWebReqs = exprs[0];
        headerKey = (Expression<String>) exprs[1];
        unParsedExpr = exprs[2];
        return true;
    }
}
