package jp.faketuna.addon.skriptwebapi.api.server.objects

class TargetHost(private val targetHost: String) {
    fun getTargetHost(): String{
        return this.targetHost
    }
    override fun toString(): String {
        return this.targetHost
    }
}