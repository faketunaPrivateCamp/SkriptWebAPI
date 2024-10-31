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
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Get request method")
@Description("Get request method of web request/response")
@Examples("on web request response:\n" +
        "    set {_response} to event-httpresponse\n" +
        "    set {_method} to request method of {_response}")
@Since("0.1.0")
public class ExprGetRequestMethod extends SimpleExpression<String> {

    static {
        Skript.registerExpression(
                ExprGetRequestMethod.class,
                String.class,
                ExpressionType.SIMPLE,
                "[skweapi] request method of  %httprequest/httpresponse%"
        );
    }


    private Expression<?> unParsedExpr;


    @Override
    protected @Nullable String[] get(Event e) {
        String[] method = new String[1];
        Object instance = unParsedExpr.getSingle(e);

        if(instance instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) unParsedExpr.getSingle(e);
            method[0] = request.getRequestMethod();
            return method;
        }
        else if(instance instanceof HttpResponse){
            HttpResponse response = (HttpResponse) unParsedExpr.getSingle(e);
            method[0] = response.getRequest().getRequestMethod();
            return method;
        }
        else {
            Skript.error("Provided HttpRequest or HttpResponse instance is null!");
            return null;
        }
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        unParsedExpr = exprs[0];
        return true;
    }
}
