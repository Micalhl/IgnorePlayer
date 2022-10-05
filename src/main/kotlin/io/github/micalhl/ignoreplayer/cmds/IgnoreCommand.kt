package io.github.micalhl.ignoreplayer.cmds

import io.github.micalhl.ignoreplayer.api.IgnoreAPI.addIgnore
import io.github.micalhl.ignoreplayer.api.IgnoreAPI.getIgnores
import io.github.micalhl.ignoreplayer.inv.IgnoreInventory.openMenu
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.command.command
import taboolib.common.platform.function.onlinePlayers
import taboolib.platform.util.sendLang

/**
 * IgnorePlayer
 * io.github.micalhl.ignoreplayer.cmds.IgnoreCommand
 *
 * @author xiaomu
 * @since 2022/9/23 9:21 PM
 */
object IgnoreCommand {

    @Awake(LifeCycle.ENABLE)
    fun register() {
        command("ignore") {
            dynamic("player") {
                suggestion<Player>(uncheck = true) { _, _ ->
                    onlinePlayers().map { it.name }
                }

                execute<Player> { user, context, _ ->
                    val name = context.argument(0)
                    val player = Bukkit.getPlayerExact(name)
                    if (player == null) {
                        user.sendLang("unknown-player", name)
                        return@execute
                    }
                    if (name == user.name) {
                        user.sendLang("self")
                        return@execute
                    }
                    if (user.getIgnores().contains(player.uniqueId)) {
                        user.sendLang("player-exist", name)
                        return@execute
                    }
                    user.addIgnore(player.uniqueId)
                    user.sendLang("success", name)
                }
            }

            execute<Player> { user, _, _ ->
                user.openMenu()
            }
        }
    }
}