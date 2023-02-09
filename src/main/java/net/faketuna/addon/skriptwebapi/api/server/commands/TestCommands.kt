package net.faketuna.addon.skriptwebapi.api.server.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class TestCommands: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (command.name.equals("skebtest", ignoreCase = true)) {
            return true
        }

        return true
    }
}