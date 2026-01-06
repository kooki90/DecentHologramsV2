package eu.decentsoftware.holograms.nms.v1_21_R1;

import eu.decentsoftware.holograms.shared.reflect.ReflectUtil;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.syncher.DataWatcher;
import net.minecraft.network.syncher.DataWatcherObject;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.Entity;

/**
 * Metadata types for TextDisplay entities.
 * These are the DataWatcherObjects specific to Display and TextDisplay
 * entities for 1.21_R1.
 */
class TextDisplayMetadataType<T> {

    // Entity base fields (1.21_R1 field names)
    private static final DataWatcherObject<Boolean> ENTITY_HAS_NO_GRAVITY_OBJECT = ReflectUtil
            .getFieldValue(Entity.class, "aT");

    // Display entity fields (shared by all Display types)
    private static final DataWatcherObject<Byte> DISPLAY_BILLBOARD_OBJECT = ReflectUtil.getFieldValue(Display.class,
            "d");

    // TextDisplay specific fields
    private static final DataWatcherObject<IChatBaseComponent> TEXT_DISPLAY_TEXT_OBJECT = ReflectUtil
            .getFieldValue(Display.TextDisplay.class, "b");
    private static final DataWatcherObject<Integer> TEXT_DISPLAY_BACKGROUND_OBJECT = ReflectUtil
            .getFieldValue(Display.TextDisplay.class, "e");
    private static final DataWatcherObject<Byte> TEXT_DISPLAY_STYLE_FLAGS_OBJECT = ReflectUtil
            .getFieldValue(Display.TextDisplay.class, "h");

    static final TextDisplayMetadataType<Boolean> ENTITY_HAS_NO_GRAVITY = new TextDisplayMetadataType<>(
            ENTITY_HAS_NO_GRAVITY_OBJECT);
    static final TextDisplayMetadataType<Byte> DISPLAY_BILLBOARD = new TextDisplayMetadataType<>(
            DISPLAY_BILLBOARD_OBJECT);
    static final TextDisplayMetadataType<IChatBaseComponent> TEXT_DISPLAY_TEXT = new TextDisplayMetadataType<>(
            TEXT_DISPLAY_TEXT_OBJECT);
    static final TextDisplayMetadataType<Integer> TEXT_DISPLAY_BACKGROUND = new TextDisplayMetadataType<>(
            TEXT_DISPLAY_BACKGROUND_OBJECT);
    static final TextDisplayMetadataType<Byte> TEXT_DISPLAY_STYLE_FLAGS = new TextDisplayMetadataType<>(
            TEXT_DISPLAY_STYLE_FLAGS_OBJECT);

    private final DataWatcherObject<T> dataWatcherObject;

    private TextDisplayMetadataType(DataWatcherObject<T> dataWatcherObject) {
        this.dataWatcherObject = dataWatcherObject;
    }

    DataWatcher.Item<T> construct(T value) {
        return new DataWatcher.Item<>(dataWatcherObject, value);
    }
}
