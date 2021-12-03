/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.players;

import com.github.swang04.forbidden.backend.treasure.InventoryItem;
import com.github.swang04.forbidden.backend.treasure.TreasureDeck;
import com.github.swang04.forbidden.backend.treasure.TreasureDeckCard;
import dev.kason.forbidden.PlayerTypeDistributor;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        if (inventoryItems.size() >= 5) {
            JOptionPane.showMessageDialog(null,
                    "You already have 5 cards, so you cannot draw another card. Please discard or use one card."
            );
            TreasureDeck deck = PlayerManager.getInstance().getDeck();
            deck.pushCard(card);
            return;
        }
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

    @Override
    public String toString() {
        return "Player{" +
                "pawn=" + pawn +
                ", playerType=" + playerType +
                ", name='" + name + '\'' +
                ", cards=" + inventoryItems +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player player)) return false;
        return Objects.equals(pawn, player.pawn) && playerType == player.playerType && Objects.equals(name, player.name) && Objects.equals(inventoryItems, player.inventoryItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pawn, playerType, name, inventoryItems);
    }
}
