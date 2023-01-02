package net.teamabode.scribe.core.api.animation;

import com.google.gson.JsonElement;
import com.mojang.math.Vector3f;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.resources.ResourceLocation;

import java.util.Iterator;
import java.util.Map;

public class AnimationParser {

    private static final Map<String, AnimationChannel.Target> ANIMATION_TARGETS = Map.of(
            "rotation", AnimationChannel.Targets.ROTATION,
            "position", AnimationChannel.Targets.POSITION,
            "scale", AnimationChannel.Targets.SCALE
    );

    public static AnimationDefinition createAnimation(ResourceLocation resourceLocation) {
        AnimationData jsonData = AnimationManager.ANIMATIONS.get(resourceLocation);
        if (jsonData == null) return nullAnimation();

        AnimationDefinition.Builder builder = AnimationDefinition.Builder.withLength(jsonData.getLength());

        Iterator<Map.Entry<String, JsonElement>> bones = jsonData.getBones();
        while (bones.hasNext()) {
            String bone = bones.next().getKey();
            Iterator<Map.Entry<String, JsonElement>> animationTargets = jsonData.getAnimationTargets(bone);
            while (animationTargets.hasNext()) {
                String animationTarget = animationTargets.next().getKey();
                Iterator<Map.Entry<String, JsonElement>> timestamps = jsonData.getTimestamps(bone, animationTarget);
                Keyframe[] keyframes = new Keyframe[jsonData.countKeyframes(bone, animationTarget)];
                int i = 0;
                while (timestamps.hasNext()) {
                    String timestamp = timestamps.next().getKey();
                    float[] value = jsonData.getAnimationValue(bone, animationTarget, timestamp);

                    keyframes[i] = new Keyframe(Float.parseFloat(timestamp), getVector(animationTarget, value), jsonData.getInterpolationType(bone, animationTarget, timestamp));
                    i++;
                }
                builder.addAnimation(bone, new AnimationChannel(getAnimationTarget(animationTarget), keyframes));
            }
        }
        if (jsonData.isLooping()) builder.looping();
        return builder.build();
    }

    private static AnimationDefinition nullAnimation() {
        return AnimationDefinition.Builder.withLength(0.0F).build();
    }

    private static AnimationChannel.Target getAnimationTarget(String target) {
        if (ANIMATION_TARGETS.containsKey(target)) {
            return ANIMATION_TARGETS.get(target);
        }
        throw new AssertionError("Invalid animation target " + target);
    }

    private static Vector3f getVector(String target, float[] value) {
        Vector3f vector3f = null;
        switch (target) {
            case "rotation" -> vector3f = KeyframeAnimations.degreeVec(value[0], value[1], value[2]);
            case "position" -> vector3f = KeyframeAnimations.posVec(value[0], value[1], value[2]);
            case "scale" -> vector3f = KeyframeAnimations.scaleVec(value[0], value[1], value[2]);
        }
        if (vector3f != null) {
            return vector3f;
        }
        throw new AssertionError();
    }
}
