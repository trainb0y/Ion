package net.horizonsend.ion.server.planets

import net.kyori.adventure.text.Component
import org.spongepowered.configurate.objectmapping.ConfigSerializable

/** @see OrbitingPlanetaryObject */
@ConfigSerializable
data class Planet(
	override val name: String,
	override val orbitOffsetX: Int,
	override val orbitOffsetZ: Int,
	override val orbitSizeX: Int,
	override val orbitSizeZ: Int,
	override val orbitingObjects: Array<OrbitingPlanetaryObject> = arrayOf()
) : OrbitingPlanetaryObject {
	override val displayName: Component = Component.text(name)

	// We only bother implementing this to avoid a editor warning
	// The code only identifies them by name so that alone is enough
	override fun equals(other: Any?): Boolean = (other is Star) && name == other.name
	override fun hashCode(): Int = name.hashCode()
}