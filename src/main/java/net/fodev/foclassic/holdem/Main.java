package net.fodev.foclassic.holdem;

import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapFont;
import com.jme3.font.Rectangle;
import com.jme3.math.ColorRGBA;
import com.jme3.system.AppSettings;
import net.fodev.foclassic.holdem.dataAccess.IniParser;
import net.fodev.foclassic.holdem.model.Card;
import net.fodev.foclassic.holdem.view.CardPlace;
import net.fodev.foclassic.holdem.view.Colors;
import net.fodev.foclassic.holdem.view.DrawBetValueText;
import net.fodev.foclassic.holdem.view.GuiPicture;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends SimpleApplication {
    private int width = 800;
    private int height = 600;

    private CardPlace dealerDeckPlace;
    private List<CardPlace> communityCardsPlaces;
    private List<CardPlace> playerOneCardsPlaces;
    private List<CardPlace> playerTwoCardsPlaces;
    private List<CardPlace> playerThreeCardsPlaces;
    private List<CardPlace> playerFourCardsPlaces;
    private List<CardPlace> playerFiveCardsPlaces;
    private List<CardPlace> playerSixCardsPlaces;

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
            initIni();
            initBackground();
            initText();
            initCardPlaces();
            initCards();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initCardPlaces() {
        communityCardsPlaces = new ArrayList<>();
        communityCardsPlaces.add(new CardPlace("flop1", 283, 400));
        communityCardsPlaces.add(new CardPlace("flop2", 328, 400));
        communityCardsPlaces.add(new CardPlace("flop3", 372, 400));
        communityCardsPlaces.add(new CardPlace("turn", 416, 400));
        communityCardsPlaces.add(new CardPlace("river", 460, 400));
    }

    private void initCards() {
        Card aceOfSpades = new Card(Card.Suit.Spade, Card.Value.Ace, "images/deck/worn-spades-A.png");
        GuiPicture flop1 = new GuiPicture("flop1", assetManager, aceOfSpades.getImageSource(), true);
        flop1.setBottomLeftPosition(communityCardsPlaces.get(0).getX(), communityCardsPlaces.get(0).getY(), 0);
        guiNode.attachChild(flop1);

        Card threeOfSpades = new Card(Card.Suit.Spade, Card.Value.Ace, "images/deck/worn-spades-3.png");
        GuiPicture flop2 = new GuiPicture("flop2", assetManager, threeOfSpades.getImageSource(), true);
        flop2.setBottomLeftPosition(communityCardsPlaces.get(1).getX(), communityCardsPlaces.get(1).getY(), 0);
        guiNode.attachChild(flop2);

        Card jackOfHearts = new Card(Card.Suit.Heart, Card.Value.Jack, "images/deck/worn-hearts-J.png");
        GuiPicture flop3 = new GuiPicture("flop3", assetManager, jackOfHearts.getImageSource(), true);
        flop3.setBottomLeftPosition(communityCardsPlaces.get(2).getX(), communityCardsPlaces.get(2).getY(), 0);
        guiNode.attachChild(flop3);

        Card queenOfHearts = new Card(Card.Suit.Heart, Card.Value.Queen, "images/deck/worn-hearts-Q.png");
        GuiPicture turn = new GuiPicture("turn", assetManager, queenOfHearts.getImageSource(), true);
        turn.setBottomLeftPosition(communityCardsPlaces.get(3).getX(), communityCardsPlaces.get(3).getY(), 0);
        guiNode.attachChild(turn);

        Card sevenOfSpades = new Card(Card.Suit.Spade, Card.Value.Seven, "images/deck/worn-spades-7.png");
        GuiPicture river = new GuiPicture("river", assetManager, sevenOfSpades.getImageSource(), true);
        river.setBottomLeftPosition(communityCardsPlaces.get(4).getX(), communityCardsPlaces.get(4).getY(), 0);
        guiNode.attachChild(river);

    }

    private void init() {
        flyCam.setEnabled(false);
        setDisplayStatView(false);
        setDisplayFps(false);
    }

    private void initIni() {
        IniParser parser = new IniParser();
        try {
            parser.parseFromFile("src/main/resources/mohave-holdem.ini");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void initBackground() {
        GuiPicture background = new GuiPicture("Background", assetManager, "images/background/table-mojave-holdem-base.png", true);
        background.setCenterPosition(width / 2, height / 2, -100);
        guiNode.attachChild(background);
    }

    private void initText() {
        BitmapFont fallouty_12 = assetManager.loadFont("fonts/Fallouty_12.fnt");
        DrawBetValueText.drawTextBox(guiNode, fallouty_12, new Rectangle(404, height - 563, 85, 14), "18,760", ColorRGBA.fromRGBA255(0, 0xC8, 0, 255), BitmapFont.Align.Center);
        DrawBetValueText.drawTextBox(guiNode, fallouty_12, new Rectangle(10, 100, 85, 14), "18,760", Colors.green, BitmapFont.Align.Center);
        DrawBetValueText.drawTextBox(guiNode, fallouty_12, new Rectangle(10, 120, 85, 14), "18,760", Colors.goldenYellow, BitmapFont.Align.Center);
    }



    @Override
    public void simpleUpdate(float tpf) {

    }
}
