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
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Set web request target URL")
@Description("Later")
@Examples("Later")
@Since("0.1.0")
public class EffSetWebRequestURL extends Effect {

    static {
        Skript.registerEffect(
                EffSetWebRequestURL.class,
                "[skeb] set %httprequest%['s] [target] (url|host) to %string%"
        );
    }


    private Expression<HttpRequest> httpRequest;
    private Expression<String> targetURI;


    @Override
    protected void execute(Event e) {
        HttpRequest request = httpRequest.getSingle(e);
        String URI = targetURI.getSingle(e);

        if(request == null) {
            Skript.error("Failed to get http request instance while setting web request target URI.");
            return;
        }

        if(URI == null || URI.isEmpty()) {
            Skript.error("Failed to set target URI because URI is null or empty!");
            return;
        }

        request.setUrl(new GenericUrl(URI));
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        httpRequest = (Expression<HttpRequest>) exprs[0];
        targetURI = (Expression<String>) exprs[1];
        return true;
    }
}
