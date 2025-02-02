package net.horizonsend.ion.server.features.sidebar.bars

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.Component.text
//import net.kyori.adventure.text.format.NamedTextColor.DARK_GRAY
import net.megavex.scoreboardlibrary.api.sidebar.component.LineDrawable
import net.megavex.scoreboardlibrary.api.sidebar.component.SidebarComponent
import java.util.function.Supplier

class ContactsSidebarComponent(private val valueSupplier: Supplier<ContactsData>) : SidebarComponent {

    override fun draw(drawable: LineDrawable) {
        val value = valueSupplier.get()

        val line = text()
        line.append(value.prefix)
        line.appendSpace()
        line.append(value.heading)
        line.appendSpace()
        line.append(value.height)
        line.appendSpace()
        line.append(value.distance)
        line.append(value.padding)
        line.append(value.name)
        if (value.suffix != Component.empty()) {
            line.appendSpace()
            line.append(value.suffix)
        }

        drawable.drawLine(line.build())
    }
}
