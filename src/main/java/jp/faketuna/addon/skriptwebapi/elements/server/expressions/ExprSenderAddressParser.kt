package jp.faketuna.addon.skriptwebapi.elements.server.expressions

import ch.njol.skript.Skript
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.util.Kleenean
import jp.faketuna.addon.skriptwebapi.api.server.objects.ContextPath
import jp.faketuna.addon.skriptwebapi.api.server.objects.SenderAddress
import org.bukkit.event.Event

class ExprSenderAddressParser: SimpleExpression<String>() {

    companion object{
        init {
            Skript.registerExpression(ExprSenderAddressParser::class.java, String::class.java, ExpressionType.COMBINED, "%senderaddr% parsed as text")
        }
    }

    private var senderAddress: Expression<SenderAddress>? = null

    override fun getReturnType(): Class<out String> {
        return String::class.java
    }

    override fun isSingle(): Boolean {
        return true
    }

    override fun init(exprs: Array<out Expression<*>>, matchedPattern: Int, isDelayed: Kleenean?, parser: SkriptParser.ParseResult?): Boolean {
        senderAddress = exprs[0] as Expression<SenderAddress>?
        return true
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return ""
    }

    override fun get(event: Event?): Array<String?>? {
        val a = senderAddress!!.getSingle(event)
        if (a != null){
            return arrayOf(a.getSenderAddress())
        }
        return null
    }
}