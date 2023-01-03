package net.teamabode.scribe.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.teamabode.scribe.core.Scribe;
import net.teamabode.scribe.core.registry.quebe.Quebe;

public class QuebeRenderer extends MobRenderer<Quebe, QuebeModel> {

    public QuebeRenderer(EntityRendererProvider.Context context) {
        super(context, new QuebeModel(context.bakeLayer(QuebeModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(Quebe entity) {
        return new ResourceLocation(Scribe.MOD_ID, "textures/entity/quebe.png");
    }
}
