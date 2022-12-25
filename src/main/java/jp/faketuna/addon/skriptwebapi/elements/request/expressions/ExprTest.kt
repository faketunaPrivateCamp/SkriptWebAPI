package jp.faketuna.addon.skriptwebapi.elements.request.expressions

import ch.njol.skript.Skript
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.util.Kleenean
import org.bukkit.entity.Player
import org.bukkit.event.Event

class ExprTest: SimpleExpression<String>() {

    companion object{
        init {
            Skript.registerExpression(ExprTest::class.java, String::class.java, ExpressionType.COMBINED, "[the] testexpr name %player%")
        }
    }

    private var player: Expression<Player>? = null

    override fun getReturnType(): Class<out String> {
        return String::class.java
    }

    override fun isSingle(): Boolean {
        return true
    }

    override fun init(exprs: Array<out Expression<*>>, matchedPattern: Int, isDelayed: Kleenean?, parser: SkriptParser.ParseResult?): Boolean {
        player = exprs[0] as Expression<Player>?
        return true
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return "name of player is: ${player!!.toString(event, debug)}}"
    }

    override fun get(event: Event?): Array<String?>? {
        val p = player!!.getSingle(event)
        if (p != null){
            return arrayOf(p.player!!.name)
        }
        return null
    }
}