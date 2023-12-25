package dev.f2a.addon.skriptwebapi.elements.request.expressions;

import ch.njol.skript.Skript;
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

public class ExprGetHeaderObject extends SimpleExpression<HttpHeaders> {

    static {
        Skript.registerExpression(
                ExprGetHeaderObject.class,
                HttpHeaders.class,
                ExpressionType.SIMPLE,
                "[skeb] header [object] from %httprequest/httpresponse%"
        );
    }


    private Expression<?> unParsedExpr;


    @Override
    protected @Nullable HttpHeaders[] get(Event e) {
        HttpHeaders[] headers = new HttpHeaders[1];
        Object instance = unParsedExpr.getSingle(e);

        if(instance instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) unParsedExpr.getSingle(e);
            headers[0] = request.getHeaders();
            return headers;
        }
        else if(instance instanceof HttpResponse){
            HttpResponse response = (HttpResponse) unParsedExpr.getSingle(e);
            headers[0] = response.getHeaders();
            return headers;
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
    public Class<? extends HttpHeaders> getReturnType() {
        return HttpHeaders.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        unParsedExpr = exprs[0];
        return true;
    }
}
