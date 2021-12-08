/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.players;

import com.github.swang04.forbidden.backend.treasure.InventoryItem;
import com.github.swang04.forbidden.backend.treasure.TreasureDeckCard;
import dev.kason.forbidden.PlayerTypeDistributor;
import dev.kason.forbidden.ui.PlayerInventoryVisualizer;

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

    public boolean receiveCard(TreasureDeckCard card) {
        card.setHolder(this);
        inventoryItems.add(card);
        return inventoryItems.size() <= 5;
    }

    public void transferCard(Player player, TreasureDeckCard card) {
        if (!inventoryItems.contains(card)) {
            JOptionPane.showMessageDialog(null, "You cannot transfer a card you don't have.", "Forbidden Island > Transfer Card", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (player.getInventoryItems().size() >= 5) {
            JOptionPane.showMessageDialog(null,
                    player.getPlayerType().getName() + " already has 5 cards, so they cannot receive another another card. Please have them discard a card first, and then transfer.",
                    "Forbidden Island > Too Many Cards",
                    JOptionPane.WARNING_MESSAGE
            );
        }
        inventoryItems.remove(card);
        card.setHolder(player);
        player.inventoryItems.add(card);
        System.out.println(inventoryItems);
        PlayerInventoryVisualizer.updateHand(this);
        PlayerInventoryVisualizer.updateHand(player);
        PlayerManager.getInstance().decrementActionsLeft();
        System.out.println("Gave " + player.getPlayerType().getName() + " card from player " + playerType.getName());
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
