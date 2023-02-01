package jp.faketuna.addon.skriptwebapi.elements.request.expressions

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.doc.Since
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.util.Kleenean
import jp.faketuna.addon.skriptwebapi.api.server.connection.HttpConnection
import jp.faketuna.addon.skriptwebapi.api.server.objects.Header
import org.bukkit.event.Event
import java.net.URL

@Name("Send web request")
@Description("Send a web request with specified method and header and body.\n" +
        "It returns response of web request.")
@Examples("set {_header} to blank header\n" +
        "set {_header}'s properties \"Content-Type\" to \"applicaiton/json\"\n" +
        "set {_header}'s properties \"User-Agent\" to \"SkriptWebAPI/0.0.1\"\n" +
        "set {_header}'s properties \"Custom\" to \"Custom header\"\n" +
        "set {_response} to response of delete request to \"http://domain/\" with header {_header} and body \"{\"\"json\"\":\"\"body\"\"}\"\n" +
        "broadcast \"response: %{_response}'s response body%\"")
@Since("0.0.4")
class ExprWebRequest: SimpleExpression<HttpConnection>() {

    companion object{
        init {
            Skript.registerExpression(ExprWebRequest::class.java,
                HttpConnection::class.java,
                ExpressionType.SIMPLE,
                "[skeb] response of (delete|1¦post|2¦put|3¦get|4¦patch) request to [url] %string% [[(with|and)] header %-header%] [[(with|and)] body %-string%]"
            )
        }
    }

    private lateinit var targetURI: Expression<String>
    private lateinit var requestHeader: Expression<Header>
    private lateinit var requestBody: Expression<String>
    private var requestMethod = "Request Method!"

    override fun getReturnType(): Class<out HttpConnection> {
        return HttpConnection::class.java
    }

    override fun isSingle(): Boolean {
        return true
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
            4 -> "PATCH"
            else -> {
                Skript.error("Request method is not supported!")
                return false
            }
        }
        return true
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return "to string"
    }

    override fun get(event: Event): Array<HttpConnection>? {
        val request = HttpConnection()
        try {
            if (::requestHeader.isInitialized) request.setRequestHeader(requestHeader.getSingle(event)!!.getHeader())
            if (::requestBody.isInitialized) request.setRequestBody(requestBody.getSingle(event))
            request.setRequestURL(URL(targetURI.getSingle(event)))
            request.setRequestMethod(requestMethod)
            request.fetchData()
        } catch (e: Exception){
            Skript.error(e.message)
            return null
        }
        return arrayOf(request)
    }
}