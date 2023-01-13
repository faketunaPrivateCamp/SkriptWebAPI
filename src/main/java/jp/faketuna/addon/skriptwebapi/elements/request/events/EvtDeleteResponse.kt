package jp.faketuna.addon.skriptwebapi.elements.request.events

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.doc.Since
import ch.njol.skript.lang.Literal
import ch.njol.skript.lang.SkriptEvent
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.registrations.EventValues
import ch.njol.skript.util.Getter
import jp.faketuna.addon.skriptwebapi.api.server.events.DeleteResponseEvent
import org.bukkit.event.Event
import java.net.HttpURLConnection

@Name("On delete request response received")
@Description("This event fired when receive the delete web request response.")
@Examples("")
@Since("0.0.3")
class EvtDeleteResponse: SkriptEvent() {

    companion object{
        init {
            Skript.registerEvent("Delete request response", EvtDeleteResponse::class.java, DeleteResponseEvent::class.java, "[skeb] delete [web] [request] response")

            EventValues.registerEventValue(DeleteResponseEvent::class.java, HttpURLConnection::class.java, object: Getter<HttpURLConnection, DeleteResponseEvent>(){
                override fun get(e: DeleteResponseEvent): HttpURLConnection {
                    return e.httpURLConnection
                }
            }, 0)
        }
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return ""
    }

    override fun init(exprs: Array<out Literal<*>>?, matchedPattern: Int, parser: SkriptParser.ParseResult?): Boolean {
        return true
    }

    override fun check(event: Event?): Boolean {
        return true
    }
}