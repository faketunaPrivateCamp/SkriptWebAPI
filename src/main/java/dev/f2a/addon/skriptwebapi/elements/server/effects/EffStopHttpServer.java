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
import dev.f2a.addon.skriptwebapi.internal.http.SkebHttpServer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Stop http server")
@Description("")
@Examples("")
@Since("0.1.0")
public class EffStopHttpServer extends Effect {

    static {
        Skript.registerEffect(
                EffSendHttpResponse.class,
                "[skeb] stop http server"
        );
    }


    @Override
    protected void execute(Event e) {
        boolean status = SkebHttpServer.stopServer();
        if(!status) {
            Skript.warning("HTTP server failed to stop! server is not running or exception occurred!");
        }
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        return true;
    }
}