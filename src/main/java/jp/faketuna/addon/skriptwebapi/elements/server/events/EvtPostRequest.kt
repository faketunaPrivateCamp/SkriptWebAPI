package jp.faketuna.addon.skriptwebapi.elements.server.events

import ch.njol.skript.Skript
import ch.njol.skript.lang.Literal
import ch.njol.skript.lang.SkriptEvent
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.registrations.EventValues
import ch.njol.skript.util.Getter
import com.sun.net.httpserver.HttpExchange
import jp.faketuna.addon.skriptwebapi.api.server.events.PostRequestEvent
import org.bukkit.event.Event

class EvtPostRequest: SkriptEvent() {

    companion object{
        init {
            Skript.registerEvent("post request", EvtPostRequest::class.java, PostRequestEvent::class.java, "[skeb] [server] receive post request")

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