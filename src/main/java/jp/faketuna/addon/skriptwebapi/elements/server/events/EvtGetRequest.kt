package jp.faketuna.addon.skriptwebapi.elements.server.events

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
import com.sun.net.httpserver.HttpExchange
import jp.faketuna.addon.skriptwebapi.api.server.events.GetRequestEvent
import org.bukkit.event.Event

@Name("On get request received")
@Description("This event fired when receive the get request.")
@Examples("on receive get request:\n" +
        "    set {_request} to event-request\n" +
        "    set {_path} to {_request}'s path\n" +
        "    set {_body} to \"\"\n" +
        "\n" +
        "    if ({_path} is \"/user\"):\n" +
        "        set {_user} to number of all players\n" +
        "        set {_body} to \"{\"\"user\"\":\"\"%{_user}%\"\"}\"\n" +
        "        set {_request}'s response header \"Content-Type\" to \"application/json\"\n" +
        "        reply {_request} with body {_body} and response code 200\n" +
        "        exit\n" +
        "\n" +
        "    reply {_request} as not found")
@Since("0.0.2")
class EvtGetRequest: SkriptEvent() {

    companion object{
        init {
            Skript.registerEvent("get request", EvtGetRequest::class.java, GetRequestEvent::class.java, "[skeb] [server] receive get request")

            EventValues.registerEventValue(GetRequestEvent::class.java, HttpExchange::class.java, object: Getter<HttpExchange, GetRequestEvent>(){
                override fun get(e: GetRequestEvent): HttpExchange {
                    return e.exchange
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