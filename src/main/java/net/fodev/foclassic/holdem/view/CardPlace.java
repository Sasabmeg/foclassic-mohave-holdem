package net.fodev.foclassic.holdem.view;

import lombok.Getter;
import lombok.Setter;

public class CardPlace {
    @Getter @Setter private String name;
    @Getter @Setter private int x;
    @Getter @Setter private int y;

    public CardPlace() {
    }

    public CardPlace(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
}
