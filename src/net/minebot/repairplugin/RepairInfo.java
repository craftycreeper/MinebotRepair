package net.minebot.repairplugin;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class RepairInfo {

	private static final Set<Material> WOOD_ITEMS = new HashSet<Material>(
			Arrays.asList(new Material[] { Material.WOOD_AXE,
					Material.WOOD_PICKAXE, Material.WOOD_HOE,
					Material.WOOD_SPADE, Material.WOOD_SWORD }));

	private static final Set<Material> LEATHER_ITEMS = new HashSet<Material>(
			Arrays.asList(new Material[] { Material.LEATHER_BOOTS,
					Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET,
					Material.LEATHER_LEGGINGS, Material.BOW }));

	private static final Set<Material> STONE_ITEMS = new HashSet<Material>(
			Arrays.asList(new Material[] { Material.STONE_AXE,
					Material.STONE_HOE, Material.STONE_PICKAXE,
					Material.STONE_SPADE, Material.STONE_SWORD }));

	private static final Set<Material> IRON_ITEMS = new HashSet<Material>(
			Arrays.asList(new Material[] { Material.IRON_AXE,
					Material.IRON_HOE, Material.IRON_PICKAXE,
					Material.IRON_SPADE, Material.IRON_SWORD,
					Material.IRON_BOOTS, Material.IRON_CHESTPLATE,
					Material.IRON_HELMET, Material.IRON_LEGGINGS,
					Material.SHEARS }));

	private static final Set<Material> GOLD_ITEMS = new HashSet<Material>(
			Arrays.asList(new Material[] { Material.GOLD_AXE,
					Material.GOLD_HOE, Material.GOLD_PICKAXE,
					Material.GOLD_SPADE, Material.GOLD_SWORD,
					Material.GOLD_BOOTS, Material.GOLD_CHESTPLATE,
					Material.GOLD_HELMET, Material.GOLD_LEGGINGS }));

	private static final Set<Material> DIAMOND_ITEMS = new HashSet<Material>(
			Arrays.asList(new Material[] { Material.DIAMOND_AXE,
					Material.DIAMOND_HOE, Material.DIAMOND_PICKAXE,
					Material.DIAMOND_SPADE, Material.DIAMOND_SWORD,
					Material.DIAMOND_BOOTS, Material.DIAMOND_CHESTPLATE,
					Material.DIAMOND_HELMET, Material.DIAMOND_LEGGINGS }));
	
	private static final Set<Material> STRING_ITEMS = new HashSet<Material>(
			Arrays.asList(new Material[] { Material.FISHING_ROD }));

	private static final Set<Material> COST_1_ITEMS = new HashSet<Material>(
			Arrays.asList(new Material[] { Material.WOOD_SPADE,
					Material.STONE_SPADE, Material.IRON_SPADE,
					Material.GOLD_SPADE, Material.DIAMOND_SPADE,
					Material.WOOD_HOE, Material.STONE_HOE, Material.IRON_HOE,
					Material.GOLD_HOE, Material.DIAMOND_HOE,
					Material.WOOD_SWORD, Material.STONE_SWORD,
					Material.IRON_SWORD, Material.GOLD_SWORD, Material.DIAMOND_SWORD,
					Material.SHEARS, Material.FISHING_ROD }));

	private static final Set<Material> COST_2_ITEMS = new HashSet<Material>(
			Arrays.asList(new Material[] { Material.WOOD_PICKAXE,
					Material.STONE_PICKAXE, Material.IRON_PICKAXE,
					Material.GOLD_PICKAXE, Material.DIAMOND_PICKAXE,
					Material.WOOD_AXE, Material.STONE_AXE, Material.IRON_AXE,
					Material.GOLD_AXE, Material.DIAMOND_AXE,
					Material.LEATHER_BOOTS, Material.IRON_BOOTS,
					Material.GOLD_BOOTS, Material.DIAMOND_BOOTS,
					Material.BOW }));

	private static final Set<Material> COST_3_ITEMS = new HashSet<Material>(
			Arrays.asList(new Material[] { Material.LEATHER_HELMET,
					Material.IRON_HELMET, Material.GOLD_HELMET,
					Material.DIAMOND_HELMET, Material.LEATHER_LEGGINGS,
					Material.IRON_LEGGINGS, Material.GOLD_LEGGINGS,
					Material.DIAMOND_LEGGINGS }));

	private static final Set<Material> COST_4_ITEMS = new HashSet<Material>(
			Arrays.asList(new Material[] { Material.LEATHER_CHESTPLATE,
					Material.IRON_CHESTPLATE, Material.GOLD_CHESTPLATE,
					Material.DIAMOND_CHESTPLATE }));

	public static ItemStack repairMaterial(Material type) {
		if (WOOD_ITEMS.contains(type))
			return new ItemStack(Material.WOOD);
		else if (LEATHER_ITEMS.contains(type))
			return new ItemStack(Material.LEATHER);
		else if (STONE_ITEMS.contains(type))
			return new ItemStack(Material.COBBLESTONE);
		else if (IRON_ITEMS.contains(type))
			return new ItemStack(Material.IRON_INGOT);
		else if (GOLD_ITEMS.contains(type))
			return new ItemStack(Material.GOLD_INGOT);
		else if (DIAMOND_ITEMS.contains(type))
			return new ItemStack(Material.DIAMOND);
		else if (STRING_ITEMS.contains(type))
			return new ItemStack(Material.STRING);
		else
			return null;
	}
	
	public static double repairFactor(Material type) {
		if (COST_1_ITEMS.contains(type))
			return 1.0;
		else if (COST_2_ITEMS.contains(type))
			return 0.5;
		else if (COST_3_ITEMS.contains(type))
			return 0.34;
		else if (COST_4_ITEMS.contains(type))
			return 0.25;
		else return 0;
	}
}
