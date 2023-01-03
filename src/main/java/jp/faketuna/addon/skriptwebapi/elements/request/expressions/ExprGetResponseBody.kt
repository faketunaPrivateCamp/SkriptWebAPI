package jp.faketuna.addon.skriptwebapi.elements.request.expressions

import ch.njol.skript.Skript
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.util.Kleenean
import org.bukkit.event.Event
import java.net.HttpURLConnection
import java.nio.charset.Charset

class ExprGetResponseBody: SimpleExpression<String>() {

    companion object{
        init {
            Skript.registerExpression(ExprGetResponseBody::class.java, String::class.java, ExpressionType.COMBINED, "[skeb] %httpurlcon%['s] response body")
        }
    }

    private var httpURLConnection: Expression<HttpURLConnection>? = null

    override fun getReturnType(): Class<out String> {
        return String::class.java
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

    override fun get(event: Event?): Array<String?>? {
        val c = httpURLConnection!!.getSingle(event)
        if (c != null){
            return arrayOf(c.inputStream.readBytes().toString(Charset.defaultCharset()))
        }
        return null
    }
}