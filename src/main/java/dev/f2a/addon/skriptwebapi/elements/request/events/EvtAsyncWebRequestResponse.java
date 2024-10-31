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
        Skript.registerEvent("Async web request response", EvtAsyncWebRequestResponse.class, WebRequestResponseEvent.class, "[skweapi] [async] web request response")
                .description("Fires when web request response received")
                .examples("on web request response:\n" +
                        "    set {_response} to event-httpresponse\n" +
                        "    set {_body} to body of {_response}\n" +
                        "    set {_method} to request method of {_response}\n" +
                        "    broadcast \"server: %{_response}'s header value from key \"server\"%\"\n" +
                        "    broadcast \"1: %{_body}%\"\n" +
                        "    broadcast \"2: %status code of {_response}%\"\n" +
                        "    broadcast \"3: %request method of {_response}%\"")
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
