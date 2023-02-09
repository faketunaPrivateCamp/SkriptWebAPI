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
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.stream.Collectors

@Name("Get body text")
@Description("It returns Body in text (mostly json).")
@Examples("set {_request} to event-request\n" +
        "set {_body} to {_request}'s body")
@Since("0.0.2")
class ExprGetBodyText: SimpleExpression<String>() {

    companion object{
        init {
            Skript.registerExpression(ExprGetBodyText::class.java, String::class.java, ExpressionType.COMBINED, "[skeb] %httpexchange%['s] body")
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
        val b = httpExchange!!.getSingle(event)!!.requestBody
        if (b != null){
            return arrayOf(BufferedReader(InputStreamReader(b)).lines().collect(Collectors.joining()))
        }
        return null
    }
}