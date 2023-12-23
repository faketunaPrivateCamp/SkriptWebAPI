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
import dev.f2a.addon.skriptwebapi.internal.events.HttpResponseEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

@Name("Send a async web request")
@Description("Send a async web request to specified URL.\n" +
        "Response can be obtain from HttpResponseEvent.")
@Examples("TODO()")
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
                    Bukkit.getPluginManager().callEvent(new HttpResponseEvent(response));
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
