package io.github.micalhl.ignoreplayer.api

import org.bukkit.entity.Player
import taboolib.expansion.getDataContainer
import java.util.UUID

/**
 * IgnorePlayer
 * io.github.micalhl.ignoreplayer.api.IgnoreAPI
 *
 * @author xiaomu
 * @since 2022/9/23 9:23 PM
 */
object IgnoreAPI {

    fun Player.addIgnore(uniqueId: UUID) {
        val ignores = getDataContainer()["ignores"] ?: ""
        val builder = StringBuilder()
        builder.append(ignores)
        builder.append(if (ignores.isEmpty()) uniqueId.toString() else ",$uniqueId")
        getDataContainer()["ignores"] = builder.toString()
    }

    fun Player.removeIgnore(uniqueId: UUID) {
        val ignores = getDataContainer()["ignores"] ?: ""
        if (ignores == uniqueId.toString()) {
            getDataContainer()["ignores"] = ""
            return
        }
        if (ignores.startsWith(uniqueId.toString())) {
            ignores.replace("$uniqueId,", "")
        } else {
            ignores.replace(",$uniqueId", "")
        }
        getDataContainer()["ignores"] = ignores
    }

    fun Player.getIgnores(): List<UUID> {
        val ignores = getDataContainer()["ignores"]
        return if (ignores.isNullOrEmpty()) emptyList() else if (ignores.contains(",")) ignores.split(",")
            .map { UUID.fromString(it) } else arrayListOf<UUID>().also { it.add(UUID.fromString(ignores)) }
    }
}