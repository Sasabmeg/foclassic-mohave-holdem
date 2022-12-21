package net.fodev.foclassic.holdem.dataAccess;

import com.google.gson.*;
import com.jme3.font.Rectangle;
import net.fodev.foclassic.holdem.model.GuiElement;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestGuiJsonParser {
    public final String initString = "{\n" +
            "    \"guiElements\": [\n" +
            "        {\n" +
            "            \"name\": \"table-base\",\n" +
            "            \"type\": \"image\",\n" +
            "            \"image\": \"table-mojave-base.png\",\n" +
            "            \"x\": 0, \"y\": 0, \"w\": 800, \"h\": 600\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"betValue\",\n" +
            "            \"type\": \"text\",\n" +
            "            \"x\": 404, \"y\": 22, \"w\": 85, \"h\": 14\n" +
            "        }\n" +
            "    ]\n" +
            "}";
    public final String invalidInitString = "{\n" +
            "    \"guiElements\": [\n" +
            "        {\n" +
            "            \"type\": \"image\",\n" +
            "            \"image\": \"table-mojave-base.png\",\n" +
            "            \"x\": 0, \"y\": 0, \"w\": 800, \"h\": 600\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"betValue\",\n" +
            "            \"type\": \"text\",\n" +
            "            \"x\": 404, \"y\": 22, \"w\": 85 \n" +
            "        }\n" +
            "    ]\n" +
            "}";
    public final String oneGuiElementAsJson = "{\n" +
            "            \"name\": \"table-base\",\n" +
            "            \"type\": \"image\",\n" +
            "            \"image\": \"table-mojave-base.png\",\n" +
            "            \"x\": 0, \"y\": 0, \"w\": 800, \"h\": 600\n" +
            "        }";

    public final GuiJsonParser parser = new GuiJsonParser();

    @Before
    public void init() {
    }

    @Test
    public void objectToString_Single_Valid() {
        GuiElement guiElement = new GuiElement("InterfaceMain", GuiElement.GuiEntityType.Image,
                "table-mojave-base.png", new Rectangle(0, 0, 800, 600));
        GuiJsonParser parser = new GuiJsonParser();
        System.out.println(parser.objectToString(guiElement));
    }

    @Test
    public void objectToString_Multiple_Valid() {
        GuiElement guiElement1 = new GuiElement("InterfaceMain", GuiElement.GuiEntityType.Image,
                "table-mojave-base.png", new Rectangle(0, 0, 800, 600));
        GuiElement guiElement2 = new GuiElement("InterfaceMain", GuiElement.GuiEntityType.Image,
                "table-mojave-sample.png", new Rectangle(0, 0, 800, 600));
        List<GuiElement> guiEntities = new ArrayList<>();
        guiEntities.add(guiElement1);
        guiEntities.add(guiElement2);
        GuiJsonParser parser = new GuiJsonParser();
        System.out.println(parser.objectListToString(guiEntities));
    }

    @Test
    public void stringToObject_Multiple_Valid() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement element = gson.fromJson(initString, JsonElement.class);
        System.out.println(element);
        String toJson = gson.toJson(element);
        System.out.println(toJson);
    }

    @Test
    public void jsonObjectToGuiElement_Single_Valid() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement jsonElement = gson.fromJson(oneGuiElementAsJson, JsonElement.class);
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            GuiElement guiElement = parser.jsonObjectToGuiElement(jsonObject);
            System.out.println(guiElement);
        }
    }

    @Test
    public void stringToGuiEntities_Multiple_Valid() {
        List<GuiElement> guiElements = parser.stringToGuiEntities(initString);
        System.out.println("Gui Elements in list: " + guiElements);
    }

    @Test
    public void stringToGuiEntities_Multiple_Invalid() {
        List<GuiElement> guiElements = parser.stringToGuiEntities(invalidInitString);
        System.out.println("Gui Elements in list: " + guiElements);
    }
}
