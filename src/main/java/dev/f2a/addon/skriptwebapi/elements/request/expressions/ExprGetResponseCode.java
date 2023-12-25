package dev.f2a.addon.skriptwebapi.elements.request.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.google.api.client.http.HttpResponse;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Name("Retrieve body from web request response")
@Description("Retrieve body from web request response")
@Examples("TODO()")
@Since("0.1.0")
public class ExprGetResponseCode extends SimpleExpression<Integer> {

    static {
        Skript.registerExpression(
                ExprGetResponseCode.class,
                Integer.class,
                ExpressionType.SIMPLE,
                "[skeb] (response|status) code of %httpresponse%"
        );
    }

    private Expression<HttpResponse> httpResponse;

    @Override
    protected @Nullable Integer[] get(Event e) {
        Integer[] retr = new Integer[1];
        HttpResponse response = httpResponse.getSingle(e);

        if(response == null) {
            Skript.error("Failed to get http response instance when retrieving response/status code.");
            return null;
        }

        retr[0] = response.getStatusCode();

        return retr;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        httpResponse = (Expression<HttpResponse>) exprs[0];
        return true;
    }
}
