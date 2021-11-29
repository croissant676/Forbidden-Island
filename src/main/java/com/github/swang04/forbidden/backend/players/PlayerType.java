/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.players;

import java.util.List;

public enum PlayerType {
    EXPLORER {

    },
    DIVER {

    };

    public List<Move> getMoves(Pawn pawn) {
        return null;
    }
}
