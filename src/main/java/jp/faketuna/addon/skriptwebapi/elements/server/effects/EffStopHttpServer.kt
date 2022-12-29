package jp.faketuna.addon.skriptwebapi.elements.server.effects

import ch.njol.skript.Skript
import ch.njol.skript.lang.Effect
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import jp.faketuna.addon.skriptwebapi.api.server.http.HttpAPIServer
import org.bukkit.event.Event

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