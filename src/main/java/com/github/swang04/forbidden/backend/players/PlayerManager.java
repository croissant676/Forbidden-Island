/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.players;

import dev.kason.forbidden.ui.PlayerTypeDistributor;

import java.util.HashSet;
import java.util.Set;

public class PlayerManager {

    private static final Set<Player> players = new HashSet<>();

    public static Set<Player> getPlayers() {
        return players;
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    public static Player generateTestPlayer() {
        Player player = new Player("Test_" + players.size(), PlayerTypeDistributor.getNextPlayerType());
        // Development stage only
        return player;
    }

}
