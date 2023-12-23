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
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Response to web request")
@Description("")
@Examples("")
@Since("0.1.0")
public class EffSendHttpResponse extends Effect {
/*
    static {
        Skript.registerEffect(
                EffSendHttpResponse.class,
                "[skeb] reply %httpexchange% [to [the] request sender] [(with|and) body %-string%] [(with|and) response code %-integer%]"
        );
    }
*/
    @Override
    protected void execute(Event e) {

    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return null;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        return false;
    }
}
