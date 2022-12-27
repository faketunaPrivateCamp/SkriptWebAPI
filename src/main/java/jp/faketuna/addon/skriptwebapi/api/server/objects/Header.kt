package jp.faketuna.addon.skriptwebapi.api.server.objects

class Header(private val header: Map<String, List<String>>){
    fun getHeader(): Map<String, List<String>>{
        return this.header
    }
    override fun toString(): String {
        return this.header.toString()
    }
}