package net.starlegacy.feature.multiblock.starshipweapon.cannon

import net.starlegacy.feature.multiblock.LegacyMultiblockShape
import net.starlegacy.feature.multiblock.starshipweapon.SignlessStarshipWeaponMultiblock
import net.starlegacy.feature.starship.active.ActiveStarship
import net.starlegacy.feature.starship.subsystem.weapon.primary.PlasmaCannonWeaponSubsystem
import net.horizonsend.ion.server.miscellaneous.Vec3i
import org.bukkit.block.BlockFace

object PlasmaCannonStarshipWeaponMultiblock : SignlessStarshipWeaponMultiblock<PlasmaCannonWeaponSubsystem>() {
	override fun createSubsystem(starship: ActiveStarship, pos: Vec3i, face: BlockFace): PlasmaCannonWeaponSubsystem {
		return PlasmaCannonWeaponSubsystem(starship, pos, face)
	}

	override fun LegacyMultiblockShape.buildStructure() {
		at(+0, +0, +0).sponge()
		at(+0, +0, +1).ironBlock()
		at(+0, +0, +2).furnace()
	}
}
