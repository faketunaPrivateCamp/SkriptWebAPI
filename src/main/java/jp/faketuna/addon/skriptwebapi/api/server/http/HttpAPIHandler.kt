package jp.faketuna.addon.skriptwebapi.api.server.http

import jp.faketuna.addon.skriptwebapi.api.server.events.GetRequestEvent
import org.bukkit.Bukkit
import javax.xml.ws.spi.http.HttpExchange
import javax.xml.ws.spi.http.HttpHandler

class HttpAPIHandler: HttpHandler() {
    override fun handle(exchange: HttpExchange) {
        if (exchange.requestMethod == "GET"){
            Bukkit.getPluginManager().callEvent(GetRequestEvent(exchange))
        }
    }
}