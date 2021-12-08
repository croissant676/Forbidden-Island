/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.players;

import com.github.swang04.forbidden.backend.Game;
import com.github.swang04.forbidden.backend.board.Board;
import com.github.swang04.forbidden.backend.treasure.FloodDeck;
import com.github.swang04.forbidden.backend.treasure.InventoryItem;
import com.github.swang04.forbidden.backend.treasure.Treasure;
import com.github.swang04.forbidden.backend.treasure.TreasureCard;
import com.github.swang04.forbidden.backend.treasure.TreasureDeck;
import com.github.swang04.forbidden.backend.treasure.TreasureDeckCard;
import com.github.swang04.forbidden.backend.treasure.WatersRiseCard;
import com.github.swang04.forbidden.ui.LossView;
import com.github.swang04.forbidden.ui.WinView;
import dev.kason.forbidden.Log;
import dev.kason.forbidden.ui.BoardUI;
import dev.kason.forbidden.ui.GameVisualizer;
import dev.kason.forbidden.ui.PlayerInventoryVisualizer;
import dev.kason.forbidden.ui.UIBackendLinker;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.swing.JOptionPane;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class PlayerManager {

    private static PlayerManager instance;
    private static int number = 0;
    private static final Logger logger = Log.logger();

    private final Set<Player> players = new HashSet<>();
    private final TreasureDeck deck = new TreasureDeck();

    private final Set<Treasure> wonTreasures = new LinkedHashSet<>();

    private Iterator<Player> playerIterator;

    public PlayerManager(String... playerNames) {
        instance = this;
        createPlayers(playerNames);
        playerIterator = players.iterator();
        givePlayersInitialCards();
    }

    private void givePlayersInitialCards() {
        for (Player player : players) {
            player.receiveCard(validateCard(deck.popTopCard()));
            player.receiveCard(validateCard(deck.popTopCard()));
        }
    }

    public void discardOf(TreasureDeckCard card) {
        deck.getDiscard().add(card);
        Player player = card.getHolder();
        player.getInventoryItems().remove(card);
        PlayerInventoryVisualizer.updateHand(player);
    }

    private static InventoryItem currentlySelectedItem;

    public TreasureDeck getDeck() {
        return deck;
    }

    private Player currentPlayer;

    @Contract(" -> new")
    public static @NotNull Player generateTestPlayer() {
        return new Player("Test:" + number++);
    }

    public static InventoryItem getCurrentlySelectedItem() {
        if (currentlySelectedItem == null) {
            setDefaultItem();
        }
        return currentlySelectedItem;
    }

    private double numberOfActionsLeft = 3.0;

    public static void setDefaultItem() {
        List<InventoryItem> inventoryItems = getInstance().getCurrentPlayer().getInventoryItems();
        if (inventoryItems.isEmpty()) {
            for (Player player : getInstance().getPlayers()) {
                if (!player.getInventoryItems().isEmpty()) {
                    currentlySelectedItem = player.getInventoryItems().get(0);
                    break;
                }
            }
            return;
        }
        currentlySelectedItem = inventoryItems.get(0);
    }

    public static boolean isTemp = false;

    public void nextTurn() {
        isTemp = false;
        if (!playerIterator.hasNext()) {
            playerIterator = players.iterator();
        }
        int conditions = Game.getGame().checkForWinLossConditions();
        switch (conditions) {
            case Game.LOSS -> {
                LossView.runView();
                return;
            }
            case Game.WIN -> {
                WinView.runView();
                return;
            }
            default -> {
            }
        }
        Board board = Board.getInstance();
        FloodDeck deck = board.getFloodDeck();
        deck.floodBasedOnWaterMeter();
        TreasureDeck treasureDeck = instance.deck;
        for (int count = 0; count < 2; count++) {
            TreasureDeckCard deckCard = treasureDeck.popTopCard();
            if (deckCard instanceof WatersRiseCard watersRiseCard) {
                JOptionPane.showMessageDialog(null, "You drew a waters rise card!", "Forbidden Island > Waters Rise!", JOptionPane.INFORMATION_MESSAGE);
                watersRiseCard.apply();
            } else {
                if (currentPlayer.getInventoryItems().size() == 5) {
                    String init = "Don't add the new cards";
                    String other = "Allow me to discard some cards first";
                    String str = (String) JOptionPane.showInputDialog(null, "You have too many cards. Please discard or use them, then click the skip move button.", "Forbidden Island > Too Many Cards", JOptionPane.WARNING_MESSAGE, null, new Object[]{init, other}, init);
                    if (str == null || str.equals(other)) {
                        numberOfActionsLeft = 0.0;
                        JOptionPane.showMessageDialog(null, "Discard or use the cards, then press \"Skip Turn\".", "Forbidden Island > Skip Message Dialog", JOptionPane.INFORMATION_MESSAGE);
                        isTemp = true;
                        return;
                    } else {
                        break;
                    }
                }
                currentPlayer.receiveCard(deckCard);
            }
        }
        numberOfActionsLeft = 3.0;
        PlayerInventoryVisualizer.updateHand(currentPlayer);
        BoardUI.getInstance().matchGame();
        currentPlayer = playerIterator.next();
        UIBackendLinker.paintMovements();
    }

    public Set<Treasure> getWonTreasures() {
        return wonTreasures;
    }

    public void decrementActionsLeft() {
        numberOfActionsLeft--;
        checkPlayerActions();
        GameVisualizer.getInstance().updateCurPlayerLabel();
        UIBackendLinker.paintMovements();
    }

    public double getNumberOfActionsLeft() {
        return numberOfActionsLeft;
    }

    public void engineerActions() {
        numberOfActionsLeft -= 0.5;
        checkPlayerActions();
        GameVisualizer.getInstance().updateCurPlayerLabel();
    }

    public void checkPlayerActions() {
        if (numberOfActionsLeft < 0.4) {
            nextTurn();
            numberOfActionsLeft = 3.0;
        }
    }

    public void createPlayers(String @NotNull ... playerNames) {
        for (String playerName : playerNames) {
            Player player = new Player(playerName);
            logger.info(player.toString());
            players.add(player);
        }
    }

    public static PlayerManager getInstance() {
        return instance;
    }

    public int getNumberOfPlayers() {
        return players.size();
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public static void setCurrentlySelectedItem(InventoryItem currentlySelectedItem) {
        PlayerManager.currentlySelectedItem = currentlySelectedItem;
        // UI
        GameVisualizer.getInstance().updateSelectedItemComponent();
    }

    public TreasureDeckCard validateCard(TreasureDeckCard card) {
        if (TreasureCard.isWatersRise(card)) {
            deck.addCard(card);
            deck.shuffle();
            return validateCard(deck.popTopCard());
        }
        return card;
    }

    public Player getCurrentPlayer() {
        if (currentPlayer == null) currentPlayer = playerIterator.next();
        return currentPlayer;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Player player : players) {
            builder.append(player).append("\n");
        }
        builder.append("It is currently ").append(getCurrentPlayer().getName()).append("'s turn");
        return builder.toString();
    }
}
