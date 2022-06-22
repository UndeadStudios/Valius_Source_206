package com.mayhem.rs2.content.shopping.impl;

import com.mayhem.rs2.content.interfaces.InterfaceHandler;
import com.mayhem.rs2.content.interfaces.impl.QuestTab;
import com.mayhem.rs2.content.shopping.Shop;
import com.mayhem.rs2.entity.item.Item;
import com.mayhem.rs2.entity.player.Player;
import com.mayhem.rs2.entity.player.net.out.impl.SendMessage;

/**
 * Voting store
 * 
 * @author Daniel
 */
public class VotingShop extends Shop {

	/**
	 * Id of Bounty shop
	 */
	public static final int SHOP_ID = 92;

	/**
	 * Price of items in Bounty store
	 * 
	 * @param id
	 * @return
	 */
	public static final int getPrice(int id) {
	switch (id) {
	
	case 2528:
	case 12789:
		return 15;
	case 989:
		return 4;
	case 299:
	return 1;
	case 12020:
		return 150;
	case 6199:
		return 20;
	case 20851:
		return 250;
	case 2572:
		return 100;
	case 12964:
	case 12966:
	case 12968:
	case 12970:
		return 20;
	case 12976:
	case 12978:
	case 12980:
	case 12982:
		return 50;
	case 12992:
	case 12994:
	case 12996:
	case 12998:
		return 70;
	case 13004:
	case 13006:
	case 13008:
	case 13010:
		return 90;
	case 13016:
	case 13018:
	case 13020:
	case 13022:
		return 110;
	case 13028:
	case 13030:
	case 13032:
	case 13034:
		return 120;
	case 13036:
	case 13038:
		return 350;
	case 1837:
	case 5607:
	case 2643:
		
		return 5;
	case 9470:
	case 9472:
		return 5;
	case 20784:
		return 800;
	case 13171:
	case 13167:
	case 13169:
		return 150;
	case 7478:
		return 20;
	case 6585:
		return 140;
	case 12954:
		return 100;
	case 4151:
		return 200;
	case 7462:
			return 20;
	case 6570:
		return 100;
	case 11840:
		return 60;
	case 12873:
		return 300;
	case 12881:
		return 250;
	case 12875:
		return 200;
	case 12877:
		return 400;
	case 12879:
		return 100;
	case 12883:
		return 350;
	case 13343:
		return 750;
	case 13344:
		return 500;
	case 20834:
		return 50;
	case 20836:
		return 50;
	case 20849:
		return 1;
	}
	return 2147483647;
}

/**
 * All items in Bounty store
 */
public VotingShop() {
	super(SHOP_ID, new Item[] {
			new Item(12020),
			new Item(299, 100),
			new Item(989, 100),
			new Item(7478),
			new Item(12789),
			new Item(6199),
			new Item(20851),
			new Item(2572),
			new Item(20849, 1000),
			new Item(20784),
			new Item(4151),
			new Item(6585),
			new Item(7462),
			new Item(12954),
			new Item(6570),
			new Item(1837), 
			new Item(5607), 
			new Item(9470),
			new Item(2643),
			new Item(12873),
			new Item(12875),
			new Item(12879),
			new Item(12881),
			new Item(12883),
			new Item(12877),
			new Item(13343),
			new Item(13344),
			new Item(20834),
			new Item(20836),
			
	}, false, "Vote Point Store");
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

		if (player.getVotePoints() < amount * getPrice(id)) {
			player.getClient().queueOutgoingPacket(new SendMessage("You do not have enough Vote points to buy that."));
			return;
		}

		player.setVotePoints(player.getVotePoints() - amount * getPrice(id));

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
		return "Vote points";
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
