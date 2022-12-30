package jp.faketuna.addon.skriptwebapi.api.server.http

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import jp.faketuna.addon.skriptwebapi.SkriptWebAPI
import jp.faketuna.addon.skriptwebapi.api.server.events.DeleteRequestEvent
import jp.faketuna.addon.skriptwebapi.api.server.events.GetRequestEvent
import jp.faketuna.addon.skriptwebapi.api.server.events.PostRequestEvent
import jp.faketuna.addon.skriptwebapi.api.server.events.PutRequestEvent
import org.bukkit.Bukkit

class HttpAPIHandler: HttpHandler {

    override fun handle(exchange: HttpExchange) {
        if (exchange.requestMethod == "GET"){
            Bukkit.getScheduler().callSyncMethod(SkriptWebAPI.Static.getPlugin()) {
                Bukkit.getPluginManager().callEvent(GetRequestEvent(exchange))
            }
            return
        }
        if (exchange.requestMethod == "POST"){
            Bukkit.getScheduler().callSyncMethod(SkriptWebAPI.Static.getPlugin()) {
                Bukkit.getPluginManager().callEvent(PostRequestEvent(exchange))
            }
            return
        }
        if (exchange.requestMethod == "PUT"){
            Bukkit.getScheduler().callSyncMethod(SkriptWebAPI.Static.getPlugin()) {
                Bukkit.getPluginManager().callEvent(PutRequestEvent(exchange))
            }
            return
        }
        if (exchange.requestMethod == "DELETE"){
            Bukkit.getScheduler().callSyncMethod(SkriptWebAPI.Static.getPlugin()) {
                Bukkit.getPluginManager().callEvent(DeleteRequestEvent(exchange))
            }
            return
        }
        else {
            exchange.close()
        }
    }
}