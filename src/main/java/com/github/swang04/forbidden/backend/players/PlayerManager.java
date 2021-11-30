/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.players;

import java.util.HashSet;
import java.util.Set;

public class PlayerManager {

    private static PlayerManager instance;
    private static int number = 0;
    private final Set<Player> players = new HashSet<>();

    public PlayerManager() {
        instance = this;
    }

    public static PlayerManager getInstance() {
        return instance;
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    public static Player generateTestPlayer() {
        Player player = new Player("Test:" + number++);
        // Development stage only
        return player;
    }

    public Set<Player> getPlayers() {
        return players;
    }

}
