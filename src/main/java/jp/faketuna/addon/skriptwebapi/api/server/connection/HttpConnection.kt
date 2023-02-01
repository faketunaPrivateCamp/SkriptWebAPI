package jp.faketuna.addon.skriptwebapi.api.server.connection

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset
import java.util.*

class HttpConnection {

    private var connection: HttpURLConnection? = null
    private var responseBody: String? = null
    private var responseCode: Int? = null
    private var responseHeader: Map<String, List<String>>? = Collections.emptyMap()

    private var requestURL: URL? = null
    private var requestMethod: String? = null
    private var requestBody: String? = null
    private var requestHeader: Map<String, List<String>> = Collections.emptyMap()

    fun fetchData(){
        sendRequest()
    }

    private fun sendRequest(){
        runBlocking {
            withContext(Dispatchers.IO) {
                with(requestURL?.openConnection() as HttpURLConnection) {
                    requestMethod = this@HttpConnection.requestMethod
                    connectTimeout = 1000
                    if (requestHeader.isNotEmpty()){
                        for (map in requestHeader){
                            map.value.forEach {
                                setRequestProperty(map.key, it)
                            }
                        }
                    }
                    if (requestBody != null){
                        doOutput = true
                        outputStream.write(requestBody!!.toByteArray())
                        outputStream.flush()
                        outputStream.close()
                    }
                    connection = this
                }
                responseBody = connection?.inputStream?.readBytes()?.toString(Charset.defaultCharset())
                responseCode = connection?.responseCode
                responseHeader = connection?.headerFields
            }
        }
    }

    fun setRequestURL(url: URL?){
        this.requestURL = url
    }

    fun setRequestBody(body: String?){
        this.requestBody = body
    }

    fun setRequestMethod(method: String?){
        this.requestMethod = method
    }

    fun setRequestHeader(header: Map<String, List<String>>){
        this.requestHeader = header
    }


    fun getConnection(): HttpURLConnection? {
        return this.connection
    }

    fun getResponseBody(): String? {
        return this.responseBody
    }

    fun getResponseCode(): Int? {
        return this.responseCode
    }

    fun getResponseHeader(): Map<String, List<String>>? {
        return this.responseHeader
    }
}