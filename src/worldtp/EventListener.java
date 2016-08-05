package worldtp;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.Listener;
import cn.nukkit.lang.TranslationContainer;
import cn.nukkit.level.Level;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

class EventListener<T extends PluginBase> implements Listener {
	T plugin;
	
	public EventListener(T plugin) {
		this.plugin = plugin;
	}
	
	/**
	 * @param sender
	 * @param command
	 * @param label
	 * @param args
	 * @return
	 */
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(new TranslationContainer("commands.generic.ingame"));
			return true;
		}
		if (args.length < 1) {
			return false;
		}
		
		Level level = plugin.getServer().getLevelByName(args[0]);
		if (level == null) {
			if (plugin.getServer().loadLevel(args[0])) {
				level = plugin.getServer().getLevelByName(args[0]);
				((Player)sender).teleport(level.getSpawnLocation());
				sender.sendMessage(TextFormat.DARK_AQUA + "월드 " + args[0] + "로 이동했습니다.");
				return true;
			} else {
				sender.sendMessage(TextFormat.RED + "해당 월드는 존재하지 않습니다.");
				return true;
			}
		} else {
			if (level == ((Player)sender).getLevel()) {
				sender.sendMessage(TextFormat.RED + "이미 해당 월드에 있습니다.");
				return true;
			}
			((Player)sender).teleport(level.getSpawnLocation());
			sender.sendMessage(TextFormat.DARK_AQUA + "월드 " + args[0] + "로 이동했습니다.");
			return true;
		}
	}
}
