package net.fodev.foclassic.holdem.model;

import lombok.Getter;
import lombok.Setter;
import net.fodev.foclassic.holdem.model.Card;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    @Getter @Setter private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        for (Card.Suit suit : Card.Suit.values()) {
            for(Card.Value value : Card.Value.values()) {
                cards.add(new Card(suit, value, String.format("images/deck/worn-%ss-%s.png", suit.label, value.label)));
            }
        }
    }

    public void shuffle(int times) {
        for (int i = 0; i < times; i++) {
            overTheTopShuffle();
            riffleShuffle();
            int middle = (int) (Math.random() * 52);
            cut(middle);
        }
    }

    public void cut(int middle) {
        for (int i = 0; i < middle; i++) {
            Card card = cards.get(0);
            cards.remove(0);
            cards.add(card);
        }
    }

    public void overTheTopShuffle() {
        List<Card> other = new ArrayList<>();
        while (!cards.isEmpty()) {
            int middle = (int)(Math.random() * 12) + 6;
            if (cards.size() > middle) {
                //System.out.println("Over the top cut at: " + middle);
                int shift = cards.size() - middle;
                for (int i = 0; i < middle; i++) {
                    other.add(cards.remove(shift));
                }
            } else {
                //System.out.println("Remaining: " + cards.size());
                int size = cards.size();
                for (int i = 0; i < size; i++) {
                    other.add(cards.remove(0));
                }
            }
        }
        cards = other;
    }

    public void riffleShuffle() {
        List<Card> bottom = new ArrayList<>();
        List<Card> top = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            top.add(cards.remove(26));
        }
        bottom = cards;
        cards = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            cards.add(bottom.remove(0));
            cards.add(top.remove(0));
        }
    }

    @Override
    public String toString() {
        String ret = "";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                ret += cards.get(i * 13 + j).toShortString() + " ";
            }
        }
        return ret;
    }
}
