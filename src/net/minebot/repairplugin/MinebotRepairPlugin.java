package net.minebot.repairplugin;
import java.util.*;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.*;
import org.bukkit.plugin.java.JavaPlugin;

public class MinebotRepairPlugin extends JavaPlugin implements Listener {

	private HashSet<String> informedPlayers = new HashSet<String>();
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);

		getLogger().info("Enabled.");
	}

	public void onDisable() {
		getLogger().info("Disabled.");
	}

	public short calcDurability(short current, short max, int toRemove) {
		return (short) (current >= 0 ? 0 : current - toRemove >= 0 ? current - toRemove : 0);
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if (informedPlayers.contains(p.getName()))
			return;
		if (e.getBlock().getType().equals(Material.IRON_BLOCK)) {
			repairMessage(p, "You have placed an anvil! Right click with the tool or armor you wish " +
				"to repair, with the necessary materials in your inventory.");
			informedPlayers.add(p.getName());
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK
				|| !e.getClickedBlock().getType().equals(Material.IRON_BLOCK))
			return;

		Player p = e.getPlayer();
		ItemStack item = p.getItemInHand();
		
		getLogger().info("DEBUG: " + p.getName() + " repairing " + item.getType().toString());
		
		if (p.getItemInHand().getType().equals(Material.AIR))
			return;
		
		ItemStack rmaterial = RepairInfo.repairMaterial(item.getType());
		if (rmaterial == null) {
			return;
		}
		
		// prevent fishing rods from doing something silly
		if (p.getItemInHand().getType().equals(Material.FISHING_ROD))
			e.setCancelled(true);
		
		if (!p.hasPermission("minebotrepair.use")) {
			repairMessage(p, "You do not have permission to repair items.");
			return;
		}
		
		if (p.getItemInHand().getDurability() == 0) {
			repairMessage(p, "That item does not require repair.");
			getLogger().info("DEBUG: " + "Item durability 0, doesn't need repair");
			return;
		}
		
		if (!p.getInventory().contains(rmaterial.getType())) {
			String costItemName = rmaterial.getType().toString().toLowerCase().replace('_', ' ');
			repairMessage(p, "You need 1 " + costItemName + " to repair this item.");
			getLogger().info("DEBUG: " + "Doesn't have 1 " + costItemName);
			return;
		}
		
		double rfactor = RepairInfo.repairFactor(item.getType());
		getLogger().info("DEBUG: " + "rfactor is " + rfactor);
		// Decrease factor by 1/3 per enchantment
		for (int i = 0; i < item.getEnchantments().size(); i++) {
			rfactor = rfactor * 0.5;
			getLogger().info("DEBUG: " + "Decreasing rfactor due to enchantment, now: " + rfactor);
		}
		
		short maxDurability = item.getData().getItemType().getMaxDurability();
		short curDurability = item.getDurability();
		short toRemove = (short)(maxDurability * rfactor);
		
		short newDurability = (short) ((curDurability - toRemove) <= 0 ? 0 : (curDurability - toRemove));
		
		getLogger().info("DEBUG: " + "Max dur: " + maxDurability + " Current dur: " + curDurability +
				" To remove: " + toRemove + " New dur: " + newDurability);
		
		item.setDurability(newDurability);
		
		ItemStack ritem = rmaterial.clone();
		ritem.setAmount(1);
		p.getInventory().removeItem(ritem);
		p.updateInventory();
		
		if (newDurability == 0)
			repairMessage(p, "The item is fully repaired!");
		else 
			repairMessage(p, "The item is partly repaired.");
		
	}
	
	private void repairMessage(Player p, String message) {
		p.sendMessage(ChatColor.AQUA + "[Repair] " + ChatColor.WHITE + message);
	}
}
