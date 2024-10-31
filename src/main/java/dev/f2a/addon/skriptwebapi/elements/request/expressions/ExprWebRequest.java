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

@Name("Send web request")
@Description("Send a normal web request.\n" +
        "This method will cause lag spike.\n" +
        "If you encountered the lag spike, please use async web request instead.")
@Examples("set {_request} to new http request with method \"PATCH\"\n" +
        "set {_request}'s target url to \"https://domain\"\n" +
        "set {_request}'s header \"Custom\" to \"Test\"\n" +
        "set {_request}'s header \"Content-Type\" to \"application/json\"\n" +
        "set {_request}'s header \"User-Agent\" to \"SkriptWebAPI/0.1.0\"\n" +
        "set {_request}'s body to \"{\"\"json\"\":\"\"test\"\"}\"\n" +
        "set {_response} to response of {_request}\n" +
        "set {_body} to body of {_response}\n" +
        "broadcast \"server: %{_response}'s header value from key \"server\"%\"\n" +
        "broadcast \"Body: %{_body}%\"\n" +
        "broadcast \"Status code: %status code of {_response}%\"\n" +
        "broadcast \"Request method: %request method of {_response}%\"")
@Since("0.1.0")
public class ExprWebRequest extends SimpleExpression<HttpResponse> {

    static {
        Skript.registerExpression(
                ExprWebRequest.class,
                HttpResponse.class,
                ExpressionType.SIMPLE,
                // set {_something} to response of
                "[skweapi] response of %httprequest%"
        );
    }


    private Expression<HttpRequest> httpRequest;


    @Override
    protected @Nullable HttpResponse[] get(Event e) {
        Plugin plugin = SkriptWebAPI.getPlugin();
        HttpRequest request = httpRequest.getSingle(e);

        if(request == null) {
            Skript.error("Failed to get http request instance when sending http request.");
            return null;
        }

        if(request.getUrl().getHost().isEmpty()) {
            Skript.error("Request URL is cannot be empty!");
            return null;
        }

        if(request.getRequestMethod().isEmpty()) {
            Skript.error("Request method is cannot be empty!");
            return null;
        }

        HttpResponse[] response = new HttpResponse[1];
        try {
            response[0] = request.execute();
        } catch (IOException ex) {
            Skript.error("Something went wrong while sending web request!");
            ex.printStackTrace();
            return null;
        } catch (IllegalArgumentException ex) {
            Skript.error("Your specified request method is not supported!");
            return null;
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
