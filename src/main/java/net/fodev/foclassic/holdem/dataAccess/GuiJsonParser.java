package net.fodev.foclassic.holdem.dataAccess;

import com.google.gson.*;
import com.jme3.font.Rectangle;
import net.fodev.foclassic.holdem.model.GuiElement;

import java.util.ArrayList;
import java.util.List;

public class GuiJsonParser {
    public String objectToString(GuiElement guiElement) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(guiElement);
    }

    public String objectListToString(List<GuiElement> guiEntities) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String data = gson.toJson(guiEntities);
        JsonArray jsonArray = new JsonParser().parse(data).getAsJsonArray();
        return jsonArray.toString();
    }

    public List<GuiElement> stringToGuiEntities(String jsonString) {
        List<GuiElement> guiElements = new ArrayList<>();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement element = gson.fromJson(jsonString, JsonElement.class);

        if (element.isJsonObject()) {
            JsonObject guiSetup = element.getAsJsonObject();
            JsonElement guiElementsRoot = guiSetup.get("guiElements");
            if (guiElementsRoot != null && guiElementsRoot.isJsonArray()) {
                JsonArray elements = guiElementsRoot.getAsJsonArray();
                for (JsonElement e: elements) {
                    if (e.isJsonObject()) {
                        GuiElement guiElement = jsonObjectToGuiElement(e.getAsJsonObject());
                        guiElements.add(guiElement);
                    } else {
                        System.out.println("Object required, but not non-object element found.");
                    }
                    System.out.println(e);
                }
            } else {
                System.out.println("'guiElements' does not exist or is not an array of elements.");
            }
        } else {
            System.out.println("Gui root element does not exist or is not a single object.");
        }
        return guiElements;
    }

    public GuiElement jsonObjectToGuiElement(JsonObject jsonObject) {
        if (jsonObject != null) {
            String name = "MISSING_NAME";
            GuiElement guiElement = new GuiElement();
            if (jsonObject.getAsJsonPrimitive("name") != null) {
                name = jsonObject.getAsJsonPrimitive("name").getAsString();
                guiElement.setName(name);
            } else {
                System.out.println("[Warning] Missing name for GUI element, skipping.");
                return null;
            }
            if (jsonObject.getAsJsonPrimitive("type") != null) {
                String type = jsonObject.getAsJsonPrimitive("type").getAsString();
                guiElement.setType(type);
            }
            if (jsonObject.getAsJsonPrimitive("image") != null) {
                String image = jsonObject.getAsJsonPrimitive("image").getAsString();
                guiElement.setImage(image);
            }
            try {
                float x = jsonObject.getAsJsonPrimitive("x").getAsFloat();
                float y = jsonObject.getAsJsonPrimitive("y").getAsFloat();
                float w = jsonObject.getAsJsonPrimitive("w").getAsFloat();
                float h = jsonObject.getAsJsonPrimitive("h").getAsFloat();
                Rectangle area = new Rectangle(x, y, w, h);
                guiElement.setArea(area);
            } catch (NullPointerException e) {
                System.out.printf("[Warning] Missing Area information, all 4 arguments must be present and valid. Skipping GUI element with name '%s'.\n", name);
                return null;
            }
            return guiElement;
        } else {
            return null;
        }
    }
}
