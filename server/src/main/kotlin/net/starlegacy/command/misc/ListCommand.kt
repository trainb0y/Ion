package net.starlegacy.command.misc

import co.aikar.commands.annotation.CommandAlias
import net.horizonsend.ion.common.extensions.userError
import net.horizonsend.ion.common.database.cache.nations.NationCache
import net.horizonsend.ion.server.features.cache.PlayerCache
import net.starlegacy.command.SLCommand
import net.horizonsend.ion.common.database.Oid
import net.horizonsend.ion.common.database.schema.nations.Nation
import net.starlegacy.feature.progression.Levels
import net.starlegacy.feature.progression.SLXP
import net.starlegacy.util.multimapOf
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object ListCommand : SLCommand() {
	@Suppress("Unused")
	@CommandAlias("list|who")
	fun execute(sender: CommandSender) {
		val players: Collection<Player> = Bukkit.getOnlinePlayers()

		if (players.isEmpty()) {
			sender.userError("No players online")
			return
		}

		val nationMap = multimapOf<Oid<Nation>?, Player>()

		for (player in players) {
			val playerNation: Oid<Nation>? = PlayerCache[player].nationOid
			nationMap[playerNation].add(player)
		}

		val nationIdsSortedByName: List<Oid<Nation>?> = nationMap.keySet()
			.sortedBy { id -> id?.let { NationCache[it].name } ?: "_" }

		for (nationId: Oid<Nation>? in nationIdsSortedByName) {
			val members: Collection<Player> = nationMap[nationId].sortedBy { SLXP[it] }

			val nationText = nationId?.let { "<dark_purple>${NationCache[it].name}" } ?: "<yellow><italic>Nationless"

			sender.sendRichMessage(
				"$nationText <dark_purple>(<light_purple>${members.count()}<dark_purple>)<dark_gray>:<gray> ${
					members.joinToString { player ->
// 					val nationPrefix = PlayerCache[player].nationTag?.let { "<reset>$it " } ?: ""
						return@joinToString "<gray>[<aqua>${Levels[player]}<gray>] " +
// 							"$nationPrefix" +
							"<gray>${player.name}"
					}
				}"
			)
		}
	}
}
