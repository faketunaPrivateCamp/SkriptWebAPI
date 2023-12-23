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
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import dev.f2a.addon.skriptwebapi.SkriptWebAPI;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

@Name("Send normal web request")
@Description("Later | This method is may cause lag spike. If you encountered the lag spike please use async web request instead.")
@Examples("Later")
@Since("0.1.0")
public class ExprWebRequest extends SimpleExpression<HttpResponse> {

    static {
        Skript.registerExpression(
                ExprWebRequest.class,
                HttpResponse.class,
                ExpressionType.SIMPLE,
                // set {_something} to response of
                "[skeb] response of %httprequest%"
        );
    }


    private Expression<HttpRequest> httpRequest;


    @Override
    protected @Nullable HttpResponse[] get(Event e) {
        Plugin plugin = SkriptWebAPI.getPlugin();
        HttpRequest request = httpRequest.getSingle(e);

        if(request == null) {
            Skript.error("Failed to get http request instance when sending http request.");
            throw new NullPointerException("Failed to get http request instance when sending http request.");
        }

        if(request.getUrl().getHost().isEmpty()) {
            Skript.error("Request URL is cannot be empty!");
            throw new IllegalStateException("Request URL is cannot be empty!");
        }

        if(request.getRequestMethod().isEmpty()) {
            Skript.error("Request method is cannot be empty!");
            throw new IllegalStateException("Request method is cannot be empty!");
        }

        HttpResponse[] response = new HttpResponse[1];
        try {
            response[0] = request.execute();
        } catch (IOException ex) {
            Skript.error("Something went wrong while sending web request!");
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends HttpResponse> getReturnType() {
        return HttpResponse.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        httpRequest = (Expression<HttpRequest>) exprs[0];
        return true;
    }
}
