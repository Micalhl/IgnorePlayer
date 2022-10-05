package io.github.micalhl.ignoreplayer

import taboolib.module.configuration.Config
import taboolib.module.configuration.ConfigNode
import taboolib.module.configuration.Configuration

/**
 * IgnorePlayer
 * io.github.micalhl.ignoreplayer.ConfigReader
 *
 * @author xiaomu
 * @since 2022/9/23 9:36 PM
 */
object ConfigReader {

    @Config(autoReload = true)
    lateinit var conf: Configuration

    @ConfigNode("Title")
    var title = ""

    @ConfigNode("Name")
    var name = ""

    @ConfigNode("Lore")
    var lore = emptyList<String>()

    @ConfigNode("Material")
    var material = "PLAYER_HEAD"
}