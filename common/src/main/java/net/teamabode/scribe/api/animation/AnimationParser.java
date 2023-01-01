package net.teamabode.scribe.api.animation;

import com.mojang.math.Vector3f;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.resources.ResourceLocation;
import net.teamabode.scribe.core.Scribe;

public class AnimationParser {

    public static AnimationDefinition getAnimation(ResourceLocation resourceLocation) {
        AnimationData animationData = AnimationData.ANIMATIONS.get(resourceLocation);
        if (animationData == null) throw new AssertionError("Animation is null!");

        AnimationDefinition.Builder builder = AnimationDefinition.Builder.withLength(animationData.getLength());
        if (animationData.isLooping()) builder.looping();

        if (animationData.getBones() != null) {
            animationData.getBones().forEachRemaining(bones -> animationData.getAnimationTargets(bones.getKey()).forEachRemaining(targets -> animationData.getTimestamps(bones.getKey(), targets.getKey()).forEachRemaining(timestamps -> {
                String bone = bones.getKey();
                String target = targets.getKey();
                String timestamp = timestamps.getKey();
                AnimationChannel.Target selectedTarget = null;
                float[] value = animationData.getAnimationValue(bone, target, timestamp);
                Vector3f vector3f = new Vector3f(value[0], value[1], value[2]);
                Keyframe[] keyframes = new Keyframe[animationData.countKeyframes(bone, target)];

                switch (target) {
                    default -> Scribe.LOGGER.error("Unknown operation \"" + target + "\" found in bone \"" + bone + "\" in animation \"" + resourceLocation.toString() + "\"");
                    case "rotation" -> {
                        selectedTarget = AnimationChannel.Targets.ROTATION;
                        vector3f = KeyframeAnimations.degreeVec(value[0], value[1], value[2]);
                    }
                    case "position" -> {
                        selectedTarget = AnimationChannel.Targets.POSITION;
                        vector3f = KeyframeAnimations.posVec(value[0], value[1], value[2]);
                    }
                    case "scale" -> {
                        selectedTarget = AnimationChannel.Targets.SCALE;
                        vector3f = KeyframeAnimations.scaleVec(value[0], value[1], value[2]);
                    }
                }
                for (int i = 0; i < keyframes.length; i++) {
                    keyframes[i] = new Keyframe(Float.parseFloat(timestamp), vector3f, animationData.getInterpolationType(bone, target, timestamp));
                }
                if (selectedTarget != null) {
                    builder.addAnimation(bone, new AnimationChannel(selectedTarget, keyframes));
                }
            })));
        } else {
            Scribe.LOGGER.warn("Bones not found in animation \"" + resourceLocation + "\"");
        }
        return builder.build();
    }
}
