package net.horizonsend.ion.server

import co.aikar.commands.PaperCommandManager
import net.horizonsend.ion.server.commands.GuideCommand
import net.horizonsend.ion.server.listeners.bukkit.BlockFadeListener
import net.horizonsend.ion.server.listeners.bukkit.BlockFormListener
import net.horizonsend.ion.server.listeners.bukkit.ChunkLoadListener
import net.horizonsend.ion.server.listeners.bukkit.InventoryClickListener
import net.horizonsend.ion.server.listeners.bukkit.InventoryCloseListener
import net.horizonsend.ion.server.listeners.bukkit.InventoryDragListener
import net.horizonsend.ion.server.listeners.bukkit.InventoryInteractListener
import net.horizonsend.ion.server.listeners.bukkit.InventoryMoveItemListener
import net.horizonsend.ion.server.listeners.bukkit.PlayerDeathListener
import net.horizonsend.ion.server.listeners.bukkit.PlayerFishListener
import net.horizonsend.ion.server.listeners.bukkit.PlayerItemConsumeListener
import net.horizonsend.ion.server.listeners.bukkit.PlayerJoinListener
import net.horizonsend.ion.server.listeners.bukkit.PlayerKickListener
import net.horizonsend.ion.server.listeners.bukkit.PlayerLoginListener
import net.horizonsend.ion.server.listeners.bukkit.PlayerQuitListener
import net.horizonsend.ion.server.listeners.bukkit.PlayerTeleportListener
import net.horizonsend.ion.server.listeners.bukkit.PotionSplashListener
import net.horizonsend.ion.server.listeners.bukkit.PrepareAnvilListener
import net.horizonsend.ion.server.listeners.bukkit.PrepareItemEnchantListener
import net.horizonsend.ion.server.listeners.luckperms.UserDataRecalculateListener
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.FurnaceRecipe
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.RecipeChoice
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.ShapelessRecipe
import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused") // Plugin entrypoint
class IonServer : JavaPlugin() {
	override fun onEnable() {
		arrayOf(
			BlockFadeListener(),
			BlockFormListener(),
			ChunkLoadListener(this),
			InventoryClickListener(),
			InventoryCloseListener(),
			InventoryDragListener(),
			InventoryInteractListener(),
			InventoryMoveItemListener(),
			PlayerDeathListener(),
			PlayerFishListener(),
			PlayerItemConsumeListener(),
			PlayerJoinListener(),
			PlayerKickListener(),
			PlayerLoginListener(),
			PlayerQuitListener(),
			PlayerTeleportListener(),
			PotionSplashListener(),
			PrepareAnvilListener(),
			PrepareItemEnchantListener()
		).forEach {
			server.pluginManager.registerEvents(it, this)
		}

		server.addRecipe(FurnaceRecipe(NamespacedKey(this, "prismarine_bricks_recipe"), ItemStack(Material.PRISMARINE_BRICKS), Material.PRISMARINE, 1f, 200))

		val bellRecipe = ShapedRecipe(NamespacedKey(this, "bell_recipe"), ItemStack(Material.BELL))
		bellRecipe.shape("wow", "szs", "zzz")
		bellRecipe.setIngredient('w', RecipeChoice.MaterialChoice(Material.STICK))
		bellRecipe.setIngredient('o', RecipeChoice.MaterialChoice(Material.OAK_LOG))
		bellRecipe.setIngredient('s', RecipeChoice.MaterialChoice(Material.IRON_BLOCK))
		bellRecipe.setIngredient('z', RecipeChoice.MaterialChoice(Material.GOLD_BLOCK))
		server.addRecipe(bellRecipe)

		val enderpearlRecipe = ShapedRecipe(NamespacedKey(this, "enderpearl_recipe"), ItemStack(Material.ENDER_PEARL))
		enderpearlRecipe.shape("wow", "oso", "wow")
		enderpearlRecipe.setIngredient('w', RecipeChoice.MaterialChoice(Material.OBSIDIAN))
		enderpearlRecipe.setIngredient('o', RecipeChoice.MaterialChoice(Material.EMERALD))
		enderpearlRecipe.setIngredient('s', RecipeChoice.MaterialChoice(Material.DIAMOND_BLOCK))
		server.addRecipe(enderpearlRecipe)

		arrayOf(
			Material.WHITE_WOOL,
			Material.ORANGE_WOOL,
			Material.MAGENTA_WOOL,
			Material.LIGHT_BLUE_WOOL,
			Material.YELLOW_WOOL,
			Material.LIME_WOOL,
			Material.PINK_WOOL,
			Material.GRAY_WOOL,
			Material.LIGHT_GRAY_WOOL,
			Material.CYAN_WOOL,
			Material.PURPLE_WOOL,
			Material.BLUE_WOOL,
			Material.BROWN_WOOL,
			Material.GREEN_WOOL,
			Material.RED_WOOL,
			Material.BLACK_WOOL
		).forEach {
			val woolType = ShapelessRecipe(
				NamespacedKey(this, "${it.name.lowercase()}_string_recipe"),
				ItemStack(Material.STRING, 4)
			)
			woolType.addIngredient(1, it)
			server.addRecipe(woolType)
		}

		val gunpowderRecipe = ShapelessRecipe(NamespacedKey(this, "gunpowder_recipe"), ItemStack(Material.GUNPOWDER))
		gunpowderRecipe.addIngredient(Material.REDSTONE)
		gunpowderRecipe.addIngredient(Material.FLINT)
		gunpowderRecipe.addIngredient(Material.SAND)
		gunpowderRecipe.addIngredient(Material.CHARCOAL)
		server.addRecipe(gunpowderRecipe)

		PaperCommandManager(this).apply {
			registerCommand(GuideCommand())
		}

		UserDataRecalculateListener()
	}
}