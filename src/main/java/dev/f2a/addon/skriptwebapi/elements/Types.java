package dev.f2a.addon.skriptwebapi.elements;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.registrations.Classes;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.sun.net.httpserver.HttpExchange;

public class Types {
    static {
        Classes.registerClass(new ClassInfo<>(HttpRequest.class, "httprequest")
                .name("httprequest")
                .usage("Http request instance")
                .user("httprequest")
                .defaultExpression(new EventValueExpression<>(HttpRequest.class))
        );

        Classes.registerClass(new ClassInfo<>(HttpResponse.class, "httpresponse")
                .name("httpresponse")
                .usage("Http response instance")
                .user("httpresponse")
                .defaultExpression(new EventValueExpression<>(HttpResponse.class))
        );

        Classes.registerClass(new ClassInfo<>(HttpHeaders.class, "httpheader")
                .name("httpheader")
                .usage("Http headers instance")
                .user("httpheader")
                .defaultExpression(new EventValueExpression<>(HttpHeaders.class))
        );

        Classes.registerClass(new ClassInfo<>(HttpExchange.class, "httpexchange")
                .name("httpexchange")
                .usage("Http exchange instance")
                .user("httpexchange")
                .defaultExpression(new EventValueExpression<>(HttpExchange.class))
        );
    }
}
