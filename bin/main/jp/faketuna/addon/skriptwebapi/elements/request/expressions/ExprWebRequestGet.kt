package jp.faketuna.addon.skriptwebapi.elements.request.expressions

import ch.njol.skript.Skript
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.util.Kleenean
import org.bukkit.event.Event
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset

class ExprWebRequestGet: SimpleExpression<String>() {

    companion object{
        init {
            Skript.registerExpression(ExprWebRequestGet::class.java,
                String::class.java,
                ExpressionType.COMBINED,
                "send [the] get request to [url] %string% [with header %-string% [and body %-string%]]"
            )
        }
    }

    private lateinit var targetURI: Expression<String>
    private lateinit var requestHeader: Expression<String>
    private lateinit var requestBody: Expression<String>

    override fun getReturnType(): Class<out String> {
        return String::class.java
    }

    override fun isSingle(): Boolean {
        return true
    }

    override fun init(exprs: Array<out Expression<*>>, matchedPattern: Int, isDelayed: Kleenean?, parser: SkriptParser.ParseResult?): Boolean {
        targetURI = exprs[0] as Expression<String>
        if (exprs[1] != null){
            requestHeader = exprs[1] as Expression<String>
        }
        if (exprs[2] != null){
            requestBody = exprs[2] as Expression<String>
        }
        return true
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return "to string"
    }

    override fun get(event: Event): Array<String?>? {
        val response = sendRequest(event)
        if (response != null){
            return arrayOf(response)
        }
        return null
    }

    private fun sendRequest(e: Event): String?{
        val uri = targetURI!!.getSingle(e) ?: return null
        val url = URL(uri)
        var header: String? = null
        var body: String? = null

        if (::requestHeader.isInitialized){
            header = requestHeader.getAll(e).toString()
        }
        if (::requestBody.isInitialized){
            body = requestBody.getAll(e).toString()
        }

        with(url.openConnection() as HttpURLConnection){
            requestMethod = "GET"
            connectTimeout = 200
            if (header != null){
                setRequestProperty("Content-Type", header.toString())
            }
            if (body != null){
                doOutput = true
                outputStream.write(body.toString().toByteArray())
                outputStream.flush()
                outputStream.close()

            }
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return inputStream.readBytes().toString(Charset.defaultCharset())
            }
        }
        return null
    }
}