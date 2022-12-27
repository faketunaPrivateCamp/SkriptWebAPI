package jp.faketuna.addon.skriptwebapi.api.server.objects

class Header(private val header: String) {
    fun getHeader(): String{
        return this.header
    }
    override fun toString(): String {
        return this.header
    }
}