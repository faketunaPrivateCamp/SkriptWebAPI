package jp.faketuna.addon.skriptwebapi.elements.server.events

import ch.njol.skript.Skript
import ch.njol.skript.lang.Literal
import ch.njol.skript.lang.SkriptEvent
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.registrations.EventValues
import ch.njol.skript.util.Getter
import com.sun.net.httpserver.HttpExchange
import jp.faketuna.addon.skriptwebapi.api.server.events.DeleteRequestEvent
import org.bukkit.event.Event

class EvtDeleteRequest: SkriptEvent() {

    companion object{
        init {
            Skript.registerEvent("delete request", EvtDeleteRequest::class.java, DeleteRequestEvent::class.java, "[skeb] [server] receive delete request")

            EventValues.registerEventValue(DeleteRequestEvent::class.java, HttpExchange::class.java, object: Getter<HttpExchange, DeleteRequestEvent>(){
                override fun get(e: DeleteRequestEvent): HttpExchange {
                    return e.exchange
                }
            }, 0)
        }
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return "on delete request"
    }

    override fun init(exprs: Array<out Literal<*>>?, matchedPattern: Int, parser: SkriptParser.ParseResult?): Boolean {
        return true
    }

    override fun check(event: Event?): Boolean {
        return true
    }
}