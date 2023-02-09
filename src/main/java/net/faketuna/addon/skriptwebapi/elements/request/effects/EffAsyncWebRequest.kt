package net.faketuna.addon.skriptwebapi.elements.request.effects

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.doc.Since
import ch.njol.skript.lang.Effect
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import net.faketuna.addon.skriptwebapi.SkriptWebAPI
import net.faketuna.addon.skriptwebapi.api.server.connection.HttpConnection
import net.faketuna.addon.skriptwebapi.api.server.events.*
import net.faketuna.addon.skriptwebapi.api.server.objects.Header
import org.bukkit.Bukkit
import org.bukkit.event.Event
import java.net.URL

@Name("Send a async delete web request")
@Description("Send a async delete web request with specified header and body.\n" +
        "It calls delete response event when connection successful.")
@Examples("set {_header} to blank header\n" +
        "set {_header}'s properties \"Content-Type\" to \"applicaiton/json\"\n" +
        "set {_header}'s properties \"User-Agent\" to \"SkriptWebAPI/0.0.1\"\n" +
        "set {_header}'s properties \"Custom\" to \"Custom header\"\n" +
        "send delete request to \"http://domain/\" with header {_header} and body \"{\"\"json\"\":\"\"body\"\"}\"\n")
@Since("0.0.3")
class EffAsyncWebRequest: Effect() {

    companion object{
        init {
            Skript.registerEffect(EffAsyncWebRequest::class.java,
                "[skeb] send [async] (delete|1¦post|2¦put|3¦get|4¦patch) [web] request to [url] %string% [[(with|and)] header %-header%] [[(with|and)] body %-string%]"
            )
        }
    }

    private lateinit var targetURI: Expression<String>
    private lateinit var requestHeader: Expression<Header>
    private lateinit var requestBody: Expression<String>
    private var requestMethod: String = "Request Method"

    override fun toString(event: Event?, debug: Boolean): String {
        return ""
    }

    override fun init(exprs: Array<out Expression<*>>, matchedPattern: Int, isDelayed: Kleenean?, parser: SkriptParser.ParseResult?): Boolean {
        targetURI = exprs[0] as Expression<String>
        if (exprs[1] != null) requestHeader = exprs[1] as Expression<Header>
        if (exprs[2] != null) requestBody = exprs[2] as Expression<String>
        requestMethod = when(parser!!.mark){
            0 -> "DELETE"
            1 -> "POST"
            2 -> "PUT"
            3 -> "GET"
            //4 -> "PATCH"
            else -> {
                Skript.error("That request method is not supported!")
                return false
            }
        }
        return true
    }

    override fun execute(event: Event) {
        val plugin = SkriptWebAPI.Static.getPlugin()
        Bukkit.getScheduler().runTaskAsynchronously(plugin, Runnable {
            val request = HttpConnection()
            try {
                if (::requestHeader.isInitialized) request.setRequestHeader(requestHeader.getSingle(event)!!.getHeader())
                if (::requestBody.isInitialized) request.setRequestBody(requestBody.getSingle(event))
                request.setRequestURL(URL(targetURI.getSingle(event)))
                request.setRequestMethod(requestMethod)
                request.fetchData()
                Bukkit.getScheduler().callSyncMethod(plugin){
                    when(requestMethod){
                        "DELETE" -> Bukkit.getPluginManager().callEvent(DeleteResponseEvent(request))
                        "POST" -> Bukkit.getPluginManager().callEvent(PostResponseEvent(request))
                        "PUT" -> Bukkit.getPluginManager().callEvent(PutResponseEvent(request))
                        "GET" -> Bukkit.getPluginManager().callEvent(GetResponseEvent(request))
                        //"PATCH" -> Bukkit.getPluginManager().callEvent(PatchResponseEvent(request))
                    }
                }
            } catch (e: Exception){
                Skript.error("Something went wrong with web request!")
                e.printStackTrace()
                return@Runnable
            }
        })
    }
}