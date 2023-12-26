package dev.f2a.addon.skriptwebapi.elements.server.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.sun.net.httpserver.HttpExchange;
import dev.f2a.addon.skriptwebapi.internal.events.HttpRequestEvent;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EvtHttpRequest extends SkriptEvent {

    static {
        Skript.registerEvent("", EvtHttpRequest.class, HttpRequestEvent.class, "[skeb] http request [received]")
                .description("Event dscription")
                .examples("TODO")
                .since("0.1.0");

        EventValues.registerEventValue(HttpRequestEvent.class, HttpExchange.class, new Getter<>() {
            @Override
            public @Nullable HttpExchange get(HttpRequestEvent event) {
                return event.getHttpExchange();
            }
        }, 0);
    }


    @Override
    public boolean init(Literal<?>[] args, int matchedPattern, SkriptParser.ParseResult parseResult) {
        return true;
    }

    @Override
    public boolean check(Event event) {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "";
    }
}
