package jp.faketuna.addon.skriptwebapi.api.server.objects

class SenderAddress(private val senderAddress: String) {
    fun getSenderAddress(): String{
        return this.senderAddress
    }
    override fun toString(): String {
        return this.senderAddress
    }
}