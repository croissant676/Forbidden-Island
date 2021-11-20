/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package dev.kason.forbidden.ui;

import com.github.swang04.forbidden.backend.players.PlayerType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerTypeDistributor {

    private static final ArrayList<PlayerType> playerTypes = new ArrayList<>(List.of(PlayerType.values()));

    static {
        Collections.shuffle(playerTypes);
    }

    public static PlayerType getNextPlayerType() {
        return null;
    }
}
