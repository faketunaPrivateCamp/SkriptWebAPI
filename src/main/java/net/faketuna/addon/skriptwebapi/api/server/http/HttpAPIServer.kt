package net.faketuna.addon.skriptwebapi.api.server.http

import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer
import java.net.InetSocketAddress

class HttpAPIServer {
    object Server{
        private var isRunning = false
        private lateinit var httpServer: HttpServer
        private lateinit var httpHandler: HttpHandler

        fun isRunning(): Boolean {
            return this.isRunning
        }
        fun setRunningState(b: Boolean){
            this.isRunning = b
        }

        fun getHttpHandler(): HttpHandler {
            return this.httpHandler
        }
        fun setHttpHandler(httpHandler: HttpHandler){
            this.httpHandler = httpHandler
        }
        fun isHttpHandlerInitialized(): Boolean{
            return ::httpHandler.isInitialized
        }


        fun getHttpServer(): HttpServer{
            return this.httpServer
        }
        fun setHttpServer(HttpServer: HttpServer){
            this.httpServer = HttpServer
        }
        fun isHttpServerInitialized(): Boolean{
            return ::httpServer.isInitialized
        }

        fun run(port: Int): Boolean{
            if (isRunning()) return false
            try {
                val httpServer = HttpServer.create(InetSocketAddress(port), 0)
                httpServer.createContext("/", HttpAPIHandler())
                httpServer.start()
                setHttpServer(httpServer)
            } catch (e: Exception){
                e.printStackTrace()
                return false
            }
            return true
        }

        fun stop(): Boolean{
            if (!isRunning()) return false
            getHttpServer().stop(0)
            return true
        }
    }






}