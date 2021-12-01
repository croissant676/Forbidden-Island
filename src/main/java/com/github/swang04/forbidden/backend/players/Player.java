/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.players;

import com.github.swang04.forbidden.backend.treasure.InventoryItem;
import com.github.swang04.forbidden.backend.treasure.TreasureDeckCard;
import dev.kason.forbidden.PlayerTypeDistributor;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final Pawn pawn;
    private final PlayerType playerType;
    private final String name;
    private final List<InventoryItem> inventoryItems;

    public Player(String name) {
        this(name, PlayerTypeDistributor.getNextPlayerType());
    }

    public Player(String name, PlayerType type) {
        this.inventoryItems = new ArrayList<>();
        this.playerType = type;
        this.pawn = new Pawn(this);
        this.name = name;
    }

    public void receiveCard(TreasureDeckCard card) {
        inventoryItems.add(card);
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

    public List<InventoryItem> getInventoryItems() {
        return inventoryItems;
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
                ", cards=" + inventoryItems +
                '}';
    }
}
