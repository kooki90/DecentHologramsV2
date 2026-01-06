package eu.decentsoftware.holograms.nms.api.renderer;

/**
 * Factory for creating hologram renderers.
 *
 * <p>
 * Implementations of this factory provide instances of hologram renderers
 * suited for rendering different types of holographic elements.
 * </p>
 *
 * @author d0by
 * @since 2.9.0
 */
public interface NmsHologramRendererFactory {

    /**
     * Creates a renderer for displaying text holograms.
     *
     * @return A new instance of {@link NmsTextHologramRenderer}.
     */
    NmsTextHologramRenderer createTextRenderer();

    /**
     * Creates a renderer for displaying icon holograms.
     *
     * @return A new instance of {@link NmsIconHologramRenderer}.
     */
    NmsIconHologramRenderer createIconRenderer();

    /**
     * Creates a renderer for displaying head holograms.
     *
     * @return A new instance of {@link NmsHeadHologramRenderer}.
     */
    NmsHeadHologramRenderer createHeadRenderer();

    /**
     * Creates a renderer for displaying small head holograms.
     *
     * @return A new instance of {@link NmsSmallHeadHologramRenderer}.
     */
    NmsSmallHeadHologramRenderer createSmallHeadRenderer();

    /**
     * Creates a renderer for displaying entity holograms.
     *
     * @return A new instance of {@link NmsEntityHologramRenderer}.
     */
    NmsEntityHologramRenderer createEntityRenderer();

    /**
     * Creates a renderer for displaying clickable holograms.
     *
     * <p>
     * These holograms support player interaction, enabling actions when clicked.
     * </p>
     *
     * @return A new instance of {@link NmsClickableHologramRenderer}.
     */
    NmsClickableHologramRenderer createClickableRenderer();

    /**
     * Creates a renderer for displaying text holograms using TextDisplay entities.
     *
     * <p>
     * TextDisplay entities support native text shadow. This is only available
     * on 1.19.4+ servers. Returns null on unsupported versions.
     * </p>
     *
     * @return A new instance of {@link NmsTextDisplayHologramRenderer}, or null if
     *         not supported.
     */
    NmsTextDisplayHologramRenderer createTextDisplayRenderer();

}
