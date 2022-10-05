package io.github.micalhl.ignoreplayer.inv

import io.github.micalhl.ignoreplayer.ConfigReader
import io.github.micalhl.ignoreplayer.api.IgnoreAPI.getIgnores
import io.github.micalhl.ignoreplayer.api.IgnoreAPI.removeIgnore
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import taboolib.common.util.replaceWithOrder
import taboolib.library.xseries.XMaterial
import taboolib.library.xseries.parseToXMaterial
import taboolib.module.chat.colored
import taboolib.module.ui.openMenu
import taboolib.module.ui.type.Linked
import taboolib.platform.compat.replacePlaceholder
import taboolib.platform.util.Slots
import taboolib.platform.util.buildItem
import taboolib.platform.util.sendLang
import java.util.UUID

/**
 * IgnorePlayer
 * io.github.micalhl.ignoreplayer.inv.IgnoreInventory
 *
 * @author xiaomu
 * @since 2022/9/23 9:20 PM
 */
object IgnoreInventory {

    fun Player.openMenu() {
        openMenu<Linked<UUID>>(ConfigReader.title) {
            rows(6)
            handLocked(true)
            slots(Slots.CENTER)

            elements {
                getIgnores()
            }

            onGenerate { viewer, uniqueId, _, _ ->
                buildItem(ConfigReader.material.parseToXMaterial()) {
                    val offline = Bukkit.getOfflinePlayer(uniqueId)
                    name = ConfigReader.name.replacePlaceholder(viewer).replaceWithOrder(
                        offline.name to "player"
                    ).colored()
                    lore.addAll(ConfigReader.lore.map {
                        it.colored().replacePlaceholder(viewer).replaceWithOrder(offline.name to "player")
                    })
                }
            }

            onClick { event, ignore ->
                event.onClick {
                    if (isLeftClick) {
                        val offline = Bukkit.getOfflinePlayer(ignore)
                        removeIgnore(ignore)
                        sendLang("remove-success", offline.name ?: "unknown")
                        closeInventory()
                        openMenu()
                    }
                }
            }

            setNextPage(51) { _, hasNextPage ->
                if (hasNextPage) {
                    buildItem(XMaterial.SPECTRAL_ARROW) {
                        name = "&7--->".colored()
                    }
                } else {
                    buildItem(XMaterial.ARROW) {
                        name = "&8--->".colored()
                    }
                }
            }

            setPreviousPage(47) { _, hasPreviousPage ->
                if (hasPreviousPage) {
                    buildItem(XMaterial.SPECTRAL_ARROW) {
                        name = "&7<---".colored()
                    }
                } else {
                    buildItem(XMaterial.ARROW) {
                        name = "&8<---".colored()
                    }
                }
            }
        }
    }
}