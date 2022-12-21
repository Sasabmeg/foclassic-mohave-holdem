package net.fodev.foclassic.holdem.model;

import lombok.Getter;
import lombok.Setter;

public class Card {

    public enum Suit {
        Spade("Spade"), Heart("Heart"), Diamond("Diamond"), Club("Club");

        public final String label;

        Suit(String label) {
            this.label = label;
        }
    }

    public enum Value {
        Two("2"), Three("3"), Four("4"), Five("5"), Six("6"), Seven("7"), Eight("8"),
        Nine("9"), Ten("10"), Jack("J"), Queen("Q"), King("K"), Ace("A");

        public final String label;

        Value(String label) {
            this.label = label;
        }
    }

    @Getter @Setter private Suit suit;
    @Getter @Setter private Value value;
    @Getter @Setter private String imageSource;

    public Card() {
    }

    public Card(Suit suit, Value value, String imageSource) {
        this.suit = suit;
        this.value = value;
        this.imageSource = imageSource;
    }

    @Override
    public String toString() {
        return String.format("(%s %s %s)", suit.label, value.label, imageSource);
    }

    public String toShortString() {
        return String.format("%s-%s", suit.label, value.label);
    }
}
