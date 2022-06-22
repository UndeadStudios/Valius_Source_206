package com.mayhem.rs2.content;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mayhem.core.definitions.ItemDefinition;
import com.mayhem.rs2.entity.Entity;
import com.mayhem.rs2.entity.World;
import com.mayhem.rs2.entity.item.Item;
import com.mayhem.rs2.entity.item.impl.GroundItemHandler;
import com.mayhem.rs2.entity.player.Player;
import com.mayhem.rs2.entity.player.net.Client;
import com.mayhem.rs2.entity.player.net.out.impl.SendMessage;
import com.mayhem.rs2.entity.player.net.out.impl.SendSidebarInterface;

public class LootingBag {
	/*public Player player;
	public List<Item> items;

	public boolean viewingLootBag = false;
	public boolean addingItemsToLootBag = false;

	public int selectedItem = -1;
	public int selectedSlot = -1;

	public LootingBag(Player player) {
		this.player = player;
		items = new ArrayList<>();
	}

	public void onDeath(Client o) {
		if (o == null) {
			return;
		}
		Entity killer = player.getCombat().getDamageTracker().getKiller();
		for (Iterator<Item> iterator = items.iterator(); iterator.hasNext();) {
			Item item = iterator.next();

			if (item == null) {
				continue;
			}
			if (item.getId() <= 0 || item.getAmount() <= 0) {
				continue;
			}
			if(!Item.getDefinition(item.getId()).isTradable()) {
				continue;
			}
			GroundItemHandler.add(item, player.getLocation(), World.getPlayers()[killer.getIndex()]);
			
			iterator.remove();
		}
		sendItems();
	}

	public static final int LOOTING_BAG = 11941;

	public static boolean isLootBag(Player player, int itemId) {
		return itemId == 11941;
	}

	public boolean handleButton(int buttonId) {
		if (buttonId == 142131) {
			closeLootbag();
			return true;
		}
		return false;
	}

	public void openLootbagWithdraw() {
		onClose();
		sendItems();
		player.getClient().queueOutgoingPacket(new SendSidebarInterface(3, 37342));
		viewingLootBag = true;
	}

	public void openLootbagView() {
		onClose();
		sendItemsViewOnly();
		player.getClient().queueOutgoingPacket(new SendSidebarInterface(3, 36342));
		viewingLootBag = true;
	}

	public void openLootbagAdd() {
		onClose();
		sendInventoryItems();
		player.getClient().queueOutgoingPacket(new SendSidebarInterface(3, 37343));
		addingItemsToLootBag = true;
	}

	public boolean handleClickItem(int id, int amount) {
		if (viewingLootBag) {
			// removeItemFromLootBag(id, amount);
			removeMultipleItemsFromBag(id, amount);
			return true;
		}
		if (addingItemsToLootBag) {
			addItemToLootBag(id, amount);
			return true;
		}
		return false;
	}

	public int findIndexInLootBag(int id) {
		for (Item item : items) {
			if (item.getId() == id) {
				return items.indexOf(item);
			}
		}
		return -1;
	}

	public boolean containsItem(int id) {
		for (Item item : items) {
			if (item.getId() == id) {
				return true;
			}
		}
		return false;
	}

	public void removeMultipleItemsFromBag(int id, int amount) {
		if (!player.getInterfaceManager().hasBankOpen()) {
			player.send(new SendMessage("You can only withdraw in a bank."));
			return;
		}
		if (amount >= Integer.MAX_VALUE) {
			amount = countItems(id);
		}
		int count = 0;
		while (containsItem(id)) {
			if (!removeItemFromLootBag(id, amount)) {
				return;
			}
			if (Item.getDefinition(id).isStackable()) {
				count += amount;
			} else {
				count++;
			}
			if (count >= amount) {
				return;
			}
		}
	}

	public boolean removeItemFromLootBag(int id, int amount) {
		if (items.size() <= 0) {
			return false;
		}
		int index = findIndexInLootBag(id);
		if (index == -1) {
			return false;
		}
		Item item = items.get(index);
		if (item == null) {
			return false;
		}
		if (item.getId() <= 0 || item.getAmount() <= 0) {
			return false;
		}
		if (player.getInventory().getFreeSlots() <= 0) {
			return false;
		}
		if (player.getInventory().getItemAmount(id) + amount >= Integer.MAX_VALUE || player.getInventory().getItemAmount(id) + amount <= 0) {
			return false;
		}

		int amountToAdd = 0;
		if ((items.get(items.indexOf(item)).getAmount()) > amount) {
			amountToAdd = amount;
			items.get(items.indexOf(item)).add(-amount);
		} else {
			amountToAdd = item.getAmount();
			items.remove(index);
		}

		player.getInventory().add(item.getId(), amountToAdd);

		System.out.println("Removing item: " + item.getId());
		sendItems();
		sendInventoryItems();

		return true;
	}

	public int countItems(int id) {
		int count = 0;
		for (Item item : items) {
			if (item.getId() == id) {
				count += item.getAmount();
			}
		}
		return count;
	}

	public void addItemToLootBag(int id, int amount) {

		if (items.size() >= 28) {
			player.send(new SendMessage("Bag is full."));
			return;
		}

		if (amount >= Integer.MAX_VALUE) {
			amount = player.getInventory().getItemAmount(id);
		}
		if (id <= 0 || amount <= 0) {
			return;
		}
		if (id == 11942 || id == 11941) {
			return;
		}

		if (countItems(id) + amount >= Integer.MAX_VALUE || countItems(id) + amount <= 0) {
			return;
		}

		int bagSpotsLeft = 28 - items.size();
		boolean stackable = Item.getDefinition(id).isStackable();
		boolean bagContainsItem = containsItem(id);
		if (amount > bagSpotsLeft) {
			if (!(stackable && bagContainsItem)) {
				amount = bagSpotsLeft;
			}
		}

		List<Integer> amounts = player.getInventory().remove(id, amount);
		// int amt = player.getItems().deleteItemAndReturnAmount(id, amount);

		int count = 0;
		for (int amt : amounts) {
			if (!addItemToList(id, amt)) {
				break;
			}
			count++;
			if (count >= amount) {
				break;
			}
			// addItemToList(id, amt);
		}

		resetItems();
		sendItems();
		sendInventoryItems();
	}

	public boolean addItemToList(int id, int amount) {
		for (Item item : items) {
			if (item.getId() == id) {
				if (item.getAmount() + amount >= Integer.MAX_VALUE) {
					return false;
				}
				if (player.getItems().isStackable(id)) {
					item.incrementAmount(amount);
					return true;
				}
			}
		}
		items.add(new Item(id, amount));
		return true;
	}

	public void closeLootbag() {
		Client c = (Client) player;
		c.setSidebarInterface(3, 3213);
		onClose();
	}

	public void deposit() {
		if (!player.getItems().playerHasItem(LOOTING_BAG)) {
			return;
		}
		if (!player.inWild()) {
			player.sendMessage("You can only deposit in the wilderness.");
			return;
		}
		openLootbagAdd();
	}

	public void withdraw() {
		if (!player.getItems().playerHasItem(LOOTING_BAG)) {
			return;
		}
		if (!player.inBank()) {
			player.sendMessage("You can only withdraw in the bank.");
			return;
		}
		openLootbagWithdraw();
	}

	public void onClose() {
		viewingLootBag = false;
		addingItemsToLootBag = false;
	}

	public void sendItems() {
		if (!player.getItems().playerHasItem(LOOTING_BAG)) {
			return;
		}
		final int START_ITEM_INTERFACE = 47342;
		for (int i = 0; i < 28; i++) {
			int id = 0;
			int amt = 0;

			if (i < items.size()) {
				Item item = items.get(i);
				if (item != null) {
					id = item.getId();
					amt = item.getAmount();
				}
			}

			Client c = (Client) player;

			if (id <= 0) {
				id = -1;
			}
			PlayerFunction.itemOnInterface(c, START_ITEM_INTERFACE + i, 0, id, amt);
			// System.out.println("Sent bag item: " + id);
		}
	}
	
	public void sendItemsViewOnly() {
		if (!player.getItems().playerHasItem(LOOTING_BAG)) {
			return;
		}
		final int START_ITEM_INTERFACE = 47372;
		for (int i = 0; i < 28; i++) {
			int id = 0;
			int amt = 0;

			if (i < items.size()) {
				Item item = items.get(i);
				if (item != null) {
					id = item.getId();
					amt = item.getAmount();
				}
			}

			Client c = (Client) player;

			if (id <= 0) {
				id = -1;
			}
			PlayerFunction.itemOnInterface(c, START_ITEM_INTERFACE + i, 0, id, amt);
			// System.out.println("Sent bag item: " + id);
		}
	}

	public void sendInventoryItems() {
		if (!player.getItems().playerHasItem(LOOTING_BAG)) {
			return;
		}
		final int START_ITEM_INTERFACE = 27342;
		for (int i = 0; i < 28; i++) {
			int id = 0;
			int amt = 0;

			if (i < player.playerItems.length) {
				id = player.playerItems[i];
				amt = player.playerItemsN[i];
			}

			Client c = (Client) player;
			PlayerFunction.itemOnInterface(c, START_ITEM_INTERFACE + i, 0, id - 1, amt);

			// System.out.println("Sent inventory item: " + id);
		}
	}

	private String getShortAmount(int amount) {
		if (amount <= 1) {
			return "";
		}
		String amountToString = "" + amount;
		if (amount > 1000000000) {
			amountToString = "@gre@" + (amount / 1000000000) + "B";
		} else if (amount > 1000000) {
			amountToString = "@gre@" + (amount / 1000000) + "M";
		} else if (amount > 1000) {
			amountToString = "@whi@" + (amount / 1000) + "K";
		}
		return amountToString;
	}

	private void resetItems() {
		player.getItems().resetItems(3214);
		player.getFunction().requestUpdates();
	}
}*/
}
