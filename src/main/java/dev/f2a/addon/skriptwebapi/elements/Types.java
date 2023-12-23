package dev.f2a.addon.skriptwebapi.elements;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.registrations.Classes;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;

@SuppressWarnings({"unchecked", "rawtypes"})
public class Types {
    static {
        Classes.registerClass(new ClassInfo(HttpRequest.class, "httprequest")
                .name("httprequest")
                .usage("Http request instance")
                .user("httprequest")
                .defaultExpression(new EventValueExpression(HttpRequest.class))
        );
        Classes.registerClass(new ClassInfo(HttpResponse.class, "httpresponse")
                .name("httpresponse")
                .usage("Http response instance")
                .user("httpresponse")
                .defaultExpression(new EventValueExpression(HttpResponse.class))
        );
    }
}
