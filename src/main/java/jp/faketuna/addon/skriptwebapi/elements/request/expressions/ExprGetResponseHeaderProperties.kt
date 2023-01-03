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
import org.bukkit.event.Event
import java.net.HttpURLConnection

@Name("Get response header properties")
@Description("It returns response header properties.")
@Examples("set {_response} to response of get request to \"http://domain/\"\n" +
        "broadcast {_response}'s response header \"Date\"")
@Since("0.0.2")
class ExprGetResponseHeaderProperties: SimpleExpression<String>() {

    companion object{
        init {
            Skript.registerExpression(ExprGetResponseHeaderProperties::class.java, String::class.java, ExpressionType.COMBINED, "[skeb] %httpurlcon%['s] response header [(properties|prop)] %string%")
        }
    }

    private var httpURLConnection: Expression<HttpURLConnection>? = null
    private var headerProperties: Expression<String>? = null

    override fun getReturnType(): Class<out String> {
        return String::class.java
    }

    override fun isSingle(): Boolean {
        return true
    }

    override fun init(exprs: Array<out Expression<*>>, matchedPattern: Int, isDelayed: Kleenean?, parser: SkriptParser.ParseResult?): Boolean {
        httpURLConnection = exprs[0] as Expression<HttpURLConnection>?
        headerProperties = exprs[1] as Expression<String>
        return true
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return ""
    }

    override fun get(event: Event?): Array<String?>? {
        val c = httpURLConnection!!.getSingle(event)
        if (c != null){
            val header = c.headerFields[headerProperties!!.getSingle(event)]?.joinToString(", ") ?: return null
            return arrayOf(header)
        }
        return null
    }
}