package net.faketuna.addon.skriptwebapi.elements.request.effects

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.doc.Since
import ch.njol.skript.lang.Effect
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import net.faketuna.addon.skriptwebapi.api.server.objects.Header
import org.bukkit.event.Event

@Name("Set request header properties")
@Description("Set the request header properties.\n" +
        "To edit you must initialize variable with `set {_header} to blank header` then `set {_header} properties %string% to %string%`")
@Examples("set {_header} to blank header\n" +
        "set {_header}'s properties \"Content-Type\" to \"applicaiton/json\"")
@Since("0.0.1")
class EffSetHeaderProperties: Effect() {

    companion object{
        init {
            Skript.registerEffect(EffSetHeaderProperties::class.java, "[skeb] set [the] %header%['s] (properties|prop) %string% to %string%")
        }
    }

    private lateinit var headerProperties: Expression<Header>
    private lateinit var headerKey: Expression<String>
    private lateinit var headerValue: Expression<String>

    override fun toString(event: Event?, debug: Boolean): String {
        return ""
    }

    override fun init(expr: Array<out Expression<*>>, matchedPattern: Int, isDelayed: Kleenean?, parser: SkriptParser.ParseResult?): Boolean {
        headerProperties = expr[0] as Expression<Header>
        headerKey = expr[1] as Expression<String>
        headerValue = expr[2] as Expression<String>
        return true
    }

    override fun execute(e: Event) {
        val key = headerKey.getSingle(e)!!
        val value = headerValue.getSingle(e)
        var list = headerProperties.getSingle(e)!!.getHeader()[key]
        if (list == null){
            list = listOf()
        }
        val mutableList = list.toTypedArray().toMutableList()
        mutableList.add(value.toString())
        headerProperties.getSingle(e)!!.setValue(key, mutableList)
    }
}