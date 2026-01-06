package eu.decentsoftware.holograms.nms.paper_v1_21_R7;

import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.SynchedEntityData;
import io.papermc.paper.adventure.PaperAdventure;

import java.util.ArrayList;
import java.util.List;

/**
 * Metadata builder specifically for TextDisplay entities on Paper.
 * Supports text shadow via style flags.
 */
class TextDisplayMetadataBuilder {

    // TextDisplay style flags
    private static final byte FLAG_SHADOW = 0x01;
    private static final byte FLAG_SEE_THROUGH = 0x02;
    private static final byte FLAG_USE_DEFAULT_BACKGROUND = 0x04;
    private static final byte FLAG_ALIGN_LEFT = 0x08;
    private static final byte FLAG_ALIGN_RIGHT = 0x10;

    private final List<SynchedEntityData.DataItem<?>> watchableObjects;

    private TextDisplayMetadataBuilder() {
        this.watchableObjects = new ArrayList<>();
    }

    List<SynchedEntityData.DataItem<?>> toWatchableObjects() {
        return watchableObjects;
    }

    TextDisplayMetadataBuilder withNoGravity() {
        watchableObjects.add(TextDisplayMetadataType.ENTITY_HAS_NO_GRAVITY.construct(true));
        return this;
    }

    TextDisplayMetadataBuilder withTextDisplayText(String text) {
        Component component;
        if (text == null || text.isEmpty()) {
            component = Component.empty();
        } else {
            // Convert legacy color codes to Adventure/Component
            component = PaperAdventure.asVanilla(
                    net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer.legacySection()
                            .deserialize(text));
        }
        watchableObjects.add(TextDisplayMetadataType.TEXT_DISPLAY_TEXT.construct(component));
        return this;
    }

    TextDisplayMetadataBuilder withTextDisplayShadow(boolean shadow) {
        byte flags = shadow ? FLAG_SHADOW : 0;
        watchableObjects.add(TextDisplayMetadataType.TEXT_DISPLAY_STYLE_FLAGS.construct(flags));
        return this;
    }

    TextDisplayMetadataBuilder withTextDisplayBillboard(byte billboard) {
        // Billboard modes: 0=FIXED, 1=VERTICAL, 2=HORIZONTAL, 3=CENTER
        watchableObjects.add(TextDisplayMetadataType.DISPLAY_BILLBOARD.construct(billboard));
        return this;
    }

    TextDisplayMetadataBuilder withTextDisplayBackground(int argb) {
        watchableObjects.add(TextDisplayMetadataType.TEXT_DISPLAY_BACKGROUND.construct(argb));
        return this;
    }

    static TextDisplayMetadataBuilder create() {
        return new TextDisplayMetadataBuilder();
    }
}
