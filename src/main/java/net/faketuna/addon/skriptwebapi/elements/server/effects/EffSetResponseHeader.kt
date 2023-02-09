package net.faketuna.addon.skriptwebapi.elements.server.effects

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

@Name("Set response header")
@Description("This is an effect that can be used within a any receive request event.\n" +
        "You can edit the response header. Header key is case sensitive.")
@Examples("set event-request response header \"Content-Type\" to \"application/json\"\n")
@Since("0.0.1")
class EffSetResponseHeader: Effect() {

    companion object{
        init {
            Skript.registerEffect(EffSetResponseHeader::class.java, "[skeb] set [the] %httpexchange%['s] response header %string% to %string%")
        }
    }

    private lateinit var exchange: Expression<HttpExchange>
    private lateinit var key: Expression<String>
    private lateinit var value: Expression<String>

    override fun toString(event: Event?, debug: Boolean): String {
        return ""
    }

    override fun init(expr: Array<out Expression<*>>?, matchedPattern: Int, isDelayed: Kleenean?, parser: SkriptParser.ParseResult?): Boolean {
        exchange = expr!![0] as Expression<HttpExchange>
        key = expr[1] as Expression<String>
        value = expr[2] as Expression<String>
        return true
    }

    override fun execute(e: Event?) {
        exchange.getSingle(e)!!.responseHeaders.set(key.getSingle(e).toString(), value.getSingle(e).toString())
    }
}