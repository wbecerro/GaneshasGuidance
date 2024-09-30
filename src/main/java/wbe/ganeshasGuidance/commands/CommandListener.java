package wbe.ganeshasGuidance.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import wbe.ganeshasGuidance.GaneshasGuidance;

public class CommandListener implements CommandExecutor {

    private GaneshasGuidance plugin = GaneshasGuidance.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("ganeshasguidance")) {
            Player player = null;
            if(sender instanceof Player) {
                player = (Player) sender;
            }

            if(args.length == 0 | args[0].equalsIgnoreCase("reload")) {
                if(!sender.hasPermission("ganeshasguidance.command.reload")) {
                    sender.sendMessage(GaneshasGuidance.messages.noPermission);
                    return false;
                }

                plugin.reloadConfiguration();
                sender.sendMessage(GaneshasGuidance.messages.reload);
            }
        }
        return true;
    }
}
