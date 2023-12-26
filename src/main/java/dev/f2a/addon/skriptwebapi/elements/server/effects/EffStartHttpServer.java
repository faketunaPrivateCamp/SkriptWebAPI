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
import dev.f2a.addon.skriptwebapi.internal.http.SkebServerStatus;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Start http server")
@Description("")
@Examples("")
@Since("0.1.0")
public class EffStartHttpServer extends Effect {

    static {
        Skript.registerEffect(
                EffStartHttpServer.class,
                "[skeb] start http server [(in|on) port %-integer%]"
        );
    }

    // TODO Will be implement the plugin config to specify default port.
    private final int TEMP_DEFAULT_PORT = 8080;

    private Expression<Integer> serverPort;


    @Override
    protected void execute(Event e) {
        Integer port = TEMP_DEFAULT_PORT;
        if(serverPort != null) {
            port = serverPort.getSingle(e);
        }

        SkebServerStatus status = SkebHttpServer.runServer(port, "/");

        switch (status) {
            case SERVER_IS_RUNNING -> {
                Skript.warning("HTTP server is already running!");
            }
            case EXCEPTION_OCCURRED -> {
                Skript.warning("Exception occurred while starting the HTTP server!");
            }
            case CONTEXT_PATH_NOT_START_WITH_SLASH -> {
                Skript.warning("The context path must start with a slash!");
            }
            case CONTEXT_PATH_NOT_END_WITH_SLASH -> {
                Skript.warning("The context path must end with a slash!");
            }
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
