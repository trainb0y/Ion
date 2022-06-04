package net.horizonsend.ion.features.blasters

import com.destroystokyo.paper.event.server.ServerTickStartEvent
import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

val projectiles = mutableListOf<Projectile>()

val noCancelEntities = mutableListOf<Entity>()

class BlasterListener : Listener {
	@EventHandler
	fun onEntityDamageEvent(event: EntityDamageByEntityEvent) {
		if (noCancelEntities.contains(event.entity)) {
			noCancelEntities.remove(event.entity)
			return
		}

		val player = event.damager as? Player ?: return

		if (fireBlaster(player, player.inventory.itemInMainHand)) event.isCancelled = true
	}

	@EventHandler
	fun onPlayerInteractEvent(event: PlayerInteractEvent) {
		if (fireBlaster(event.player, event.item)) event.isCancelled = true
	}

	private fun fireBlaster(source: Player, blaster: ItemStack?): Boolean {
		if (blaster == null) return false

		Weapon.values().find { it.material == blaster.type }?.onLeftClick(source)

//		if (blaster.type != Material.NETHERITE_SWORD) return false
//		if (blaster.itemMeta!!.customModelData == 0) return false
//		if (source.getCooldown(Material.NETHERITE_SWORD) > 0) return false
//
//		source.setCooldown(Material.NETHERITE_SWORD, 10)
//
//		projectiles.add(Projectile(source.eyeLocation, source, 16.0))
//		source.playSound(Sound.sound(Key.key("minecraft", "entity.firework_rocket.blast"), Sound.Source.PLAYER, 0.5f, 0.5f))

		return true
	}

	@EventHandler
	fun onServerTickEvent(event: ServerTickStartEvent) {
		var goUntil = projectiles.size
		var index = 0

		while (index < goUntil) {
			val projectile = projectiles[index]
			if (projectile.tick()) {
				projectiles.removeAt(index)
				goUntil--
			} else {
				index++
			}
		}
	}
}