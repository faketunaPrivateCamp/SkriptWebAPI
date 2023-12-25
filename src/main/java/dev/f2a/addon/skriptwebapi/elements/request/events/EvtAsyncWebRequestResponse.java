package dev.f2a.addon.skriptwebapi.elements.request.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.google.api.client.http.HttpResponse;
import dev.f2a.addon.skriptwebapi.internal.events.WebRequestResponseEvent;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EvtAsyncWebRequestResponse extends SkriptEvent {

    static {
        Skript.registerEvent("", EvtAsyncWebRequestResponse.class, WebRequestResponseEvent.class, "[skeb] web request response")
                .description("Event dscription")
                .examples("TODO")
                .since("0.1.0");

        EventValues.registerEventValue(WebRequestResponseEvent.class, HttpResponse.class, new Getter<HttpResponse, WebRequestResponseEvent>() {
            @Override
            public @Nullable HttpResponse get(WebRequestResponseEvent event) {
                return event.getHttpResponse();
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
