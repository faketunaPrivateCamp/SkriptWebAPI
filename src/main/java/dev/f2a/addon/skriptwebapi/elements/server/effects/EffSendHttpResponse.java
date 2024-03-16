package dev.f2a.addon.skriptwebapi.elements.server.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.sun.net.httpserver.HttpExchange;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Name("Response to web request")
@Description("Responses request with custom body and response code.\n" +
        "If not specify the response code, It will response with a 500.")
@Examples("on http request received:\n" +
        "    set {_request} to event-httpexchange\n" +
        "    reply {_request} with body \"{\"\"body\"\":\"\"body\"\"}\" and response code 200")
@Since("0.1.0")
public class EffSendHttpResponse extends Effect {

    static {
        Skript.registerEffect(
                EffSendHttpResponse.class,
                "[skeb] [reply|response] %httpexchange% [to [the] request sender] [(with|and) body %-string%] [(with|and) response code %-integer%]"
        );
    }

    private Expression<HttpExchange> httpExchange;
    private Expression<String> responseBody;
    private Expression<Integer> responseCode;

    @Override
    protected void execute(Event e) {
        HttpExchange exchange = httpExchange.getSingle(e);
        String body = "";
        if(responseBody != null) {
            body = responseBody.getSingle(e);
        }
        Integer code = 500;
        if(responseCode != null) {
            code = responseCode.getSingle(e);
        }

        if(exchange == null) {
            Skript.error("Provided HttpExchange instance is null!");
            return;
        }

        try {
            byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(code, bytes.length);
            exchange.getResponseBody().write(body.getBytes(StandardCharsets.UTF_8));
            exchange.close();
        } catch (IOException ex) {
            Skript.error("An error occurred while send response to request!");
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        httpExchange = (Expression<HttpExchange>) exprs[0];
        if(exprs[1] != null) responseBody = (Expression<String>) exprs[1];
        if(exprs[2] != null) responseCode = (Expression<Integer>) exprs[2];
        return true;
    }
}
