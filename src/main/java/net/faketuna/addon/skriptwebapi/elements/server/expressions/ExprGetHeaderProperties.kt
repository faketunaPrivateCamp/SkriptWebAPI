package net.faketuna.addon.skriptwebapi.elements.server.expressions

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.doc.Since
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.util.Kleenean
import com.sun.net.httpserver.HttpExchange
import org.bukkit.event.Event

@Name("Get header properties")
@Description("It returns header properties in text.")
@Examples("set {_message} to {_request}'s request header properties \"Message\"")
@Since("0.0.2")
class ExprGetHeaderProperties: SimpleExpression<String>() {

    companion object{
        init {
            Skript.registerExpression(ExprGetHeaderProperties::class.java, String::class.java, ExpressionType.COMBINED, "[skeb] %httpexchange%['s] request header properties %string%")
        }
    }

    private var httpExchange: Expression<HttpExchange>? = null
    private var properties: Expression<String>? = null

    override fun getReturnType(): Class<out String> {
        return String::class.java
    }

    override fun isSingle(): Boolean {
        return true
    }

    override fun init(exprs: Array<out Expression<*>>, matchedPattern: Int, isDelayed: Kleenean?, parser: SkriptParser.ParseResult?): Boolean {
        httpExchange = exprs[0] as Expression<HttpExchange>?
        properties = exprs[1] as Expression<String>
        return true
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return ""
    }

    override fun get(event: Event?): Array<String?>? {
        val b = httpExchange!!.getSingle(event)
        if (b != null && b.requestHeaders[properties!!.getSingle(event)] != null){
            return arrayOf(b.requestHeaders[properties!!.getSingle(event)]!![0])
        }
        return null
    }
}