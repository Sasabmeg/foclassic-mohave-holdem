package net.fodev.foclassic.holdem;

import com.jme3.app.SimpleApplication;
import com.jme3.system.AppSettings;
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        flyCam.setEnabled(false);
        setDisplayStatView(false);
        //setDisplayFps(false);
    }

    private void initBackground() {
        GuiPicture background = new net.fodev.foclassic.holdem.view.GuiPicture("Background", assetManager, "images/table-mojave-holdem-sample.png", true);
        background.setCenterPosition(width / 2, height / 2, -100);
        guiNode.attachChild(background);
    }

    @Override
    public void simpleUpdate(float tpf) {

    }
}
