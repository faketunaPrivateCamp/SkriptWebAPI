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
import com.google.api.client.http.HttpRequest;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Set web request method")
@Description("Set web request method as you specified.\n" +
        "Supported methods: DELETE, GET, HEAD, POST, PUT\n" +
        "You cannot set method to PATCH.")
@Examples("TODO()")
@Since("0.1.0")
public class EffSetWebRequestMethod extends Effect {

    static {
        Skript.registerEffect(
                EffSetWebRequestMethod.class,
                "[skeb] set %httprequest%['s] [request] method to %string%"
        );
    }


    private Expression<HttpRequest> httpRequest;
    private Expression<String> requestMethod;


    @Override
    protected void execute(Event e) {
        HttpRequest request = httpRequest.getSingle(e);
        String method = requestMethod.getSingle(e);

        if(request == null) {
            Skript.error("Failed to get http request instance while setting web request method.");
            return;
        }

        if(method == null || method.isEmpty()) {
            Skript.error("Failed to set request method because method is null or empty!");
            return;
        }

        request.setRequestMethod(method.toUpperCase());
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        httpRequest = (Expression<HttpRequest>) exprs[0];
        requestMethod = (Expression<String>) exprs[1];
        return true;
    }
}
