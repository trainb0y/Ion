package net.horizonsend.ion.server.planets

import net.kyori.adventure.text.Component

interface PlanetaryObject {
	/** The name of the planetary object, used internally, and not displayed to any players. */
	val name: String

	/** The display name of the planetary object, can be used to apply formatting. */
	val displayName: Component

	/** Planetary objects orbiting this planetary object. */
	val orbitingObjects: Array<OrbitingPlanetaryObject>
}