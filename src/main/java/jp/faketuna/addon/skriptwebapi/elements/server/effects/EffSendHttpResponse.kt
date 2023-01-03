package jp.faketuna.addon.skriptwebapi.elements.server.effects

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.doc.Since
import ch.njol.skript.lang.Effect
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import com.sun.net.httpserver.HttpExchange
import org.bukkit.event.Event

@Name("Response to web request")
@Description("This is an effect that can be used within a any receive request event.\n" +
        "It returns a response to request sender, You can set body and response code.\n" +
        "If you don't specified response code it returns 500 status code.")
@Examples("reply event-request with body \"{\"\"the\"\":\"\"body\"\"}\" and response code 200\n")
@Since("0.0.1")
class EffSendHttpResponse: Effect() {

    companion object{
        init {
            Skript.registerEffect(EffSendHttpResponse::class.java, "[skeb] reply %httpexchange% [to [the] request sender] [(with|and) body %-string%] [(with|and) response code %-integer%]")
        }
    }

    private var exchange: Expression<HttpExchange>? = null
    private var body: Expression<String>? = null
    private var statusCode: Expression<Int>? = null

    override fun toString(event: Event?, debug: Boolean): String {
        return ""
    }

    override fun init(expr: Array<out Expression<*>>?, matchedPattern: Int, isDelayed: Kleenean?, parser: SkriptParser.ParseResult?): Boolean {
        if (expr!![0] != null) {
            exchange = expr!![0] as Expression<HttpExchange>
        }
        if (expr!![1] != null) {
            body = expr!![1] as Expression<String>
        }
        if (expr!![2] != null){
            statusCode = expr!![2] as Expression<Int>
        }
        return true
    }

    override fun execute(e: Event?) {
        val ex = exchange!!.getSingle(e)
        var resBody = body!!.getSingle(e)
        var stCode = statusCode?.getSingle(e)
        if (resBody == null) resBody = ""
        if (stCode == null) stCode = 500
        ex!!.sendResponseHeaders(stCode, resBody.length.toLong())
        ex!!.responseBody.write(resBody.toByteArray())
        ex!!.responseBody.close()
    }
}