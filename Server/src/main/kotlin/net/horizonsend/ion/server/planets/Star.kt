package net.horizonsend.ion.server.planets

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.spongepowered.configurate.objectmapping.ConfigSerializable

@ConfigSerializable
data class Star(
	override val name: String,
	val starColour: String,
	override val x: Int,
	override val z: Int,
	override val orbitingObjects: Array<OrbitingPlanetaryObject> = arrayOf()
) : PlanetaryObject {
	override val displayName: Component =
		Component.text(name.replaceFirstChar { it.uppercase() } + " System")
			.color(TextColor.fromHexString(starColour))

	// We only bother implementing this to avoid a editor warning
	// The code only identifies them by name so that alone is enough
	override fun equals(other: Any?): Boolean = (other is Star) && name == other.name
	override fun hashCode(): Int = name.hashCode()
}