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
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import dev.f2a.addon.skriptwebapi.SkriptWebAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

@Name("Retrieve the new web request instance")
@Description("Retrieve the new web request instance.\n" +
        "You can specify request method. If you don't specified It will use GET as default method\n" +
        "Patch method is not supported.")
@Examples("TODO()")
@Since("0.1.0")
public class ExprGetNewHTTPRequest extends SimpleExpression<HttpRequest> {

    static {
        Skript.registerExpression(
                ExprGetNewHTTPRequest.class,
                HttpRequest.class,
                ExpressionType.SIMPLE,
                // set {_something} to response of
                "[skeb] new http request [with method %-string%]"
        );
    }


    private Expression<String> requestMethod;


    @Override
    protected @Nullable HttpRequest[] get(Event e) {
        HttpRequest[] newRequest = new HttpRequest[1];
        String method = null;
        if(requestMethod != null) {
            method = requestMethod.getSingle(e);
        }
        try {
            if(method != null) {
                switch (method.toUpperCase()) {
                    case "PATCH" -> {
                        newRequest[0] = SkriptWebAPI.getHttpRequestFactory().buildPatchRequest(null, null);
                    }
                    case "HEAD" -> {
                        newRequest[0] = SkriptWebAPI.getHttpRequestFactory().buildHeadRequest(null);
                    }
                    default -> {
                        newRequest[0] = SkriptWebAPI.getHttpRequestFactory().buildRequest(method, null, null);
                    }
                }
            }
            else {
                newRequest[0] = SkriptWebAPI.getHttpRequestFactory().buildRequest("GET", null, null);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return newRequest;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends HttpRequest> getReturnType() {
        return HttpRequest.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        if(exprs[0] != null) requestMethod = (Expression<String>) exprs[0];
        return true;
    }
}
