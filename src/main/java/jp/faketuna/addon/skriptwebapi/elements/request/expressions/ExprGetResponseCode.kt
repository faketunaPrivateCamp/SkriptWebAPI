package jp.faketuna.addon.skriptwebapi.elements.request.expressions

import ch.njol.skript.Skript
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.util.Kleenean
import org.bukkit.event.Event
import java.net.HttpURLConnection

class ExprGetResponseCode: SimpleExpression<Number>() {

    companion object{
        init {
            Skript.registerExpression(ExprGetResponseCode::class.java, Number::class.java, ExpressionType.COMBINED, "[skeb] %httpurlcon%['s] response code")
        }
    }

    private var httpURLConnection: Expression<HttpURLConnection>? = null

    override fun getReturnType(): Class<out Number> {
        return Number::class.java
    }

    override fun isSingle(): Boolean {
        return true
    }

    override fun init(exprs: Array<out Expression<*>>, matchedPattern: Int, isDelayed: Kleenean?, parser: SkriptParser.ParseResult?): Boolean {
        httpURLConnection = exprs[0] as Expression<HttpURLConnection>?
        return true
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return ""
    }

    override fun get(event: Event?): Array<Number?>? {
        val c = httpURLConnection!!.getSingle(event)
        if (c != null){
            return arrayOf(c.responseCode)
        }
        return null
    }
}