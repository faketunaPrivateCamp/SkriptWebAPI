package net.faketuna.addon.skriptwebapi.elements.server.events

import ch.njol.skript.Skript
import ch.njol.skript.lang.Literal
import ch.njol.skript.lang.SkriptEvent
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.registrations.EventValues
import ch.njol.skript.util.Getter
import com.sun.net.httpserver.HttpExchange
import net.faketuna.addon.skriptwebapi.api.server.events.DeleteRequestEvent
import org.bukkit.event.Event

class EvtDeleteRequest: SkriptEvent() {

    companion object{
        init {
            Skript.registerEvent("delete request received", EvtDeleteRequest::class.java, DeleteRequestEvent::class.java, "[skeb] [server] receive delete request")
                .description("This event fired when receive the delete request.")
                .examples("on receive delete request:\n" +
                        "    set {_request} to event-request\n" +
                        "    set {_path} to {_request}'s path\n" +
                        "    set {_body} to \"\"\n" +
                        "\n" +
                        "    if ({_path} is \"/delete-variable\"):\n" +
                        "        set {_variableName} to {_request}'s request header properties \"VariableName\"\n" +
                        "\n" +
                        "\n" +
                        "        if ({%{_variableName}%} is not set):\n" +
                        "            set {_body} to \"{\"\"variable\"\":\"\"not found\"\"}\"\n" +
                        "            reply {_request} with body {_body} and response code 404\n" +
                        "            exit\n" +
                        "        else:\n" +
                        "            delete {%{_variableName}%}\n" +
                        "            set {_body} to \"{\"\"variable\"\":\"\"removed\"\"}\"\n" +
                        "\n" +
                        "        set {_request}'s response header \"Content-Type\" to \"application/json\"\n" +
                        "        reply {_request} with body {_body} and response code 200\n" +
                        "        exit\n" +
                        "\n" +
                        "\n" +
                        "    reply {_request} as not found")
                .since("0.0.2")

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