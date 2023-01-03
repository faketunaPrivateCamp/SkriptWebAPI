package jp.faketuna.addon.skriptwebapi.elements.server.effects

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.doc.Since
import ch.njol.skript.lang.Effect
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import com.sun.net.httpserver.HttpExchange
import org.bukkit.event.Event

@Name("Response to web request as not found")
@Description("This is an effect that can be used within a any receive request event.\n" +
        "It returns a 404 HTTP status code and json with {\"status\": \"404\"}.")
@Examples("")
@Since("0.0.1")
class EffSend404HttpResponse: Effect() {

    companion object{
        init {
            Skript.registerEffect(EffSend404HttpResponse::class.java, "[skeb] reply %httpexchange% as (not found|404)")
        }
    }

    private lateinit var exchange: Expression<HttpExchange>

    override fun toString(event: Event?, debug: Boolean): String {
        return ""
    }

    override fun init(expr: Array<out Expression<*>>?, matchedPattern: Int, isDelayed: Kleenean?, parser: SkriptParser.ParseResult?): Boolean {
        exchange = expr!![0] as Expression<HttpExchange>
        return true
    }

    override fun execute(e: Event?) {
        val ex = exchange.getSingle(e)
        val body = """{"status":"404"}"""
        ex!!.sendResponseHeaders(404, body.length.toLong())
        ex!!.responseBody.write(body.toByteArray())
        ex!!.responseBody.close()
    }
}