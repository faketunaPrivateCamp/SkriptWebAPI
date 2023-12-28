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
import com.google.api.client.http.HttpHeaders;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Remove header value")
@Description("Rewrite")
@Examples("")
@Since("0.1.0")
public class EffRemoveHeaderValue extends Effect {

    static {
        Skript.registerEffect(
                EffRemoveHeaderValue.class,
                "[skeb] remove [the] %httpheader%['s] (header|header properties|header prop) %string%"
        );
    }

    private Expression<HttpHeaders> httpHeaders;
    private Expression<String> headerKey;

    @Override
    protected void execute(Event e) {
        HttpHeaders headers = httpHeaders.getSingle(e);
        String key = headerKey.getSingle(e);

        if(headers == null) {
            Skript.error("Provided HttpHeaders instance is null!");
            return;
        }
        if(key == null || key.isEmpty()) {
            Skript.error("Header key is cannot be blank!");
            return;
        }

        headers.remove(key);
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        httpHeaders = (Expression<HttpHeaders>) exprs[0];
        headerKey = (Expression<String>) exprs[1];
        return true;
    }
}
