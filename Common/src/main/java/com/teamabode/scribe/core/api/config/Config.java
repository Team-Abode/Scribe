package com.teamabode.scribe.core.api.config;

import com.google.gson.*;
import com.google.gson.stream.JsonWriter;
import com.teamabode.scribe.core.Scribe;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.util.GsonHelper;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class Config {
    public static class Property<T> {
        String key;
        boolean loaded;
        T defaultValue;
        T loadedValue;
        Consumer<T> handler;
        Predicate<JsonObject> validater;
        Function<JsonObject, T> loader;
        Function<T, JsonElement> serializer;

        public Property(String key, T defaultValue, Consumer<T> handler, Predicate<JsonObject> validater, Function<JsonObject, T> loader, Function<T, JsonElement> serializer){
            this.key = key;
            this.defaultValue = defaultValue;
            this.handler = handler;
            this.validater = validater;
            this.loader = loader;
            this.serializer = serializer;
        }

        public T get(){
            if(loaded) return loadedValue;

            return defaultValue;
        }

        public void load(JsonObject jsonObject){
            if(!jsonObject.has(key)) {
                Scribe.LOGGER.info("Missing key " + key + " on " + jsonObject);

                return;
            }

            if(!validater.test(jsonObject)) throw new AssertionError("Invalid config property!");

            loadedValue = loader.apply(jsonObject);
            loaded = true;
        }

        public void handle(){
            handler.accept(get());
        }

        public JsonElement toJson(){
            return serializer.apply(get());
        }
    }

    public static class Group {
        public HashMap<String, Property<?>> properties = new HashMap<>();

        public Group property(String key, String defaultValue, Consumer<String> handler){
            properties.put(key, new Property<>(key, defaultValue, handler, jsonObject -> GsonHelper.isStringValue(jsonObject, key), jsonObject -> GsonHelper.getAsString(jsonObject, key), value -> new JsonPrimitive(value)));

            return this;
        }

        public Group property(String key, String defaultValue){
            properties.put(key, new Property<>(key, defaultValue, value -> {
            }, jsonObject -> GsonHelper.isStringValue(jsonObject, key), jsonObject -> GsonHelper.getAsString(jsonObject, key), JsonPrimitive::new));

            return this;
        }

        public Group property(String key, int defaultValue, Consumer<Integer> handler){
            properties.put(key, new Property<>(key, defaultValue, handler, jsonObject -> GsonHelper.isNumberValue(jsonObject, key), jsonObject -> GsonHelper.getAsInt(jsonObject, key), JsonPrimitive::new));

            return this;
        }

        public Group property(String key, int defaultValue){
            properties.put(key, new Property<>(key, defaultValue, value -> {
            }, jsonObject -> GsonHelper.isNumberValue(jsonObject, key), jsonObject -> GsonHelper.getAsInt(jsonObject, key), JsonPrimitive::new));

            return this;
        }

        public Group property(String key, float defaultValue, Consumer<Float> handler){
            properties.put(key, new Property<>(key, defaultValue, handler, jsonObject -> GsonHelper.isNumberValue(jsonObject, key), jsonObject -> GsonHelper.getAsFloat(jsonObject, key), JsonPrimitive::new));

            return this;
        }

        public Group property(String key, float defaultValue){
            properties.put(key, new Property<>(key, defaultValue, value -> {
            }, jsonObject -> GsonHelper.isNumberValue(jsonObject, key), jsonObject -> GsonHelper.getAsFloat(jsonObject, key), JsonPrimitive::new));

            return this;
        }

        public Group property(String key, boolean defaultValue, Consumer<Boolean> handler){
            properties.put(key, new Property<>(key, defaultValue, handler, jsonObject -> GsonHelper.isBooleanValue(jsonObject, key), jsonObject -> GsonHelper.getAsBoolean(jsonObject, key), JsonPrimitive::new));

            return this;
        }

        public Group property(String key, boolean defaultValue){
            properties.put(key, new Property<>(key, defaultValue, value -> {
            }, jsonObject -> GsonHelper.isBooleanValue(jsonObject, key), jsonObject -> GsonHelper.getAsBoolean(jsonObject, key), value -> new JsonPrimitive(value)));

            return this;
        }

        public Group convertJsonGroup(JsonObject jsonObject){
            Group group = new Group();

            jsonObject.entrySet().forEach(entry -> {
                String key = entry.getKey();
                JsonElement jsonElement = entry.getValue();

                if(jsonElement.isJsonObject()){
                    group.group(key, convertJsonGroup(jsonElement.getAsJsonObject()));
                } else if(jsonElement.isJsonArray()){
                    group.property(key, convertJsonArray(jsonElement.getAsJsonArray()));
                } else if(jsonElement.isJsonPrimitive()){
                    JsonPrimitive jsonPrimitive = jsonElement.getAsJsonPrimitive();

                    if(jsonPrimitive.isString()){
                        group.property(key, jsonPrimitive.getAsString());
                    } else if(jsonPrimitive.isNumber()){
                        group.property(key, jsonPrimitive.getAsFloat());
                    } else if(jsonPrimitive.isBoolean()){
                        group.property(key, jsonPrimitive.getAsBoolean());
                    }
                }
            });

            return group;
        }

        public List<Object> convertJsonArray(JsonArray jsonArray){
            ArrayList<Object> list = new ArrayList<>();

            jsonArray.forEach(jsonElement -> {
                if(jsonElement.isJsonNull()) {
                    list.add(null);
                } else if(jsonElement.isJsonArray()){
                    list.add(convertJsonArray(jsonElement.getAsJsonArray()));
                } else if(jsonElement.isJsonObject()){
                    list.add(convertJsonGroup(jsonElement.getAsJsonObject()));
                } else if (jsonElement.isJsonPrimitive()){
                    JsonPrimitive jsonPrimitive = jsonElement.getAsJsonPrimitive();

                    if(jsonPrimitive.isString()){
                        list.add(jsonPrimitive.getAsString());
                    } else if(jsonPrimitive.isNumber()){
                        list.add(jsonPrimitive.getAsFloat());
                    } else if(jsonPrimitive.isBoolean()){
                        list.add(jsonPrimitive.getAsBoolean());
                    }
                }
            });

            return  list;
        }

        public JsonArray convertToJsonArray(List<Object> list){
            JsonArray jsonArray = new JsonArray();

            list.forEach(value -> {
                if(value instanceof Group){
                    jsonArray.add(((Group)value).toJson());
                } else if(value instanceof List) {
                    jsonArray.add(convertToJsonArray((List<Object>) value));
                } else if(value instanceof Integer) {
                    jsonArray.add(new JsonPrimitive((int) value));
                } else if(value instanceof Float) {
                    jsonArray.add(new JsonPrimitive((float) value));
                } else if(value instanceof String) {
                    jsonArray.add(new JsonPrimitive((String) value));
                } else if(value instanceof Boolean) {
                    jsonArray.add(new JsonPrimitive((boolean) value));
                }
            });

            return jsonArray;
        }

        public Group property(String key, List<Object> defaultValue, Consumer<List<Object>> handler){
            properties.put(key, new Property<>(key, defaultValue, handler, jsonObject -> GsonHelper.isArrayNode(jsonObject, key), jsonObject -> convertJsonArray(GsonHelper.getAsJsonArray(jsonObject, key)), value -> ((Group) value).toJson()));

            return this;
        }

        public Group property(String key, List<Object> defaultValue){
            properties.put(key, new Property<>(key, defaultValue, value -> {
            }, jsonObject -> GsonHelper.isArrayNode(jsonObject, key), jsonObject -> convertJsonArray(GsonHelper.getAsJsonArray(jsonObject, key)), value -> ((Group) value).toJson()));

            return this;
        }

        public Group group(String key, Consumer<Group> handler){
            Group group = new Group();

            handler.accept(group);

            properties.put(
                    key,
                    new Property<>(key, group, passedGroup -> group.handle(), jsonObject -> GsonHelper.isObjectNode(jsonObject, key), jsonObject -> group.load(jsonObject.getAsJsonObject(key)), Group::toJson));

            return this;
        }

        public Group group(String key, Group group){
            properties.put(key, new Property<>(key, group, passedGroup -> group.handle(), jsonObject -> GsonHelper.isObjectNode(jsonObject, key), jsonObject -> group.load(jsonObject.getAsJsonObject(key)), Group::toJson));

            return this;
        }

        public Group load(JsonObject jsonObject){
            properties.forEach((String key, Property property) -> property.load(jsonObject));

            return this;
        }

        public void handle() {
            properties.forEach((String key, Property property) -> property.handle());
        }

        public Object get(String key){
            if(!properties.containsKey(key)) return null;

            return properties.get(key).get();
        }

        public Group getGroup(String key){
            if(!properties.containsKey(key)) return null;

            return (Group) properties.get(key).get();
        }

        public JsonObject toJson(){
            JsonObject jsonObject = new JsonObject();

            properties.forEach((String key, Property property) -> {
                jsonObject.add(key, property.toJson());
            });

            return jsonObject;
        }
    }

    private final Path path;
    private final String id;
    private final Group root;

    Config(String id){
        this.id = id;
        this.path = getPath();
        this.root = new Group();
    }

    public static Config define(String id){
        return new Config(id);
    }

    @ExpectPlatform
    public static Path getPath() {
        throw new AssertionError("No config path for platform found!");
    }

    public Config property(String key, String defaultValue, Consumer<String> handler){
        root.property(key, defaultValue, handler);

        return this;
    }

    public Config property(String key, String defaultValue){
        root.property(key, defaultValue);

        return this;
    }

    public Config property(String key, int defaultValue, Consumer<Integer> handler){
        root.property(key, defaultValue, handler);

        return this;
    }

    public Config property(String key, int defaultValue){
        root.property(key, defaultValue);

        return this;
    }

    public Config property(String key, float defaultValue, Consumer<Float> handler){
        root.property(key, defaultValue, handler);

        return this;
    }

    public Config property(String key, float defaultValue){
        root.property(key, defaultValue);

        return this;
    }

    public Config property(String key, boolean defaultValue, Consumer<Boolean> handler){
        root.property(key, defaultValue, handler);

        return this;
    }

    public Config property(String key, boolean defaultValue){
        root.property(key, defaultValue);

        return this;
    }

    public Config property(String key, List<Object> defaultValue, Consumer<List<Object>> handler){
        root.property(key, defaultValue, handler);

        return this;
    }

    public Config property(String key, List<Object> defaultValue){
        root.property(key, defaultValue);

        return this;
    }

    public Config group(String key, Consumer<Group> handler){
        root.group(key, handler);

        return this;
    }

    public Config load(){
        try {
            FileReader fileReader = new FileReader(path.resolve(id + ".json").toString());

            JsonObject jsonObject = GsonHelper.parse(fileReader);

            fileReader.close();

            System.out.println("Read config file at " + path.resolve(id + ".json"));

            if(!jsonObject.isJsonObject()) throw new AssertionError("Config file must be a json object not an array!");

            root.load(jsonObject);
        } catch (Exception exception) {
            Scribe.LOGGER.warn("Error loading config file at " + path.resolve(id + ".json"));
            Scribe.LOGGER.error(exception.getMessage());

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            try{
                FileWriter fileWriter = new FileWriter(path.resolve(id + ".json").toString());
                fileWriter.write(gson.toJson(root.toJson()));
                fileWriter.close();
            }catch (Exception exception2){
                Scribe.LOGGER.warn("Error writing default config at " + path.resolve(id + ".json"));
                Scribe.LOGGER.error(exception2.getMessage());
                Scribe.LOGGER.error(String.valueOf(exception2.getStackTrace()[0].getLineNumber()));
            }
        }

        root.handle();

        return this;
    }

    public Object get(String key){
        return root.get(key);
    }

    public Group getGroup(String key){
        return (Group) root.get(key);
    }
}
