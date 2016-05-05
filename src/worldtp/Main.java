package worldtp;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.PluginBase;

public class Main extends PluginBase {
	EventListener<Main> listener;
	
	@Override
	public void onEnable() {
		listener = new EventListener<Main>(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		return listener.onCommand(sender, command, label, args);
	}
}
