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
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Remove header value")
@Description("Removes header value from web request")
@Examples("set {_request} to new http request with method \"PUT\"\n" +
        "set {_request}'s target url to \"https://httpbin.org/put\"\n" +
        "set {_request}'s header \"Custom\" to \"Test\"\n" +
        "remove {_request}'s header \"Custom\"")
@Since("0.1.0")
public class EffRemoveHeaderValue extends Effect {

    static {
        Skript.registerEffect(
                EffRemoveHeaderValue.class,
                "[skweapi] remove [the] %httprequest%['s] (header|header properties|header prop) %string%"
        );
    }

    private Expression<HttpRequest> httpRequest;
    private Expression<String> headerKey;

    @Override
    protected void execute(Event e) {
        HttpRequest request = httpRequest.getSingle(e);
        String key = headerKey.getSingle(e);

        if(request == null) {
            Skript.error("Provided HttpHeaders instance is null!");
            return;
        }
        if(key == null || key.isEmpty()) {
            Skript.error("Header key is cannot be blank!");
            return;
        }

        request.getHeaders().remove(key);
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        httpRequest = (Expression<HttpRequest>) exprs[0];
        headerKey = (Expression<String>) exprs[1];
        return true;
    }
}
