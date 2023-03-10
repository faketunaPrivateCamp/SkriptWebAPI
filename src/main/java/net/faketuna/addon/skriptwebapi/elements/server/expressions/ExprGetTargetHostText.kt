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

@Name("Get host")
@Description("It returns host in text.")
@Examples("set {_request} to event-request\n" +
        "set {_targetHost} to {_request}'s target-host")
@Since("0.0.2")
class ExprGetTargetHostText: SimpleExpression<String>() {

    companion object{
        init {
            Skript.registerExpression(ExprGetTargetHostText::class.java, String::class.java, ExpressionType.COMBINED, "[skeb] %httpexchange%['s] [target][-]host")
        }
    }

    private var httpExchange: Expression<HttpExchange>? = null

    override fun getReturnType(): Class<out String> {
        return String::class.java
    }

    override fun isSingle(): Boolean {
        return true
    }

    override fun init(exprs: Array<out Expression<*>>, matchedPattern: Int, isDelayed: Kleenean?, parser: SkriptParser.ParseResult?): Boolean {
        httpExchange = exprs[0] as Expression<HttpExchange>?
        return true
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return ""
    }

    override fun get(event: Event?): Array<String?>? {
        val p = httpExchange!!.getSingle(event)!!.requestHeaders["host"]!![0]
        if (p != null){
            return arrayOf(p)
        }
        return null
    }
}