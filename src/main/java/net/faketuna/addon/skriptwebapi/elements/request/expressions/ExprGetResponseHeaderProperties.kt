package net.faketuna.addon.skriptwebapi.elements.request.expressions

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
import net.faketuna.addon.skriptwebapi.api.server.connection.HttpConnection
import org.bukkit.event.Event

@Name("Get response header properties")
@Description("It returns response header properties.")
@Examples("set {_response} to response of get request to \"http://domain/\"\n" +
        "broadcast {_response}'s response header \"Date\"")
@Since("0.0.4")
class ExprGetResponseHeaderProperties: SimpleExpression<String>() {

    companion object{
        init {
            Skript.registerExpression(ExprGetResponseHeaderProperties::class.java, String::class.java, ExpressionType.COMBINED, "[skeb] %httpconnection%['s] response header [(properties|prop)] %string%")
        }
    }

    private var httpConnection: Expression<HttpConnection>? = null
    private var headerProperties: Expression<String>? = null

    override fun getReturnType(): Class<out String> {
        return String::class.java
    }

    override fun isSingle(): Boolean {
        return true
    }

    override fun init(exprs: Array<out Expression<*>>, matchedPattern: Int, isDelayed: Kleenean?, parser: SkriptParser.ParseResult?): Boolean {
        httpConnection = exprs[0] as Expression<HttpConnection>?
        headerProperties = exprs[1] as Expression<String>
        return true
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return ""
    }

    override fun get(event: Event?): Array<String?>? {
        val prop = httpConnection!!.getSingle(event)?.getResponseHeader()?.get(headerProperties?.getSingle(event))
        if (prop != null){
            return arrayOf(prop.joinToString(", "))
        }
        return null
    }
}