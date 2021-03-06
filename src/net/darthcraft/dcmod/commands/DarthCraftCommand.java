package net.darthcraft.dcmod.commands;

import net.darthcraft.dcmod.DarthCraft;
import net.darthcraft.dcmod.DC_Utils;
import net.darthcraft.dcmod.addons.BanManager;
import net.darthcraft.dcmod.player.PlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class DarthCraftCommand
{

    protected DarthCraft plugin;
    protected Server server;
    //
    protected DC_Utils util;
    protected PlayerManager playerManager;
    protected BanManager banManager;
    //
    private CommandSender commandSender;

    public DarthCraftCommand()
    {
    }

    public void setPlugin(DarthCraft plugin)
    {
        this.plugin = plugin;
        this.server = plugin.getServer();
        this.util = plugin.util;
        this.playerManager = plugin.playerManager;
        this.banManager = plugin.banManager;
    }

    public void setCommandSender(CommandSender sender)
    {
        this.commandSender = sender;
    }

    public boolean noPerms()
    {
        commandSender.sendMessage(ChatColor.RED + "You don't have access to that command");
        return true;
    }

    public boolean showUsage(Command command)
    {
        commandSender.sendMessage(ChatColor.YELLOW + "Usage: " + command.getUsage().replaceAll("<command>", command.getName()));
        return true;
    }

    public boolean consoleOnly()
    {
        warn("This command can only be executed from the console.");
        return true;
    }

    public boolean playerOnly()
    {
        warn("This command can only be executed by players.");
        return true;
    }

    public boolean warn(String message)
    {
        commandSender.sendMessage(ChatColor.YELLOW + message);
        return true;
    }

    @Deprecated
    public boolean msg(String message)
    {
        commandSender.sendMessage(ChatColor.GRAY + message);
        return true;
    }

    public abstract boolean run(CommandSender sender, Command cmd, String[] args);
}
