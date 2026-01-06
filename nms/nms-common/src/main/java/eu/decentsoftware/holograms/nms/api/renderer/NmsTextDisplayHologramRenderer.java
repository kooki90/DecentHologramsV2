package eu.decentsoftware.holograms.nms.api.renderer;

/**
 * A renderer for holographic text using TextDisplay entities.
 *
 * <p>
 * This renderer uses TextDisplay entities (introduced in 1.19.4) which
 * support native text shadow via style flags. For older versions, this
 * renderer is not available and falls back to ArmorStand-based rendering.
 * </p>
 *
 * @author d0by
 * @since 2.9.10
 */
public interface NmsTextDisplayHologramRenderer extends NmsHologramRenderer<String> {

    /**
     * Checks if this renderer supports text shadow.
     *
     * @return true if text shadow is supported, false otherwise.
     */
    default boolean supportsTextShadow() {
        return true;
    }

    /**
     * Display the hologram with shadow preference.
     *
     * @param player     The player to display to.
     * @param data       The hologram part data.
     * @param textShadow Whether text shadow should be enabled.
     */
    void display(org.bukkit.entity.Player player, eu.decentsoftware.holograms.nms.api.NmsHologramPartData<String> data,
            boolean textShadow);

    /**
     * Display the hologram with shadow and transparent background preferences.
     *
     * @param player      The player to display to.
     * @param data        The hologram part data.
     * @param textShadow  Whether text shadow should be enabled.
     * @param transparent Whether background should be transparent.
     */
    default void display(org.bukkit.entity.Player player,
            eu.decentsoftware.holograms.nms.api.NmsHologramPartData<String> data,
            boolean textShadow, boolean transparent) {
        display(player, data, textShadow);
    }

    /**
     * Update the content with shadow preference.
     *
     * @param player     The player to update for.
     * @param data       The hologram part data.
     * @param textShadow Whether text shadow should be enabled.
     */
    void updateContent(org.bukkit.entity.Player player,
            eu.decentsoftware.holograms.nms.api.NmsHologramPartData<String> data, boolean textShadow);

    /**
     * Update the content with shadow and transparent background preferences.
     *
     * @param player      The player to update for.
     * @param data        The hologram part data.
     * @param textShadow  Whether text shadow should be enabled.
     * @param transparent Whether background should be transparent.
     */
    default void updateContent(org.bukkit.entity.Player player,
            eu.decentsoftware.holograms.nms.api.NmsHologramPartData<String> data, boolean textShadow,
            boolean transparent) {
        updateContent(player, data, textShadow);
    }
}
