package io.github.micalhl.ignoreplayer.events

import io.github.micalhl.ignoreplayer.api.IgnoreAPI.getIgnores
import org.bukkit.entity.Player
import org.bukkit.event.player.AsyncPlayerChatEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.platform.util.onlinePlayers

/**
 * IgnorePlayer
 * io.github.micalhl.ignoreplayer.events.PlayerEvents
 *
 * @author xiaomu
 * @since 2022/9/23 9:20 PM
 */
object PlayerEvents {

    @SubscribeEvent
    fun e(e: AsyncPlayerChatEvent) {
        val players = hashSetOf<Player>()
        onlinePlayers.forEach {
            if (it.getIgnores().contains(e.player.uniqueId)) {
                players.add(it)
            }
        }
        e.recipients.removeAll(players)
    }
}