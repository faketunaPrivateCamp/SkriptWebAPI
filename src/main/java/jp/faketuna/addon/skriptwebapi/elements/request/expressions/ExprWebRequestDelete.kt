package jp.faketuna.addon.skriptwebapi.elements.request.expressions

import ch.njol.skript.Skript
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.lang.util.SimpleExpression
import ch.njol.util.Kleenean
import jp.faketuna.addon.skriptwebapi.api.server.objects.Header
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.bukkit.event.Event
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class ExprWebRequestDelete: SimpleExpression<HttpURLConnection>() {

    companion object{
        init {
            Skript.registerExpression(ExprWebRequestDelete::class.java,
                HttpURLConnection::class.java,
                ExpressionType.COMBINED,
                "[skeb] response of delete request to [url] %string% [(with|and)] header %header% [(with|and)] body %string%"
            )
        }
    }

    private lateinit var targetURI: Expression<String>
    private lateinit var requestHeader: Expression<Header>
    private lateinit var requestBody: Expression<String>

    override fun getReturnType(): Class<out HttpURLConnection> {
        return HttpURLConnection::class.java
    }

    override fun isSingle(): Boolean {
        return true
    }

    override fun init(exprs: Array<out Expression<*>>, matchedPattern: Int, isDelayed: Kleenean?, parser: SkriptParser.ParseResult?): Boolean {
        targetURI = exprs[0] as Expression<String>
        requestHeader = exprs[1] as Expression<Header>
        requestBody = exprs[2] as Expression<String>
        return true
    }

    override fun toString(event: Event?, debug: Boolean): String {
        return "to string"
    }

    override fun get(event: Event): Array<HttpURLConnection?>? {
        val response: HttpURLConnection?
        try {
            response = sendRequest(event)
        } catch (e: Exception){
            return null
        }
        if (response != null){
            return arrayOf(response)
        }
        return null
    }

    private fun sendRequest(e: Event): HttpURLConnection?{
        val uri = targetURI.getSingle(e) ?: return null
        val url = URL(uri)
        val header: Header? = requestHeader.getSingle(e)
        val body: String? = requestBody.getSingle(e)

        var result: HttpURLConnection? = null
        runBlocking {
            withContext(Dispatchers.IO) {
                with(url.openConnection() as HttpURLConnection) {
                    requestMethod = "DELETE"
                    connectTimeout = 1000
                    if (header != null) {
                        for (map in header.getHeader()) {
                            setRequestProperty(map.key, map.value.joinToString())
                        }
                    }
                    if (body != null) {
                        doOutput = true
                        outputStream.write(body.toString().toByteArray())
                        outputStream.flush()
                        outputStream.close()

                    }
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        result = this
                    }
                }
            }
        }
        return result
    }
}