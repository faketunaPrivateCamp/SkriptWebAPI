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

@Name("Get response code")
@Description("It returns response code.")
@Examples("set {_response} to response of get request to \"http://domain/\"\n" +
        "broadcast {_response}'s response code")
@Since("0.0.2")
class ExprGetResponseCode: SimpleExpression<Number>() {

    companion object{
        init {
            Skript.registerExpression(ExprGetResponseCode::class.java, Number::class.java, ExpressionType.COMBINED, "[skeb] %httpconnection%['s] response code")
        }
    }

    private var HttpConnection: Expression<HttpConnection>? = null

    override fun getReturnType(): Class<out Number> {
        return Number::class.java
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

    override fun get(event: Event?): Array<Number?>? {
        val responseCode = HttpConnection!!.getSingle(event)?.getResponseCode()
        if (responseCode != null){
            return arrayOf(responseCode)
        }
        return null
    }
}