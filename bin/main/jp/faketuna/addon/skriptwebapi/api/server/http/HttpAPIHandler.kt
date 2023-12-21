package jp.faketuna.addon.skriptwebapi.api.server.http

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import jp.faketuna.addon.skriptwebapi.SkriptWebAPI
import jp.faketuna.addon.skriptwebapi.api.server.events.GetRequestEvent
import org.bukkit.Bukkit

class HttpAPIHandler: HttpHandler {

    override fun handle(exchange: HttpExchange) {
        if (exchange.requestMethod == "GET"){
            Bukkit.getScheduler().callSyncMethod(SkriptWebAPI.Static.getPlugin()) {
                Bukkit.getPluginManager().callEvent(GetRequestEvent(exchange))
            }
        }
    }
}