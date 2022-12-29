package jp.faketuna.addon.skriptwebapi.elements.server.expressions

import ch.njol.skript.Skript
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.util.Kleenean
import jp.faketuna.addon.skriptwebapi.api.server.objects.ContextPath
import org.bukkit.event.Event

class ExprPathParser: SimpleExpression<String>() {

    companion object{
        init {
            Skript.registerExpression(ExprPathParser::class.java, String::class.java, ExpressionType.COMBINED, "[skeb] %contextpath% parsed as text")
        }
    }

    private var path: Expression<ContextPath>? = null

    override fun getReturnType(): Class<out String> {
        return String::class.java
    }

    override fun isSingle(): Boolean {
        return true
    }

    override fun init(exprs: Array<out Expression<*>>, matchedPattern: Int, isDelayed: Kleenean?, parser: SkriptParser.ParseResult?): Boolean {
        path = exprs[0] as Expression<ContextPath>?
        return true
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return ""
    }

    override fun get(event: Event?): Array<String?>? {
        val p = path!!.getSingle(event)
        if (p != null){
            return arrayOf(p.getContextPath())
        }
        return null
    }
}