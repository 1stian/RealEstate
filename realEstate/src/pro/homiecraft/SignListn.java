package pro.homiecraft;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignListn implements Listener {
	@EventHandler
	public void signCreate(SignChangeEvent event){
		Player player = event.getPlayer();
		Block block = event.getBlock();
		
		if (realEstate.perms.has(player, "estate.create")){
			String[] line = event.getLines();
						
			if (line[0].equalsIgnoreCase("[estate]")){
				player.sendMessage("You have created an estate sign!! testy testy :)");
					
				event.setLine(0, "[Estate]");
				event.setLine(1, "No Owner");
				event.setLine(2, "Testy2");
				event.setLine(3, "Testy3");
					
				block.getState().update();
			}
		}else{
			player.sendMessage("You don't have permissions to create estates!");
		}
	}
}
