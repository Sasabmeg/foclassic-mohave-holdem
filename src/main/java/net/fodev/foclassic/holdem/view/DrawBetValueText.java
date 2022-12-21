package net.fodev.foclassic.holdem.view;

import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.font.Rectangle;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;

public class DrawBetValueText {
    public static void drawText(Node parentNode, BitmapFont font, int x, int y, String text) {
        BitmapText hudText = new BitmapText(font, false);
        hudText.setSize(font.getCharSet().getRenderedSize());      // font size
        hudText.setColor(ColorRGBA.fromRGBA255(98, 217, 62, 255));                             // font color
        hudText.setText(text);             // the text
        hudText.setLocalTranslation(x, y + hudText.getLineHeight(), 0); // position
        parentNode.attachChild(hudText);
    }

    public static void drawTextBox(Node parentNode, BitmapFont font, Rectangle rectangle, String text, ColorRGBA color, BitmapFont.Align align) {
        BitmapText hudText = new BitmapText(font, false);
        hudText.setSize(font.getCharSet().getRenderedSize());      // font size
        hudText.setColor(color);                             // font color
        hudText.setText(text);             // the text
        //hudText.setLocalTranslation(x, y + hudText.getLineHeight(), 0); // position
        hudText.setBox(rectangle);
        hudText.setAlignment(align);
        hudText.setVerticalAlignment(BitmapFont.VAlign.Center);
        parentNode.attachChild(hudText);
    }
}
