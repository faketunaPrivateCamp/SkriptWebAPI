package jp.faketuna.addon.skriptwebapi.elements.request.expressions

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
import jp.faketuna.addon.skriptwebapi.api.server.connection.HttpConnection
import org.bukkit.event.Event

@Name("Get response body")
@Description("It returns response body.")
@Examples("set {_response} to response of get request to \"http://domain/\"\n" +
        "broadcast {_response}'s response body")
@Since("0.0.4")
class ExprGetResponseBody: SimpleExpression<String>() {

    companion object{
        init {
            Skript.registerExpression(ExprGetResponseBody::class.java, String::class.java, ExpressionType.COMBINED, "[skeb] %httpconnection%['s] response body")
        }
    }

    private var HttpConnection: Expression<HttpConnection>? = null

    override fun getReturnType(): Class<out String> {
        return String::class.java
    }

    override fun isSingle(): Boolean {
        return true
    }

    override fun init(exprs: Array<out Expression<*>>, matchedPattern: Int, isDelayed: Kleenean?, parser: SkriptParser.ParseResult?): Boolean {
        HttpConnection = exprs[0] as Expression<HttpConnection>?
        return true
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return ""
    }

    override fun get(event: Event?): Array<String?>? {
        val body = HttpConnection!!.getSingle(event)?.getResponseBody()
        if (body != null){
            return arrayOf(body)
        }
        return null
    }
}