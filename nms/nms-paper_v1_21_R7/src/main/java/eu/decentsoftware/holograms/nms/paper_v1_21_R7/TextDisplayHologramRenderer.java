package eu.decentsoftware.holograms.nms.paper_v1_21_R7;

import eu.decentsoftware.holograms.nms.api.NmsHologramPartData;
import eu.decentsoftware.holograms.nms.api.renderer.NmsTextDisplayHologramRenderer;
import eu.decentsoftware.holograms.shared.DecentPosition;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

class TextDisplayHologramRenderer implements NmsTextDisplayHologramRenderer {

    private final int textDisplayEntityId;
    private boolean currentShadowState = false;
    private boolean currentTransparentState = false;

    TextDisplayHologramRenderer(EntityIdGenerator entityIdGenerator) {
        this.textDisplayEntityId = entityIdGenerator.getFreeEntityId();
    }

    @Override
    public void display(Player player, NmsHologramPartData<String> data) {
        display(player, data, false, false);
    }

    @Override
    public void display(Player player, NmsHologramPartData<String> data, boolean textShadow) {
        display(player, data, textShadow, false);
    }

    @Override
    public void display(Player player, NmsHologramPartData<String> data, boolean textShadow, boolean transparent) {
        String content = data.getContent();
        DecentPosition position = data.getPosition();
        this.currentShadowState = textShadow;
        this.currentTransparentState = transparent;

        TextDisplayMetadataBuilder builder = TextDisplayMetadataBuilder.create()
                .withNoGravity()
                .withTextDisplayText(content)
                .withTextDisplayShadow(textShadow)
                .withTextDisplayBillboard((byte) 3); // CENTER billboard

        // Set transparent background (0 = fully transparent)
        if (transparent) {
            builder.withTextDisplayBackground(0);
        }

        EntityPacketsBuilder.create()
                .withSpawnEntity(textDisplayEntityId, EntityType.TEXT_DISPLAY, position)
                .withEntityMetadata(textDisplayEntityId, builder.toWatchableObjects())
                .sendTo(player);
    }

    @Override
    public void updateContent(Player player, NmsHologramPartData<String> data) {
        updateContent(player, data, this.currentShadowState, this.currentTransparentState);
    }

    @Override
    public void updateContent(Player player, NmsHologramPartData<String> data, boolean textShadow) {
        updateContent(player, data, textShadow, this.currentTransparentState);
    }

    @Override
    public void updateContent(Player player, NmsHologramPartData<String> data, boolean textShadow,
            boolean transparent) {
        this.currentShadowState = textShadow;
        this.currentTransparentState = transparent;

        TextDisplayMetadataBuilder builder = TextDisplayMetadataBuilder.create()
                .withTextDisplayText(data.getContent())
                .withTextDisplayShadow(textShadow);

        // Set transparent background (0 = fully transparent)
        if (transparent) {
            builder.withTextDisplayBackground(0);
        }

        EntityPacketsBuilder.create()
                .withEntityMetadata(textDisplayEntityId, builder.toWatchableObjects())
                .sendTo(player);
    }

    @Override
    public void move(Player player, NmsHologramPartData<String> data) {
        EntityPacketsBuilder.create()
                .withTeleportEntity(textDisplayEntityId, data.getPosition())
                .sendTo(player);
    }

    @Override
    public void hide(Player player) {
        EntityPacketsBuilder.create()
                .withRemoveEntity(textDisplayEntityId)
                .sendTo(player);
    }

    @Override
    public double getHeight(NmsHologramPartData<String> data) {
        return 0.25d;
    }

    @Override
    public int[] getEntityIds() {
        return new int[] { textDisplayEntityId };
    }
}
