package dev.f2a.addon.skriptwebapi.elements.request.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.json.JsonHttpContent;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class EffSetWebRequestBody extends Effect {

    static {
        Skript.registerEffect(
                EffSetWebRequestBody.class,
                "[skeb] set %httprequest%['s] (body|contents) to %string%"
        );
    }

    private Expression<HttpRequest> httpRequest;
    private Expression<String> requestBody;

    @Override
    protected void execute(Event e) {
        HttpRequest request = httpRequest.getSingle(e);
        String body = requestBody.getSingle(e);
        if(request == null) {
            Skript.error("Failed to get http request instance while setting web request method.");
            return;
        }
        if(request.getRequestMethod().equalsIgnoreCase("GET")) {
            Skript.error("GET with non-zero content length is not supported");
            return;
        }

        request.setContent(new ByteArrayContent("application/json", body.getBytes(Charset.defaultCharset())));
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        httpRequest = (Expression<HttpRequest>) exprs[0];
        requestBody = (Expression<String>) exprs[1];
        return true;
    }
}
