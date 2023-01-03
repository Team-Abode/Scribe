package net.teamabode.scribe.core;

import net.teamabode.scribe.client.QuebeModel;
import net.teamabode.scribe.client.QuebeRenderer;
import net.teamabode.scribe.core.api.platform.EntityHelper;

public class ScribeClient {

    public static void clientInit() {
        EntityHelper.registerLayerDefinition(QuebeModel.LAYER_LOCATION, QuebeModel::createBodyLayer);
        EntityHelper.registerEntityRenderer(Scribe.QUEBE, QuebeRenderer::new);
    }
}
