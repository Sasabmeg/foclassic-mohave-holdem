package net.fodev.foclassic.holdem.dataAccess;

import net.fodev.foclassic.holdem.model.GuiElement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IniParser {
    public Map<String, String> parseFromFile(String filename) throws IOException {
        Map<String, String> map = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        do {
            line = reader.readLine();
            if (line != null && line.length() > 2) {
                line = line.trim();
                if (!line.startsWith("#")) {
                    String[] keyValue = line.split("=");
                    if (keyValue.length > 1) {
                        map.put(keyValue[0], keyValue[1]);
                        System.out.println(line);
                    } else {
                        System.out.println("Missing parameters at line: " + line);
                    }
                } else {
                    //System.out.println(line);
                }
            }
        } while (line != null);
        return map;
    }

    public List<GuiElement> parseMapToGuiEntity(Map<String, String> map) {
        List<GuiElement> guiEntities = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey().endsWith("Pic") || entry.getKey().endsWith("Img")) {
                String name = entry.getKey().substring(0, entry.getKey().length() - 3);
                if (guiEntities.stream()
                        .anyMatch(e -> name.equalsIgnoreCase(e.getName()))
                ) {
                    guiEntities.stream()
                            .filter(e -> name.equalsIgnoreCase(e.getName()))
                            .findFirst()
                            .get()
                            .setImage(entry.getValue());
                } else {
                    GuiElement guiElement = new GuiElement();
                    guiElement.setName(name);
                    guiElement.setImage(entry.getValue());
                    guiElement.setType(GuiElement.GuiEntityType.Image);
                    guiEntities.add(guiElement);
                }
            } else {
                String name = entry.getKey();
                if (guiEntities.stream()
                        .anyMatch(e -> name.equalsIgnoreCase(e.getName()))
                ) {
                    //  already exists, shouldn't, but let's overwrite
                } else {
                    GuiElement guiElement = new GuiElement();
                    guiElement.setName(name);
                    String split[] = entry.getValue().split("\\s+");

                    guiEntities.add(guiElement);
                }
            }
        }
        return guiEntities;
    }

}
