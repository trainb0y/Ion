package net.horizonsend.ion.server.planets

interface StationaryPlanetaryObject: PlanetaryObject {
	/** The X coordinate. */
	val x: Int

	/** The Z coordinate. */
	val z: Int
}