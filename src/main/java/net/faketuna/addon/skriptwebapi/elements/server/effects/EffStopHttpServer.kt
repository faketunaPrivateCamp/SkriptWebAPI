package net.faketuna.addon.skriptwebapi.elements.server.effects

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.doc.Since
import ch.njol.skript.lang.Effect
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import net.faketuna.addon.skriptwebapi.api.server.http.HttpAPIServer
import org.bukkit.event.Event

@Name("Stop HTTP server")
@Description("This effect is used to stop the HTTP server.\n" +
        "Nothing special.")
@Examples("")
@Since("0.0.1")
class EffStopHttpServer: Effect() {

    companion object{
        init {
            Skript.registerEffect(EffStopHttpServer::class.java, "[skeb] stop http [api] server")
        }
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return ""
    }

    override fun init(expr: Array<out Expression<*>>?, matchedPattern: Int, isDelayed: Kleenean?, parser: SkriptParser.ParseResult?): Boolean {
        return true
    }

    override fun execute(e: Event?) {
        if (HttpAPIServer.Server.isRunning()){
            HttpAPIServer.Server.stop()
            HttpAPIServer.Server.setRunningState(false)
        }
    }
}