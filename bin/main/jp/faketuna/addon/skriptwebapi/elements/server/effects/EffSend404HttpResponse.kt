package jp.faketuna.addon.skriptwebapi.elements.server.effects

import ch.njol.skript.Skript
import ch.njol.skript.lang.Effect
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import com.sun.net.httpserver.HttpExchange
import org.bukkit.event.Event

class EffSend404HttpResponse: Effect() {

    companion object{
        init {
            Skript.registerEffect(EffSend404HttpResponse::class.java, "reply %httpexchange% as (not found|404)")
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