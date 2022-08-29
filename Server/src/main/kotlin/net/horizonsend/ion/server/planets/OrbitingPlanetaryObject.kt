package net.horizonsend.ion.server.planets

interface OrbitingPlanetaryObject: PlanetaryObject {
	/** The X offset from the parent object. */
	val orbitOffsetX: Int

	/** The Z offset from the parent object. */
	val orbitOffsetZ: Int

	/** The size of the orbit on the X axis. */
	val orbitSizeX: Int

	/** The size of the orbit on the Z axis. */
	val orbitSizeZ: Int
}