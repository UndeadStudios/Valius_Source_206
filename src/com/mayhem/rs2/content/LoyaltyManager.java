package com.mayhem.rs2.content;

import com.mayhem.core.task.Task;
import com.mayhem.rs2.entity.Entity;
import com.mayhem.rs2.entity.item.Item;
import com.mayhem.rs2.entity.player.Player;
import com.mayhem.rs2.entity.player.net.out.impl.SendMessage;

/**
 * @author Andy || ReverendDread Mar 2, 2017
 */
public class LoyaltyManager extends Task {
	
	/**
	 * 
	 * Constructs a new object.
	 * @param player {@link Player} The player.
	 * @param delay The delay until execute fires.
	 */
	public LoyaltyManager(Player player, int delay) {
		super(player, delay);
		this.player = player;
	}

	//The player
	private Player player;

	@Override
	public void execute() {
		if (player == null) {
			this.stop();
			return;
		}
		//Bank rewards
		if (player.getBank().getFreeSlots() > 2) {
			player.getBank().add(new Item(13307, 5000));
			player.getBank().add(new Item(995, 500000));
			player.send(new SendMessage("[LoyaltyManager]: You've been awarded for playing for 1 hour. Your reward is your bank."));
		} else {
			//do something else if bank is full
		}
	}

	@Override
	public void onStop() {

	}


	
}
