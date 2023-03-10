package net.faketuna.addon.skriptwebapi.elements

import ch.njol.skript.classes.ClassInfo
import ch.njol.skript.expressions.base.EventValueExpression
import ch.njol.skript.registrations.Classes
import com.sun.net.httpserver.HttpExchange
import net.faketuna.addon.skriptwebapi.api.server.connection.HttpConnection
import net.faketuna.addon.skriptwebapi.api.server.events.DeleteRequestEvent
import net.faketuna.addon.skriptwebapi.api.server.events.GetRequestEvent
import net.faketuna.addon.skriptwebapi.api.server.events.PostRequestEvent
import net.faketuna.addon.skriptwebapi.api.server.events.PutRequestEvent
import net.faketuna.addon.skriptwebapi.api.server.objects.Header
import java.net.HttpURLConnection

class Types {

    companion object{
        init {
            Classes.registerClass(ClassInfo(Header::class.java, "header")
                .name("Header")
                .usage("Header of request")
                .user("header?")
                .defaultExpression(EventValueExpression(Header::class.java)))

            Classes.registerClass(ClassInfo(HttpExchange::class.java, "httpexchange")
                .name("HttpExchange")
                .usage("Http request instance")
                .user("(request|http ?exchange)")
                .defaultExpression(EventValueExpression(HttpExchange::class.java)))

            Classes.registerClass(ClassInfo(GetRequestEvent::class.java, "getrequestevent")
                .name("GetRequestEvent")
                .usage("Get request Event")
                .defaultExpression(EventValueExpression(GetRequestEvent::class.java)))

            Classes.registerClass(ClassInfo(PostRequestEvent::class.java, "postrequestevent")
                .name("PostRequestEvent")
                .usage("Post request Event")
                .defaultExpression(EventValueExpression(PostRequestEvent::class.java)))

            Classes.registerClass(ClassInfo(PutRequestEvent::class.java, "putrequestevent")
                .name("PutRequestEvent")
                .usage("Put request Event")
                .defaultExpression(EventValueExpression(PutRequestEvent::class.java)))

            Classes.registerClass(ClassInfo(DeleteRequestEvent::class.java, "deleterequestevent")
                .name("DeleteRequestEvent")
                .usage("Delete request Event")
                .defaultExpression(EventValueExpression(DeleteRequestEvent::class.java)))

            Classes.registerClass(ClassInfo(HttpConnection::class.java, "httpconnection")
                .name("HttpConnection")
                .user("httpconnection")
                .defaultExpression(EventValueExpression(HttpConnection::class.java)))
        }
    }
}