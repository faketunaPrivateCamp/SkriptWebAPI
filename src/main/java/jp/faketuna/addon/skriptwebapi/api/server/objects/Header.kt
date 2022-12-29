package jp.faketuna.addon.skriptwebapi.api.server.objects

class Header(private var header: Map<String, List<String>>){
    fun getHeader(): Map<String, List<String>>{
        return this.header
    }
    fun setValue(key: String, value: List<String>){
        val mutableMap = this.header.toMutableMap()
        mutableMap[key] = value
        this.header = mutableMap
    }
    override fun toString(): String {
        return this.header.toString()
    }
}