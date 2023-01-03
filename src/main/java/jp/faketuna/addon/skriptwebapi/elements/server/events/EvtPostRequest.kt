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
import jp.faketuna.addon.skriptwebapi.api.server.events.PostRequestEvent
import org.bukkit.event.Event

@Name("On post request received")
@Description("This event fired when receive the post request.")
@Examples("on receive post request:\n" +
        "    set {_request} to event-request\n" +
        "    set {_path} to {_request}'s path\n" +
        "    set {_body} to \"\"\n" +
        "\n" +
        "    if ({_path} is \"/add-value\"):\n" +
        "        set {_listName} to {_request}'s request header properties \"List\"\n" +
        "        set {_value} to {_request}'s request header properties \"Value\"\n" +
        "    \n" +
        "        if ({%{_listName}%::*} is not set):\n" +
        "            set {_body} to \"{\"\"list\"\":\"\"not found\"\"}\"\n" +
        "            reply {_request} with body {_body} and response code 404\n" +
        "            exit\n" +
        "        \n" +
        "        else if ({_value} is not set):\n" +
        "            set {_body} to \"{\"\"error\"\":\"\"Value not provided\"\"}\"\n" +
        "            reply {_request} with body {_body} and response code 400\n" +
        "            exit\n" +
        "        \n" +
        "        else:\n" +
        "            add {_value} to {%{_listName}%::*}\n" +
        "            set {_body} to \"{\"\"list\"\":\"\"added\"\"}\"\n" +
        "\n" +
        "        set {_request}'s response header \"Content-Type\" to \"application/json\"\n" +
        "        reply {_request} with body {_body} and response code 200\n" +
        "        exit\n" +
        "        \n" +
        "    reply {_request} as not found")
@Since("0.0.2")
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