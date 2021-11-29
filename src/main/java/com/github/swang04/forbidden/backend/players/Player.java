/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.players;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final Pawn pawn;
    private final PlayerType playerType;
    private final String name;
    private final List<Card> cards;

    public Player(String name, PlayerType type) {
        this.cards = new ArrayList<>();
        this.playerType = type;
        this.pawn = new Pawn(this);
        this.name = name;
        PlayerManager.getPlayers().add(this);
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public Pawn getPawn() {
        return pawn;
    }

    public String getName() {
        return name;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public List<Card> getCards() {
        return cards;
    }

    public List<Move> getMoves() {
        return playerType.getMoves(pawn);
    }

    @Override
    public String toString() {
        return "Player{" +
                "pawn=" + pawn +
                ", playerType=" + playerType +
                ", name='" + name + '\'' +
                ", cards=" + cards +
                '}';
    }
}
