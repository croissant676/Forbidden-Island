/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.board;

import com.github.swang04.forbidden.backend.Game;
import com.github.swang04.forbidden.backend.players.Pawn;
import com.github.swang04.forbidden.backend.players.PlayerManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PawnManager {

    private final PlayerManager manager;
    private final Game reference;
    private final Board board;
    private Set<Pawn> pawns;

    public PawnManager(@NotNull Game reference) {
        this.reference = reference;
        this.manager = reference.getPlayerManager();
        this.board = reference.getBoard();
    }

    public Set<Pawn> getPawns() {
        return pawns;
    }

    public void displayPawnLocations() {
        StringBuilder builder = new StringBuilder();
        String[][] array = new String[6][6];
        String str = " ".repeat(15);
        Set<Pawn> remainingPawns = new HashSet<>(pawns);
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                Tile tile = board.getTileAt(row, col);
                if (tile != null) {
                    List<Pawn> pawns = new ArrayList<>();
                    for (Pawn remainingPawn : remainingPawns) {
                        if (remainingPawn.getTile().getX() == row && remainingPawn.getTile().getY() == col) {
                            pawns.add(remainingPawn);
                        }
                    }
                    if (pawns.isEmpty()) {

                        continue;
                    }
                    continue;
                }
                array[row][col] = str;
            }
        }
    }
}
