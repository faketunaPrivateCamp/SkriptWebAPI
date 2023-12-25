package dev.f2a.addon.skriptwebapi.internal.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import dev.f2a.addon.skriptwebapi.SkriptWebAPI;
import dev.f2a.addon.skriptwebapi.internal.events.HttpRequestEvent;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.net.InetSocketAddress;

public class SkebHttpServer {

    private static boolean isRunning = false;
    private static HttpServer httpServer;

    public static boolean isRunning() {
        return isRunning;
    }

    public static boolean runServer(int port) {
        if(isRunning) return false;
        try {
            var server =  HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext("/", new SkebHttpHandler());
            server.start();
            httpServer = server;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean stopServer() {
        if(!isRunning) return false;
        httpServer.stop(0);
        return true;
    }

    private static class SkebHttpHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            Bukkit.getScheduler().callSyncMethod(SkriptWebAPI.getPlugin(), () -> {
                Bukkit.getPluginManager().callEvent(new HttpRequestEvent(exchange));
                return null;
            });
        }
    }
}
