package net.horizonsend.ion.features.blasters

import net.starlegacy.util.randomDouble
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player

enum class Weapon(val material: Material, val cooldown: Int) {
	BLASTER_PISTOL(Material.NETHERITE_SWORD, 20) {
		override fun onLeftClick(player: Player) {
			projectiles.add(Projectile(player.eyeLocation, player, 8.0))

			player.world.playSound(
				player.eyeLocation,
				Sound.ENTITY_FIREWORK_ROCKET_BLAST,
				SoundCategory.PLAYERS,
				0.5f,
				randomDouble(0.3, 0.7).toFloat()
			)
		}
	},
	BLASTER_RIFLE(Material.NETHERITE_PICKAXE, 2) {
		override fun onLeftClick(player: Player) {
			projectiles.add(Projectile(player.eyeLocation, player, 16.0))

			player.world.playSound(
				player.eyeLocation,
				Sound.ENTITY_FIREWORK_ROCKET_BLAST,
				SoundCategory.PLAYERS,
				0.5f,
				randomDouble(0.3, 0.7).toFloat()
			)
		}

		override fun onRightClick(player: Player) = onLeftClick(player)
	},
	BLASTER_SHOTGUN(Material.NETHERITE_SHOVEL, 40) {
		override fun onLeftClick(player: Player) {
			projectiles.add(Projectile(player.eyeLocation, player, 8.0))

			player.world.playSound(
				player.eyeLocation,
				Sound.ENTITY_FIREWORK_ROCKET_BLAST,
				SoundCategory.PLAYERS,
				0.5f,
				randomDouble(0.3, 0.7).toFloat()
			)
		}
	},
	BLASTER_SNIPER(Material.NETHERITE_AXE, 80) {
		override fun onLeftClick(player: Player) {
			projectiles.add(Projectile(player.eyeLocation, player, 8.0))

			player.world.playSound(
				player.eyeLocation,
				Sound.ENTITY_FIREWORK_ROCKET_BLAST,
				SoundCategory.PLAYERS,
				0.5f,
				randomDouble(0.3, 0.7).toFloat()
			)
		}
	},
	BLASTER_CANNON(Material.NETHERITE_HOE, 160) {
		override fun onLeftClick(player: Player) {
			projectiles.add(Projectile(player.eyeLocation, player, 2.0))

			player.world.playSound(
				player.eyeLocation,
				Sound.ENTITY_FIREWORK_ROCKET_BLAST,
				SoundCategory.PLAYERS,
				0.5f,
				randomDouble(0.3, 0.7).toFloat()
			)
		}
	};

	open fun onLeftClick(player: Player) {}

	open fun onRightClick(player: Player) {}

	open fun onHit(target: LivingEntity) {}

  companion object {
		val blasterMaterials = values().associateBy { it.material }
  }
}
