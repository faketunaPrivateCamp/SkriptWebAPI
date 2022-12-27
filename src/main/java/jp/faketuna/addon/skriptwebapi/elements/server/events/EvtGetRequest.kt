package jp.faketuna.addon.skriptwebapi.elements.server.events

import ch.njol.skript.Skript
import ch.njol.skript.lang.Literal
import ch.njol.skript.lang.SkriptEvent
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.registrations.EventValues
import ch.njol.skript.util.Getter
import jp.faketuna.addon.skriptwebapi.api.server.events.GetRequestEvent
import jp.faketuna.addon.skriptwebapi.api.server.objects.Body
import jp.faketuna.addon.skriptwebapi.api.server.objects.Header
import jp.faketuna.addon.skriptwebapi.api.server.objects.SenderAddress
import jp.faketuna.addon.skriptwebapi.api.server.objects.UserAgent
import org.bukkit.event.Event

class EvtGetRequest: SkriptEvent() {

    companion object{
        init {
            Skript.registerEvent("get request", EvtGetRequest::class.java, GetRequestEvent::class.java, "[server] receive get request")

            EventValues.registerEventValue(GetRequestEvent::class.java, Body::class.java, object: Getter<Body, GetRequestEvent>(){
                override fun get(e: GetRequestEvent): Body {
                    return e.body
                }
            }, 0)
            EventValues.registerEventValue(GetRequestEvent::class.java, Header::class.java, object: Getter<Header, GetRequestEvent>(){
                override fun get(e: GetRequestEvent): Header {
                    return e.header
                }
            }, 0)
            EventValues.registerEventValue(GetRequestEvent::class.java, UserAgent::class.java, object: Getter<UserAgent, GetRequestEvent>(){
                override fun get(e: GetRequestEvent): UserAgent {
                    return e.userAgent
                }
            }, 0)
            EventValues.registerEventValue(GetRequestEvent::class.java, SenderAddress::class.java, object: Getter<SenderAddress, GetRequestEvent>(){
                override fun get(e: GetRequestEvent): SenderAddress {
                    return e.senderAddress
                }
            }, 0)
        }
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return "on get request"
    }

    override fun init(exprs: Array<out Literal<*>>?, matchedPattern: Int, parser: SkriptParser.ParseResult?): Boolean {

        return true
    }

    override fun check(event: Event?): Boolean {
        return true
    }
}