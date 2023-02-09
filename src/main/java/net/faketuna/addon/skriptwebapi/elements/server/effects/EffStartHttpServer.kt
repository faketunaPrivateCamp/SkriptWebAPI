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
import net.faketuna.addon.skriptwebapi.api.server.config.SKebConfig
import net.faketuna.addon.skriptwebapi.api.server.http.HttpAPIServer
import org.bukkit.event.Event

@Name("Start HTTP server")
@Description("This effect is used to start the HTTP server.\n" +
        "If you don't specified the port number it uses config value (default 8080)")
@Examples("start http server in port 8000")
@Since("0.0.1")
class EffStartHttpServer: Effect() {

    companion object{
        init {
            Skript.registerEffect(EffStartHttpServer::class.java, "[skeb] start http [api] server [in port %-integer%]")
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