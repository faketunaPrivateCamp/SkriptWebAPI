package jp.faketuna.addon.skriptwebapi.elements.server.events

import ch.njol.skript.Skript
import ch.njol.skript.lang.Literal
import ch.njol.skript.lang.SkriptEvent
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.registrations.EventValues
import ch.njol.skript.util.Getter
import com.sun.net.httpserver.HttpExchange
import jp.faketuna.addon.skriptwebapi.api.server.events.PutRequestEvent
import org.bukkit.event.Event

class EvtPutRequest: SkriptEvent() {

    companion object{
        init {
            Skript.registerEvent("On put request received", EvtPutRequest::class.java, PutRequestEvent::class.java, "[skeb] [server] receive put request")
                .description("This event fired when receive the put request.")
                .examples("on receive put request:\n" +
                        "    set {_request} to event-request\n" +
                        "    broadcast {_request}'s path\n" +
                        "    set {_request} to event-request\n" +
                        "    set {_path} to {_request}'s path\n" +
                        "    set {_body} to \"\"\n" +
                        "\n" +
                        "\n" +
                        "    if ({_path} is \"/send\"):\n" +
                        "        set {_user} to {_request}'s request header properties \"User\" parsed as offline player\n" +
                        "        set {_message} to {_request}'s request header properties \"Message\"\n" +
                        "        \n" +
                        "        if ({_user} is online):\n" +
                        "            send {_message} to {_user}\n" +
                        "            set {_body} to \"{\"\"sent\"\":true}\"\n" +
                        "        else:\n" +
                        "            set {_body} to \"{\"\"sent\"\":false}\"\n" +
                        "        \n" +
                        "        set {_request}'s response header \"Content-Type\" to \"application/json\"\n" +
                        "        reply {_request} with body {_body} and response code 200\n" +
                        "        exit\n" +
                        "\n" +
                        "    reply {_request} as not found")
                .since("0.0.2")

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