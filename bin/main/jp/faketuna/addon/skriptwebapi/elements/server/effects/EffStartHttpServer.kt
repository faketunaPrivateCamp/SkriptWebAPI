package jp.faketuna.addon.skriptwebapi.elements.server.effects

import ch.njol.skript.Skript
import ch.njol.skript.lang.Effect
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import jp.faketuna.addon.skriptwebapi.api.server.config.SKebConfig
import jp.faketuna.addon.skriptwebapi.api.server.http.HttpAPIServer
import org.bukkit.event.Event

class EffStartHttpServer: Effect() {

    companion object{
        init {
            Skript.registerEffect(EffStartHttpServer::class.java, "start http [api] server [in port %-integer%]")
        }
    }

    private lateinit var port: Expression<Int>

    override fun toString(event: Event?, debug: Boolean): String {
        return ""
    }

    override fun init(expr: Array<out Expression<*>>?, matchedPattern: Int, isDelayed: Kleenean?, parser: SkriptParser.ParseResult?): Boolean {
        port = expr!![0] as Expression<Int>
        return true
    }

    override fun execute(e: Event?) {
        val sp = port.getSingle(e)

        if (!HttpAPIServer.Server.isRunning()){
            HttpAPIServer.Server.run(sp ?: SKebConfig.Config.getHttpPort())
            HttpAPIServer.Server.setRunningState(true)
        }
    }
}