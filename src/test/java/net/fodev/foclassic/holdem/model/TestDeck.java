package net.fodev.foclassic.holdem.model;

import org.junit.Test;

public class TestDeck {

    @Test
    public void constructor_Single_Valid() {
        Deck deck = new Deck();
        deck.getCards().stream().forEach(c -> {
            System.out.println(c);
        });
    }

    @Test
    public void overTheTopShuffle_Single_Valid() {
        Deck deck = new Deck();
        System.out.println(deck.toString());
        deck.overTheTopShuffle();
        System.out.println(deck.toString());
    }

    @Test
    public void riffleShuffle_Single_Valid() {
        Deck deck = new Deck();
        System.out.println(deck.toString());
        deck.riffleShuffle();
        System.out.println(deck.toString());

    }

    @Test
    public void cut_Single_Valid() {
        Deck deck = new Deck();
        System.out.println(deck.toString());
        int middle = (int)(Math.random() * 52);
        System.out.println("Cutting at: " + middle);
        deck.cut(middle);
        System.out.println(deck.toString());
    }

    @Test
    public void shuffle_Multiple_Valid() {
        Deck deck = new Deck();
        System.out.println(deck.toString());
        deck.shuffle(5);
        System.out.println(deck.toString());
    }
}
