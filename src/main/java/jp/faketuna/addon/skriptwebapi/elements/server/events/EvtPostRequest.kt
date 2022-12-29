package jp.faketuna.addon.skriptwebapi.elements.server.events

import ch.njol.skript.Skript
import ch.njol.skript.lang.Literal
import ch.njol.skript.lang.SkriptEvent
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.registrations.EventValues
import ch.njol.skript.util.Getter
import com.sun.net.httpserver.HttpExchange
import jp.faketuna.addon.skriptwebapi.api.server.events.PostRequestEvent
import jp.faketuna.addon.skriptwebapi.api.server.objects.*
import org.bukkit.event.Event

class EvtPostRequest: SkriptEvent() {

    companion object{
        init {
            Skript.registerEvent("post request", EvtPostRequest::class.java, PostRequestEvent::class.java, "[skeb] [server] receive post request")

            EventValues.registerEventValue(PostRequestEvent::class.java, Body::class.java, object: Getter<Body, PostRequestEvent>(){
                override fun get(e: PostRequestEvent): Body {
                    return e.body
                }
            }, 0)
            EventValues.registerEventValue(PostRequestEvent::class.java, Header::class.java, object: Getter<Header, PostRequestEvent>(){
                override fun get(e: PostRequestEvent): Header {
                    return e.header
                }
            }, 0)
            EventValues.registerEventValue(PostRequestEvent::class.java, UserAgent::class.java, object: Getter<UserAgent, PostRequestEvent>(){
                override fun get(e: PostRequestEvent): UserAgent {
                    return e.userAgent
                }
            }, 0)
            EventValues.registerEventValue(PostRequestEvent::class.java, SenderAddress::class.java, object: Getter<SenderAddress, PostRequestEvent>(){
                override fun get(e: PostRequestEvent): SenderAddress {
                    return e.senderAddress
                }
            }, 0)
            EventValues.registerEventValue(PostRequestEvent::class.java, ContextPath::class.java, object: Getter<ContextPath, PostRequestEvent>(){
                override fun get(e: PostRequestEvent): ContextPath {
                    return e.contextPath
                }
            }, 0)
            EventValues.registerEventValue(PostRequestEvent::class.java, HttpExchange::class.java, object: Getter<HttpExchange, PostRequestEvent>(){
                override fun get(e: PostRequestEvent): HttpExchange {
                    return e.exchange
                }
            }, 0)
        }
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return "on post request"
    }

    override fun init(exprs: Array<out Literal<*>>?, matchedPattern: Int, parser: SkriptParser.ParseResult?): Boolean {
        return true
    }

    override fun check(event: Event?): Boolean {
        return true
    }
}