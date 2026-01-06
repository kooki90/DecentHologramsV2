package eu.decentsoftware.holograms.nms.v1_21_R1;

import eu.decentsoftware.holograms.nms.api.NmsHologramPartData;
import eu.decentsoftware.holograms.nms.api.renderer.NmsTextDisplayHologramRenderer;
import eu.decentsoftware.holograms.shared.DecentPosition;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

class TextDisplayHologramRenderer implements NmsTextDisplayHologramRenderer {

    private final int textDisplayEntityId;
    private boolean currentShadowState = false;

    TextDisplayHologramRenderer(EntityIdGenerator entityIdGenerator) {
        this.textDisplayEntityId = entityIdGenerator.getFreeEntityId();
    }

    @Override
    public void display(Player player, NmsHologramPartData<String> data) {
        display(player, data, false);
    }

    @Override
    public void display(Player player, NmsHologramPartData<String> data, boolean textShadow) {
        String content = data.getContent();
        DecentPosition position = data.getPosition();
        this.currentShadowState = textShadow;
        EntityPacketsBuilder.create()
                .withSpawnEntity(textDisplayEntityId, EntityType.TEXT_DISPLAY, position)
                .withEntityMetadata(textDisplayEntityId, TextDisplayMetadataBuilder.create()
                        .withNoGravity()
                        .withTextDisplayText(content)
                        .withTextDisplayShadow(textShadow)
                        .withTextDisplayBillboard((byte) 3) // CENTER billboard
                        .toWatchableObjects())
                .sendTo(player);
    }

    @Override
    public void updateContent(Player player, NmsHologramPartData<String> data) {
        updateContent(player, data, this.currentShadowState);
    }

    @Override
    public void updateContent(Player player, NmsHologramPartData<String> data, boolean textShadow) {
        this.currentShadowState = textShadow;
        EntityPacketsBuilder.create()
                .withEntityMetadata(textDisplayEntityId, TextDisplayMetadataBuilder.create()
                        .withTextDisplayText(data.getContent())
                        .withTextDisplayShadow(textShadow)
                        .toWatchableObjects())
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
