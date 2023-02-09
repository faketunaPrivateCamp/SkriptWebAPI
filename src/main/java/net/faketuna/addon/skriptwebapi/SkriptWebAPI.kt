package net.faketuna.addon.skriptwebapi

import ch.njol.skript.Skript
import ch.njol.skript.SkriptAddon
import net.faketuna.addon.skriptwebapi.api.server.commands.TestCommands
import net.faketuna.addon.skriptwebapi.elements.Types
import org.bukkit.plugin.java.JavaPlugin

class SkriptWebAPI: JavaPlugin() {

    object Static{
        private lateinit var plugin: JavaPlugin

        fun setPlugin(plugin: JavaPlugin){
            this.plugin = plugin
        }

        fun getPlugin(): JavaPlugin{
            return this.plugin
        }
    }

    private val instance: SkriptWebAPI = this
    private lateinit var addon: SkriptAddon


    override fun onEnable() {
        Static.setPlugin(this)
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


    fun getInstance(): SkriptWebAPI {
        return instance
    }

    fun getAddonInstance(): SkriptAddon{
        return addon
    }

    private fun loadSkriptElements(){
        loadAPIServerElements()
        loadWebRequestElements()
        Types()
    }

    private fun loadMisc(){
        getCommand("skebtest")!!.setExecutor(TestCommands())
    }

    private fun loadAPIServerElements(){
        addon.loadClasses("jp.faketuna.addon.skriptwebapi.elements.server")
    }

    private fun loadWebRequestElements(){
        addon.loadClasses("jp.faketuna.addon.skriptwebapi.elements.request")
    }
}