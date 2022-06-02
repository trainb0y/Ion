package net.horizonsend.ion.features.blasters

import org.bukkit.Color
import org.bukkit.FluidCollisionMode
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.entity.Damageable
import org.bukkit.entity.Player

class Projectile(
	private val location: Location,
	private val source: Player,
	private val distancePerTick: Double
) {
	private val stepsPerTick = distancePerTick.toInt() * 5
	private val data = Particle.DustOptions(Color.ORANGE, 0.5f)

	private val directionVector = location.direction.normalize().multiply(0.2)

	fun tick(): Boolean {
		if (!location.isChunkLoaded) return true

		val rayTraceResult = location.world.rayTrace(
			location,
			directionVector,
			distancePerTick,
			FluidCollisionMode.NEVER,
			false,
			0.5
		) {
			it != source
		}

		val hitEntity = rayTraceResult?.hitEntity as Damageable?

		if (hitEntity != null) {
			noCancelEntities.add(hitEntity)
			hitEntity.damage(5.0, source)
		}

		val distanceToHit = rayTraceResult?.hitPosition?.distance(location.toVector())

		for (step in 0 until ((distanceToHit ?: distancePerTick) * 5).toInt()) step()

		return distanceToHit != null
	}

	private fun step() {
		location.add(directionVector)

		location.world.spawnParticle(Particle.REDSTONE, location, 1, 0.0, 0.0, 0.0, 0.0, data, true)
	}
}