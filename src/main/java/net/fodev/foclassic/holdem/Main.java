package net.fodev.foclassic.holdem;

import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.font.Rectangle;
import com.jme3.math.ColorRGBA;
import com.jme3.system.AppSettings;
import net.fodev.foclassic.holdem.view.DrawBetValueText;
import net.fodev.foclassic.holdem.view.GuiPicture;

public class Main extends SimpleApplication {
    private int width = 800;
    private int height = 600;

    public static void main(String[] args) {
        Main app = new Main();

        app.setShowSettings(false);

        AppSettings settings = new AppSettings(true);
        settings.setTitle("FOClassic - Holdem");
        settings.setWidth(app.width);
        settings.setHeight(app.height);
        settings.setFrameRate(60);
        app.setSettings(settings);

        app.start();;
    }

    @Override
    public void simpleInitApp() {
        try {
            init();
            initBackground();
            initText();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        flyCam.setEnabled(false);
        setDisplayStatView(false);
        setDisplayFps(false);
    }

    private void initBackground() {
        GuiPicture background = new net.fodev.foclassic.holdem.view.GuiPicture("Background", assetManager, "images/background/table-mojave-holdem-base.png", true);
        background.setCenterPosition(width / 2, height / 2, -100);
        guiNode.attachChild(background);
    }

    private void initText() {
        BitmapFont fallouty_12 = assetManager.loadFont("fonts/Fallouty_12.fnt");
        DrawBetValueText.drawTextBox(guiNode, fallouty_12, new Rectangle(404, height - 563, 85, 14), "18,760", BitmapFont.Align.Center);
    }



    @Override
    public void simpleUpdate(float tpf) {

    }
}
