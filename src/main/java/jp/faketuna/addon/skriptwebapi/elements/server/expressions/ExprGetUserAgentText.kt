package jp.faketuna.addon.skriptwebapi.elements.server.expressions

import ch.njol.skript.Skript
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.util.Kleenean
import com.sun.net.httpserver.HttpExchange
import jp.faketuna.addon.skriptwebapi.api.server.objects.ContextPath
import jp.faketuna.addon.skriptwebapi.api.server.objects.UserAgent
import org.bukkit.event.Event

class ExprGetUserAgentText: SimpleExpression<String>() {

    companion object{
        init {
            Skript.registerExpression(ExprGetUserAgentText::class.java, String::class.java, ExpressionType.COMBINED, "[skeb] %httpexchange%['s] [user][-]agent")
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
        val p = httpExchange!!.getSingle(event)!!.requestHeaders["User-Agent"]!![0]
        if (p != null){
            return arrayOf(p)
        }
        return null
    }
}