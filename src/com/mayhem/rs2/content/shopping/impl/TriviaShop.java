package com.mayhem.rs2.content.shopping.impl;

import com.mayhem.rs2.content.interfaces.InterfaceHandler;
import com.mayhem.rs2.content.interfaces.impl.QuestTab;
import com.mayhem.rs2.content.shopping.Shop;
import com.mayhem.rs2.entity.item.Item;
import com.mayhem.rs2.entity.player.Player;
import com.mayhem.rs2.entity.player.net.out.impl.SendMessage;

/**
 * Trivia store
 * 
 * @author Dez
 */
public class TriviaShop extends Shop {

	/**
	 * Id of Trivia shop
	 */
	public static final int SHOP_ID = 362;

	/**
	 * Price of items in Trivia store
	 * 
	 * @param id
	 * @return
	 */
	public static final int getPrice(int id) {
	switch (id) {
	
	case 2677:
		return 2;
	case 2801:
		return 6;
	case 989:
		return 1;
	case 10115:
		return 100;
	case 10117:
		return 100;
	case 13283:
	case 13284:
	case 13285:
	case 13286:
	case 13287:
		return 15;
	case 20433:
	case 20436:
	case 20439:
	case 20442:
		return 10;
	case 19727:
		return 25;
		
	

	}
	return 2147483647;
}

/**
 * All items in trivia
 */
public TriviaShop() {
	super(SHOP_ID, new Item[] {
			new Item(2677),
			new Item(2801),
			new Item(989),
			new Item(10115),
			new Item(10117),
			new Item(13283),
			new Item(13284),
			new Item(13285),
			new Item(13286),
			new Item(13287),
			new Item(20433),
			new Item(20436),
			new Item(20439),
			new Item(20442),
			new Item(19727)
			}, 
			false, "Trivia point Shop");
}

	@Override
	public void buy(Player player, int slot, int id, int amount) {
		if (!hasItem(slot, id))
			return;
		if (get(slot).getAmount() == 0)
			return;
		if (amount > get(slot).getAmount()) {
			amount = get(slot).getAmount();
		}

		Item buying = new Item(id, amount);

		if (!player.getInventory().hasSpaceFor(buying)) {
			if (!buying.getDefinition().isStackable()) {
				int slots = player.getInventory().getFreeSlots();
				if (slots > 0) {
					buying.setAmount(slots);
					amount = slots;
				} else {
					player.getClient().queueOutgoingPacket(new SendMessage("You do not have enough inventory space to buy this item."));
				}
			} else {
				player.getClient().queueOutgoingPacket(new SendMessage("You do not have enough inventory space to buy this item."));
				return;
			}
		}

		if (player.gettriviaPoints() < amount * getPrice(id)) {
			player.getClient().queueOutgoingPacket(new SendMessage("You do not have enough Trivia points to buy that."));
			return;
		}

		player.settriviaPoints(player.gettriviaPoints() - amount * getPrice(id));

		//InterfaceHandler.writeText(new QuestTab(player));

		player.getInventory().add(buying);
		update();
	}

	@Override
	public int getBuyPrice(int id) {
		return 0;
	}

	@Override
	public String getCurrencyName() {
		return "TriviaPoints";
	}

	@Override
	public int getSellPrice(int id) {
		return getPrice(id);
	}

	@Override
	public boolean sell(Player player, int id, int amount) {
		player.getClient().queueOutgoingPacket(new SendMessage("You cannot sell items to this shop."));
		return false;
	}
}
