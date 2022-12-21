package net.fodev.foclassic.holdem.model;

import com.jme3.font.Rectangle;
import lombok.Getter;
import lombok.Setter;

public class GuiElement {
    public enum GuiEntityType {
        Image,
        Text,
        Button,
        Unknown
    }

    public GuiElement() {

    }

    public GuiElement(String name, GuiEntityType type, String image, Rectangle area) {
        this.name = name;
        this.type = type;
        this.image = image;
        this.area = area;
    }

    @Getter @Setter private String name;
    @Getter @Setter private GuiEntityType type;
    @Getter @Setter private String image;
    @Getter @Setter private Rectangle area;

    public void setType(String typeString) {
        if ("Image".equalsIgnoreCase(typeString)) {
            type = GuiEntityType.Image;
        } else if ("Text".equalsIgnoreCase(typeString)) {
            type = GuiEntityType.Text;
        } else if ("Button".equalsIgnoreCase(typeString)) {
            type = GuiEntityType.Button;
        } else {
            type = GuiEntityType.Unknown;
        }
    }

    //  why lombok doesn't work with custom setters...
    public void setType(GuiEntityType typearg) {
        type = typearg;
    }

    @Override
    public String toString() {
        if (area != null) {
            return String.format("{ name = %s, type = %s, image = %s, area = %d %d %d %d }",
                    name, type, image, (int)area.x, (int)area.y, (int)area.width, (int)area.height);
        } else {
            return String.format("{ name = %s, type = %s, image = %s, area = null }", name, type, image);
        }
    }

}
