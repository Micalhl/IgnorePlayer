package io.github.micalhl.ignoreplayer.events

import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.expansion.releaseDataContainer
import taboolib.expansion.setupDataContainer

/**
 * IgnorePlayer
 * io.github.micalhl.ignoreplayer.events.DatabaseEvents
 *
 * @author xiaomu
 * @since 2022/9/23 9:25 PM
 */
object DatabaseEvents {

    @SubscribeEvent
    fun e(e: PlayerJoinEvent) {
        e.player.setupDataContainer()
    }

    @SubscribeEvent
    fun e(e: PlayerQuitEvent) {
        e.player.releaseDataContainer()
    }
}