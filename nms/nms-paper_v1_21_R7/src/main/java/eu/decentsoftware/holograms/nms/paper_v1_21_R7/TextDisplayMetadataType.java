package eu.decentsoftware.holograms.nms.paper_v1_21_R7;

import eu.decentsoftware.holograms.shared.reflect.ReflectUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.Entity;

/**
 * Metadata types for TextDisplay entities on Paper.
 * Uses Mojang-mapped field names.
 */
class TextDisplayMetadataType<T> {

    // Entity base fields (Mojang-mapped names)
    private static final EntityDataAccessor<Boolean> ENTITY_HAS_NO_GRAVITY_OBJECT = ReflectUtil
            .getFieldValue(Entity.class, "DATA_NO_GRAVITY");

    // Display entity fields (shared by all Display types)
    private static final EntityDataAccessor<Byte> DISPLAY_BILLBOARD_OBJECT = ReflectUtil
            .getFieldValue(Display.class, "DATA_BILLBOARD_RENDER_CONSTRAINTS_ID");

    // TextDisplay specific fields
    private static final EntityDataAccessor<Component> TEXT_DISPLAY_TEXT_OBJECT = ReflectUtil
            .getFieldValue(Display.TextDisplay.class, "DATA_TEXT_ID");
    private static final EntityDataAccessor<Integer> TEXT_DISPLAY_BACKGROUND_OBJECT = ReflectUtil
            .getFieldValue(Display.TextDisplay.class, "DATA_BACKGROUND_COLOR_ID");
    private static final EntityDataAccessor<Byte> TEXT_DISPLAY_STYLE_FLAGS_OBJECT = ReflectUtil
            .getFieldValue(Display.TextDisplay.class, "DATA_STYLE_FLAGS_ID");

    static final TextDisplayMetadataType<Boolean> ENTITY_HAS_NO_GRAVITY = new TextDisplayMetadataType<>(
            ENTITY_HAS_NO_GRAVITY_OBJECT);
    static final TextDisplayMetadataType<Byte> DISPLAY_BILLBOARD = new TextDisplayMetadataType<>(
            DISPLAY_BILLBOARD_OBJECT);
    static final TextDisplayMetadataType<Component> TEXT_DISPLAY_TEXT = new TextDisplayMetadataType<>(
            TEXT_DISPLAY_TEXT_OBJECT);
    static final TextDisplayMetadataType<Integer> TEXT_DISPLAY_BACKGROUND = new TextDisplayMetadataType<>(
            TEXT_DISPLAY_BACKGROUND_OBJECT);
    static final TextDisplayMetadataType<Byte> TEXT_DISPLAY_STYLE_FLAGS = new TextDisplayMetadataType<>(
            TEXT_DISPLAY_STYLE_FLAGS_OBJECT);

    private final EntityDataAccessor<T> entityDataAccessor;

    private TextDisplayMetadataType(EntityDataAccessor<T> entityDataAccessor) {
        this.entityDataAccessor = entityDataAccessor;
    }

    SynchedEntityData.DataItem<T> construct(T value) {
        return new SynchedEntityData.DataItem<>(entityDataAccessor, value);
    }
}
