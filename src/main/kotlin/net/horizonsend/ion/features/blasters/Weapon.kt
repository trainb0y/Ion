package net.horizonsend.ion.features.blasters

import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import kotlin.random.Random

enum class Weapon(val material: Material, val cooldown: Int) {
	BLASTER_PISTOL(Material.NETHERITE_SWORD, 20) {
		override fun onLeftClick(player: Player) {
			projectiles.add(Projectile(player.eyeLocation, player, 8.0))

			player.world.playSound(
				player.eyeLocation,
				Sound.ENTITY_FIREWORK_ROCKET_BLAST_FAR,
				SoundCategory.PLAYERS,
				0.5f,
				(Random.nextFloat() * .4f) + .5f
			)
		}
	},
	BLASTER_SHOTFUN(Material.NETHERITE_AXE, 20) {
		override fun onLeftClick(player: Player) {
			val eyeLocation = player.eyeLocation

			val shot1 = eyeLocation.clone()
			val shot2 = eyeLocation.clone()
			val shot3 = eyeLocation.clone()
			val shot4 = eyeLocation.clone()

			shot1.pitch += (Random.nextFloat() * 8) - 4
			shot1.yaw   += (Random.nextFloat() * 8) - 4
			shot2.pitch += (Random.nextFloat() * 8) - 4
			shot2.yaw   += (Random.nextFloat() * 8) - 4
			shot3.pitch += (Random.nextFloat() * 8) - 4
			shot3.yaw   += (Random.nextFloat() * 8) - 4
			shot4.pitch += (Random.nextFloat() * 8) - 4
			shot4.yaw   += (Random.nextFloat() * 8) - 4

			projectiles.add(Projectile(shot1, player, 8.0).apply { tick() })
			projectiles.add(Projectile(shot2, player, 8.0).apply { tick() })
			projectiles.add(Projectile(shot3, player, 8.0).apply { tick() })
			projectiles.add(Projectile(shot4, player, 8.0).apply { tick() })

			player.world.playSound(
				player.eyeLocation,
				Sound.ENTITY_FIREWORK_ROCKET_BLAST,
				SoundCategory.PLAYERS,
				0.5f,
				(Random.nextFloat() * .4f)
			)
		}
	};

	open fun onLeftClick(player: Player) {}

	open fun onRightClick(player: Player) {}

	open fun onHit(target: LivingEntity) {}
}
