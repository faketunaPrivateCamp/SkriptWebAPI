package jp.faketuna.addon.skriptwebapi.api.server.objects

class UserAgent(private val userAgent: String) {
    fun getUserAgent(): String{
        return this.userAgent
    }
    override fun toString(): String {
        return this.userAgent
    }
}