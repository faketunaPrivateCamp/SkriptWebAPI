package jp.faketuna.addon.skriptwebapi

import ch.njol.skript.Skript
import ch.njol.skript.SkriptAddon
import org.bukkit.plugin.java.JavaPlugin

class SkriptWebAPI: JavaPlugin() {

    private val instance: SkriptWebAPI = this
    private lateinit var addon: SkriptAddon


    override fun onEnable() {
        val skript = server.pluginManager.getPlugin("Skript")

        if (skript == null) {
            logger.info("null!")
        }
        addon = Skript.registerAddon(this)
        loadSkriptElements()
        logger.info("[SkriptWebAPI] has been enabled")
    }

    override fun onDisable() {
    }


    fun getInstance(): SkriptWebAPI{
        return instance
    }

    fun getAddonInstance(): SkriptAddon{
        return addon
    }

    private fun loadSkriptElements(){
        loadAPIServerElements()
        loadWebRequestElements()
    }

    private fun loadAPIServerElements(){

    }

    private fun loadWebRequestElements(){
        addon.loadClasses("jp.faketuna.addon.skriptwebapi.elements.request")
    }
}