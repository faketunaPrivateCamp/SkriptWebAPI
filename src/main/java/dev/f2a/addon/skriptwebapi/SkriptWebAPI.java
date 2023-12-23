package dev.f2a.addon.skriptwebapi;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import dev.f2a.addon.skriptwebapi.elements.Types;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class SkriptWebAPI extends JavaPlugin {

    private static Plugin plugin;
    private SkriptAddon skriptAddon;
    private static final HttpRequestFactory HTTP_REQUEST_FACTORY = new NetHttpTransport().createRequestFactory();

    public static HttpRequestFactory getHttpRequestFactory() {
        return HTTP_REQUEST_FACTORY;
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        Plugin skript = this.getServer().getPluginManager().getPlugin("Skript");

        if (skript == null) {
            this.getLogger().info("Failed to find active skript instance. Plugin is not running or installed?");
            return;
        }

        skriptAddon = Skript.registerAddon(this);

        try {
            new Types();
            skriptAddon.loadClasses("dev.f2a.addon.skriptwebapi.elements.server");
            skriptAddon.loadClasses("dev.f2a.addon.skriptwebapi.elements.request");
        } catch (IOException e) {
            this.getLogger().warning("Plugin failed to register some feature due to exception. See error log.");
            e.printStackTrace();
        }

        this.getLogger().info("SkriptWebAPI has been loaded.");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("SkriptWebAPI has been disabled.");
    }
}
