package jp.faketuna.addon.skriptwebapi.elements.server.expressions

import ch.njol.skript.Skript
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.util.Kleenean
import jp.faketuna.addon.skriptwebapi.api.server.objects.Body
import jp.faketuna.addon.skriptwebapi.api.server.objects.ContextPath
import org.bukkit.event.Event

class ExprBodyParser: SimpleExpression<String>() {

    companion object{
        init {
            Skript.registerExpression(ExprBodyParser::class.java, String::class.java, ExpressionType.COMBINED, "[skeb] %body% parsed as text")
        }
    }

    private var body: Expression<Body>? = null

    override fun getReturnType(): Class<out String> {
        return String::class.java
    }

    override fun isSingle(): Boolean {
        return true
    }

    override fun init(exprs: Array<out Expression<*>>, matchedPattern: Int, isDelayed: Kleenean?, parser: SkriptParser.ParseResult?): Boolean {
        body = exprs[0] as Expression<Body>?
        return true
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return ""
    }

    override fun get(event: Event?): Array<String?>? {
        val b = body!!.getSingle(event)
        if (b != null){
            return arrayOf(b.getBody())
        }
        return null
    }
}