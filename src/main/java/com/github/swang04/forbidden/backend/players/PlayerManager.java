/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.players;

import dev.kason.forbidden.logging.Log;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class PlayerManager {

    private static PlayerManager instance;
    private static int number = 0;
    private final Set<Player> players = new HashSet<>();
    private static final Logger logger = Log.logger();

    public PlayerManager(String... playerNames) {
        instance = this;
        createPlayers(playerNames);
    }

    public static Player generateTestPlayer() {
        return new Player("Test:" + number++);
    }

    public void createPlayers(String... playerNames) {
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
        return builder.toString();
    }
}
