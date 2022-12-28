package com.teamabode.scribe.api.animation;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Map;

public class AnimationData {

    public JsonElement data;

    public AnimationData(JsonElement data) {
        this.data = data;
    }

    public float getLength() {
        if (!data.getAsJsonObject().has("length")) throw new AssertionError("A specified length is required on an animation.");
        return Float.parseFloat(data.getAsJsonObject().get("length").getAsString());
    }

    public boolean isLooping() {
        if (!data.getAsJsonObject().has("looping")) return false;
        return Boolean.parseBoolean(data.getAsJsonObject().get("looping").getAsString());
    }

    public Iterator<Map.Entry<String, JsonElement>> getBones() {
        if (!data.getAsJsonObject().has("bones")) throw new AssertionError("The bones object is required on an animation.");
        return data.getAsJsonObject().get("bones").getAsJsonObject().entrySet().iterator();
    }

    public Iterator<Map.Entry<String, JsonElement>> getKeyframeTimestamp(String bone) {
        if (!data.getAsJsonObject().get("bones").getAsJsonObject().has(bone)) throw new AssertionError();
        return data.getAsJsonObject().get("bones").getAsJsonObject().get(bone).getAsJsonObject().entrySet().iterator();
    }

    public Iterator<Map.Entry<String, JsonElement>> getAnimationTargets(String bone, String timestamp) {
        if (!data.getAsJsonObject().get("bones").getAsJsonObject().get(bone).getAsJsonObject().has(timestamp)) throw new AssertionError();
        return data.getAsJsonObject().get("bones").getAsJsonObject().get(bone).getAsJsonObject().get(timestamp).getAsJsonObject().entrySet().iterator();
    }

    public InterpolationType getInterpolationType(String bone, String timestamp) {
        JsonObject jsonObject = data.getAsJsonObject().get("bones").getAsJsonObject().get(bone).getAsJsonObject().get(timestamp).getAsJsonObject().get("interpolation").getAsJsonObject();
        if (!jsonObject.isJsonObject()) throw new AssertionError();
        return jsonObject.has("linear") ? InterpolationType.LINEAR : InterpolationType.CATMULLROM;
    }

    public int getKeyframeCount(String bone) {
        return data.getAsJsonObject().get("bones").getAsJsonObject().get(bone).getAsJsonObject().size();
    }

    public enum InterpolationType {
        LINEAR,
        CATMULLROM
    }
}
