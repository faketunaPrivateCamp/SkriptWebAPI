package jp.faketuna.addon.skriptwebapi.api.server.objects

class Body(private val body: String) {
    fun getBody(): String{
        return this.body
    }
    override fun toString(): String {
        return this.body
    }
}