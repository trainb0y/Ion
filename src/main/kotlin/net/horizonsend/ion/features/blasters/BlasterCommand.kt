package net.horizonsend.ion.features.blasters

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import co.aikar.commands.annotation.Default
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

@CommandAlias("giveblaster")
@CommandPermission("ion.give.blasters")
class BlasterCommand: BaseCommand() {
	@Default
	fun onGiveBlasterCommand(player: Player) {
		val blaster = ItemStack(Material.NETHERITE_SWORD)

		val itemMeta = blaster.itemMeta

		itemMeta.setCustomModelData(1)

		blaster.itemMeta = itemMeta

		player.inventory.addItem(blaster)
	}
}