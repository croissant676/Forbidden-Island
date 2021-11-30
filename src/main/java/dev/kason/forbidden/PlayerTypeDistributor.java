/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package dev.kason.forbidden;

import com.github.swang04.forbidden.backend.players.PlayerType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerTypeDistributor {

    private static final ArrayList<PlayerType> playerTypes = new ArrayList<>(List.of(PlayerType.values()));
    private static int currentIndex = 0;

    public static PlayerType getNextPlayerType() {
        if (currentIndex % 6 == 0) {
            Collections.shuffle(playerTypes);
        }
        return playerTypes.get(currentIndex++ % 6);
    }
}
