package net.teamabode.scribe.core.mixin;

import net.minecraft.client.animation.definitions.FrogAnimation;
import net.minecraft.client.model.FrogModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.frog.Frog;
import net.teamabode.scribe.core.Scribe;
import net.teamabode.scribe.core.api.animation.AnimationParser;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(FrogModel.class)
public class FrogModelMixin<T extends Frog> extends HierarchicalModel<T> {
    @Shadow @Final private ModelPart croakingBody;

    private static final ResourceLocation FROG_CONSUME = new ResourceLocation(Scribe.MOD_ID, "consume");

    @Override
    public void setupAnim(T frog, float f, float g, float h, float i, float j) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        float k = Math.min((float)frog.getDeltaMovement().lengthSqr() * 200.0F, 8.0F);
        this.animate(frog.jumpAnimationState, FrogAnimation.FROG_JUMP, h);
        this.animate(frog.croakAnimationState, FrogAnimation.FROG_CROAK, h);
        this.animate(frog.tongueAnimationState, AnimationParser.createAnimation(FROG_CONSUME), h);
        this.animate(frog.walkAnimationState, FrogAnimation.FROG_WALK, h, k);
        this.animate(frog.swimAnimationState, FrogAnimation.FROG_SWIM, h);
        this.animate(frog.swimIdleAnimationState, FrogAnimation.FROG_IDLE_WATER, h);
        this.croakingBody.visible = frog.croakAnimationState.isStarted();
    }

    @Shadow
    public ModelPart root() {
        throw new AssertionError();
    }
}
