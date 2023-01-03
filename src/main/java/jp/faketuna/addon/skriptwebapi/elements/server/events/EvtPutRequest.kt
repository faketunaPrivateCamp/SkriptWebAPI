package jp.faketuna.addon.skriptwebapi.elements.server.events

import ch.njol.skript.Skript
import ch.njol.skript.lang.Literal
import ch.njol.skript.lang.SkriptEvent
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.registrations.EventValues
import ch.njol.skript.util.Getter
import com.sun.net.httpserver.HttpExchange
import jp.faketuna.addon.skriptwebapi.api.server.events.PutRequestEvent
import jp.faketuna.addon.skriptwebapi.api.server.objects.*
import org.bukkit.event.Event

class EvtPutRequest: SkriptEvent() {

    companion object{
        init {
            Skript.registerEvent("put request", EvtPutRequest::class.java, PutRequestEvent::class.java, "[skeb] [server] receive put request")

            EventValues.registerEventValue(PutRequestEvent::class.java, Body::class.java, object: Getter<Body, PutRequestEvent>(){
                override fun get(e: PutRequestEvent): Body {
                    return e.body
                }
            }, 0)
            EventValues.registerEventValue(PutRequestEvent::class.java, Header::class.java, object: Getter<Header, PutRequestEvent>(){
                override fun get(e: PutRequestEvent): Header {
                    return e.header
                }
            }, 0)
            EventValues.registerEventValue(PutRequestEvent::class.java, UserAgent::class.java, object: Getter<UserAgent, PutRequestEvent>(){
                override fun get(e: PutRequestEvent): UserAgent {
                    return e.userAgent
                }
            }, 0)
            EventValues.registerEventValue(PutRequestEvent::class.java, TargetHost::class.java, object: Getter<TargetHost, PutRequestEvent>(){
                override fun get(e: PutRequestEvent): TargetHost {
                    return e.targetHost
                }
            }, 0)
            EventValues.registerEventValue(PutRequestEvent::class.java, ContextPath::class.java, object: Getter<ContextPath, PutRequestEvent>(){
                override fun get(e: PutRequestEvent): ContextPath {
                    return e.contextPath
                }
            }, 0)
            EventValues.registerEventValue(PutRequestEvent::class.java, HttpExchange::class.java, object: Getter<HttpExchange, PutRequestEvent>(){
                override fun get(e: PutRequestEvent): HttpExchange {
                    return e.exchange
                }
            }, 0)
        }
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return "on put request"
    }

    override fun init(exprs: Array<out Literal<*>>?, matchedPattern: Int, parser: SkriptParser.ParseResult?): Boolean {
        return true
    }

    override fun check(event: Event?): Boolean {
        return true
    }
}