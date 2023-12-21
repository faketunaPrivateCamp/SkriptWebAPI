package jp.faketuna.addon.skriptwebapi.api.server.objects

class ContextPath(private val contextPath: String) {
    fun getContextPath(): String{
        return this.contextPath
    }
    override fun toString(): String {
        return this.contextPath
    }
}