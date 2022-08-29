package net.horizonsend.ion.server.planets

import net.kyori.adventure.text.Component

/** @see StationaryPlanetaryObject */
data class RougePlanet(
	override val name: String,
	override val x: Int,
	override val z: Int,
	override val orbitingObjects: Array<OrbitingPlanetaryObject> = arrayOf()
) : StationaryPlanetaryObject {
	override val displayName: Component = Component.text(name)

	// We only bother implementing this to avoid a editor warning
	// The code only identifies them by name so that alone is enough
	override fun equals(other: Any?): Boolean = (other is Star) && name == other.name
	override fun hashCode(): Int = name.hashCode()
}