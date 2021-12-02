/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.players;

import com.github.swang04.forbidden.backend.treasure.TreasureCard;
import com.github.swang04.forbidden.backend.treasure.TreasureDeck;
import com.github.swang04.forbidden.backend.treasure.TreasureDeckCard;
import dev.kason.forbidden.Log;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

public class PlayerManager {

    private static PlayerManager instance;
    private static int number = 0;
    private static final Logger logger = Log.logger();

    private final Set<Player> players = new HashSet<>();
    private final TreasureDeck deck = new TreasureDeck();

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

    private TreasureDeckCard validateCard(TreasureDeckCard card) {
        if (TreasureCard.isWatersRise(card)) {
            deck.addCard(card);
            deck.shuffle();
            return validateCard(deck.popTopCard());
        }
        return card;
    }

    public TreasureDeck getDeck() {
        return deck;
    }

    private Player currentPlayer;

    @Contract(" -> new")
    public static @NotNull Player generateTestPlayer() {
        return new Player("Test:" + number++);
    }

    public Player nextTurn() {
        if (!playerIterator.hasNext()) {
            playerIterator = players.iterator();
        }
        return currentPlayer = playerIterator.next();
    }

    public Player getCurrentPlayerTurn() {
        if (currentPlayer == null) currentPlayer = playerIterator.next();
        return currentPlayer;
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Player player : players) {
            builder.append(player).append("\n");
        }
        builder.append("It is currently ").append(getCurrentPlayerTurn().getName()).append("'s turn");
        return builder.toString();
    }
}
