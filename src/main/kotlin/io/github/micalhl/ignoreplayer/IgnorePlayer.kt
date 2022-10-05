package io.github.micalhl.ignoreplayer

import taboolib.common.platform.Plugin
import taboolib.common.platform.function.getDataFolder
import taboolib.common.platform.function.onlinePlayers
import taboolib.expansion.setupDataContainer
import taboolib.expansion.setupPlayerDatabase
import java.io.File

/**
 * IgnorePlayer
 * io.github.micalhl.ignoreplayer.IgnorePlayer
 *
 * @author xiaomu
 * @since 2022/9/23 9:19 PM
 */
object IgnorePlayer : Plugin() {

    override fun onEnable() {
        setupPlayerDatabase(File(getDataFolder(), "player.db"))
        onlinePlayers().forEach { it.setupDataContainer() }
    }
}