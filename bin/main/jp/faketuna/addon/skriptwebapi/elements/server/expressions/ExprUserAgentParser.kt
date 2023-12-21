package jp.faketuna.addon.skriptwebapi.elements.server.expressions

import ch.njol.skript.Skript
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.util.Kleenean
import jp.faketuna.addon.skriptwebapi.api.server.objects.ContextPath
import jp.faketuna.addon.skriptwebapi.api.server.objects.UserAgent
import org.bukkit.event.Event

class ExprUserAgentParser: SimpleExpression<String>() {

    companion object{
        init {
            Skript.registerExpression(ExprUserAgentParser::class.java, String::class.java, ExpressionType.COMBINED, "%useragent% parsed as text")
        }
    }

    private var userAgent: Expression<UserAgent>? = null

    override fun getReturnType(): Class<out String> {
        return String::class.java
    }

    override fun isSingle(): Boolean {
        return true
    }

    override fun init(exprs: Array<out Expression<*>>, matchedPattern: Int, isDelayed: Kleenean?, parser: SkriptParser.ParseResult?): Boolean {
        userAgent = exprs[0] as Expression<UserAgent>?
        return true
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return ""
    }

    override fun get(event: Event?): Array<String?>? {
        val a = userAgent!!.getSingle(event)
        if (a != null){
            return arrayOf(a.getUserAgent())
        }
        return null
    }
}