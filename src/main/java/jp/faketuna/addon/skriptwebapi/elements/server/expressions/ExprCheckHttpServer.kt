package jp.faketuna.addon.skriptwebapi.elements.server.expressions

import ch.njol.skript.Skript
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.util.Kleenean
import jp.faketuna.addon.skriptwebapi.elements.server.effects.EffStartHttpServer
import org.bukkit.event.Event

class ExprCheckHttpServer: SimpleExpression<Boolean>() {

    companion object{
        init {
            Skript.registerExpression(ExprCheckHttpServer::class.java, Boolean::class.java, ExpressionType.COMBINED, "is running http [api] server")
        }
    }

    private lateinit var isRunning:Expression<Boolean>

    override fun getReturnType(): Class<out Boolean> {
        return Boolean::class.java
    }

    override fun isSingle(): Boolean {
        return true
    }

    override fun init(expr: Array<out Expression<*>>?, matchedPattern: Int, isDelayed: Kleenean?, parser: SkriptParser.ParseResult?): Boolean {
        isRunning = expr!![0] as Expression<Boolean>
        return true
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return ""
    }

    override fun get(e: Event?): Array<Boolean?> {
        val r = isRunning.getSingle(e)
        if (r != null){
            return arrayOf(r)
        } else {
            return arrayOf(false)
        }
    }
}