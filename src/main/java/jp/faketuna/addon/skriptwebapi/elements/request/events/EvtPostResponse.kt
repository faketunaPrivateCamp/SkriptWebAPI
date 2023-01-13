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
import jp.faketuna.addon.skriptwebapi.api.server.events.PostResponseEvent
import org.bukkit.event.Event
import java.net.HttpURLConnection

@Name("On post request response received")
@Description("This event fired when receive the post web request response.")
@Examples("")
@Since("0.0.3")
class EvtPostResponse: SkriptEvent() {

    companion object{
        init {
            Skript.registerEvent("Post request response", EvtPostResponse::class.java, PostResponseEvent::class.java, "[skeb] post [web] [request] response")

            EventValues.registerEventValue(PostResponseEvent::class.java, HttpURLConnection::class.java, object: Getter<HttpURLConnection, PostResponseEvent>(){
                override fun get(e: PostResponseEvent): HttpURLConnection {
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