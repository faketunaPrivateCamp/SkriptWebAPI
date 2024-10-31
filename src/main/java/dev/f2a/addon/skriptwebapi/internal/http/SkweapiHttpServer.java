package dev.f2a.addon.skriptwebapi.internal.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import dev.f2a.addon.skriptwebapi.SkriptWebAPI;
import dev.f2a.addon.skriptwebapi.internal.events.HttpRequestEvent;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.net.InetSocketAddress;

public class SkweapiHttpServer {

    private static boolean isRunning = false;
    private static HttpServer httpServer;

    public static boolean isRunning() {
        return isRunning;
    }

    public static SkweapiServerStatus runServer(int port, String contextPath) {
        if(isRunning) return SkweapiServerStatus.SERVER_IS_RUNNING;
        if(!contextPath.startsWith("/")) return SkweapiServerStatus.CONTEXT_PATH_NOT_START_WITH_SLASH;
        if(!contextPath.endsWith("/")) return SkweapiServerStatus.CONTEXT_PATH_NOT_END_WITH_SLASH;

        try {
            var server =  HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext(contextPath, new SkweapiHttpHandler());
            server.start();
            httpServer = server;
            isRunning = true;
        } catch (IOException e) {
            e.printStackTrace();
            return SkweapiServerStatus.EXCEPTION_OCCURRED;
        }
        return SkweapiServerStatus.OPERATION_SUCCESS;
    }

    public static SkweapiServerStatus stopServer() {
        if(!isRunning) return SkweapiServerStatus.SERVER_IS_STOPPED;
        httpServer.stop(0);
        isRunning = false;
        return SkweapiServerStatus.OPERATION_SUCCESS;
    }

    private static class SkweapiHttpHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            Bukkit.getScheduler().callSyncMethod(SkriptWebAPI.getPlugin(), () -> {
                Bukkit.getPluginManager().callEvent(new HttpRequestEvent(exchange));
                return null;
            });
        }
    }
}
