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
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.json.JsonHttpContent;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

@Name("Set web request body")
@Description("Set web request body to specified string")
@Examples("set {_request} to new http request with method \"PATCH\"\n" +
        "set {_request}'s target url to \"https://domain/\"\n" +
        "set {_request}'s header \"Custom\" to \"Test\"\n" +
        "set {_request}'s header \"Content-Type\" to \"application/json\"\n" +
        "set {_request}'s header \"User-Agent\" to \"SkriptWebAPI/0.1.0\"\n" +
        "set {_request}'s body to \"{\"\"json\"\":\"\"test\"\"}\"")
@Since("0.1.0")
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
