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
import net.faketuna.addon.skriptwebapi.api.server.objects.Header
import org.bukkit.event.Event

@Name("Get blank header")
@Description("It returns blank header uses for web request.")
@Examples("set {_header} to blank header")
@Since("0.0.1")
class ExprGetBlankHeader: SimpleExpression<Header>() {

    companion object{
        init {
            Skript.registerExpression(ExprGetBlankHeader::class.java, Header::class.java, ExpressionType.COMBINED, "[skeb] blank header")
        }
    }


    override fun getReturnType(): Class<out Header> {
        return Header::class.java
    }

    override fun isSingle(): Boolean {
        return true
    }

    override fun init(exprs: Array<out Expression<*>>, matchedPattern: Int, isDelayed: Kleenean?, parser: SkriptParser.ParseResult?): Boolean {
        return true
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return ""
    }

    override fun get(event: Event?): Array<Header> {
        return arrayOf(Header(HashMap()))
    }
}