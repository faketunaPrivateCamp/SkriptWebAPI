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
import com.google.api.client.http.HttpResponse;
import dev.f2a.addon.skriptwebapi.SkriptWebAPI;
import dev.f2a.addon.skriptwebapi.internal.events.WebRequestResponseEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

@Name("Send a async web request")
@Description("Send a async web request to specified URL.\n" +
        "Response can be obtain from HttpResponseEvent.")
@Examples("command asyncpatch:\n" +
        "    trigger:\n" +
        "        set {_request} to new http request with method \"PATCH\"\n" +
        "        set {_request}'s target url to \"https://domain/\"\n" +
        "        set {_request}'s header \"Custom\" to \"Test\"\n" +
        "        set {_request}'s header \"Content-Type\" to \"application/json\"\n" +
        "        set {_request}'s header \"User-Agent\" to \"SkriptWebAPI/0.1.0\"\n" +
        "        set {_request}'s body to \"{\"\"json\"\":\"\"test\"\"}\"\n" +
        "        send async web request with {_request}" +
        "" +
        "on web request response:\n" +
        "    set {_response} to event-httpresponse\n" +
        "    set {_body} to body of {_response}\n" +
        "    set {_method} to request method of {_response}\n" +
        "    broadcast \"server: %{_response}'s header value from key \"server\"%\"\n" +
        "    broadcast \"1: %{_body}%\"\n" +
        "    broadcast \"2: %status code of {_response}%\"\n" +
        "    broadcast \"3: %request method of {_response}%\"")
@Since("0.1.0")
public class EffAsyncWebRequest extends Effect {

    static {
        Skript.registerEffect(
                EffAsyncWebRequest.class,
                "[skeb] send [async] web request [(using|with)] %httprequest%"
        );
    }

    private Expression<HttpRequest> httpRequest;


    @Override
    protected void execute(Event e) {
        Plugin plugin = SkriptWebAPI.getPlugin();
        HttpRequest request = httpRequest.getSingle(e);

        if(request == null) {
            Skript.error("Failed to get http request instance when sending http request.");
            return;
        }

        if(request.getUrl().getHost().isEmpty()) {
            Skript.error("Request URL is cannot be empty!");
            return;
        }

        if(request.getRequestMethod().isEmpty()) {
            Skript.error("Request method is cannot be empty!");
            return;
        }

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                HttpResponse response = request.execute();
                Bukkit.getScheduler().callSyncMethod(plugin, () -> {
                    Bukkit.getPluginManager().callEvent(new WebRequestResponseEvent(response));
                    return null;
                });
            } catch (IOException ex) {
                Skript.error("Something went wrong while sending web request!");
                ex.printStackTrace();
            }
        });
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
