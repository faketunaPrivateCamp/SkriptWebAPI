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

@Name("Start http server")
@Description("")
@Examples("")
@Since("0.1.0")
public class EffStartHttpServer extends Effect {

    static {
        Skript.registerEffect(
                EffSendHttpResponse.class,
                "[skeb] start http server [(in|on) port %-integer%]"
        );
    }

    private final int TEMP_DEFAULT_PORT = 8080;

    private Expression<Integer> serverPort;


    @Override
    protected void execute(Event e) {
        Integer port = serverPort.getSingle(e);
        if(port == null) {
            port = TEMP_DEFAULT_PORT;
        }

        boolean status = SkebHttpServer.runServer(port);
        if(!status) {
            Skript.warning("HTTP server failed to start! server is already running or exception occurred!");
        }
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        if(exprs[0] != null) serverPort = (Expression<Integer>) exprs[0];
        return true;
    }
}
