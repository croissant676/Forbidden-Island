/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.backend.board;

import com.github.swang04.forbidden.backend.Game;
import com.github.swang04.forbidden.backend.players.Pawn;
import com.github.swang04.forbidden.backend.players.Player;
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
    private final Set<Pawn> pawns;

    public PawnManager(@NotNull Game reference) {
        this.reference = reference;
        this.manager = reference.getPlayerManager();
        this.board = reference.getBoard();
        this.pawns = new HashSet<>();
        for (Player player : manager.getPlayers()) {
            pawns.add(player.getPawn());
        }
    }

    public Set<Pawn> getPawns() {
        return pawns;
    }

    public List<Pawn> getPawnsFor(Tile tile) {
        List<Pawn> pawns1 = new ArrayList<>();
        for (Pawn pawn : pawns) {
            if (pawn.getTile().equals(tile)) {
                pawns1.add(pawn);
            }
        }
        return pawns1;
    }

    public List<Pawn> getPawnsFor(int x, int y) {
        return getPawnsFor(Board.getInstance().getTileAt(x, y));
    }

    public List<Pawn>[][] allPawnsNullable() {
        List<Pawn>[][] array = new List[6][6];
        for (Pawn pawn : pawns) {
            int x = pawn.getTile().getX();
            int y = pawn.getTile().getY();
            if (array[x][y] == null) {
                List<Pawn> temp = new ArrayList<>();
                temp.add(pawn);
                array[x][y] = temp;
            } else {
                array[x][y].add(pawn);
            }
        }
        return array;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String[][] array = new String[6][6];
        String str = " ".repeat(20);
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                Tile tile = board.getTileAt(row, col);
                if (tile != null) {
                    List<Pawn> pawns = new ArrayList<>();
                    for (Pawn pawn : this.pawns) {
                        if (pawn.getTile().equals(tile)) {
                            pawns.add(pawn);
                        }
                    }
                    if (pawns.isEmpty()) {
                        String string = tile.managerRep();
                        string = string + " ".repeat(20 - string.length());
                        array[row][col] = string;
                        continue;
                    } else {
                        int count = 17 - pawns.size() * 3;
                        String string = tile.managerRep();
                        string = string + " ".repeat(count - string.length());
                        StringBuilder builder1 = new StringBuilder();
                        for (Pawn pawn : pawns) {
                            builder1.append("[").append(pawn.getPlayer().getName().charAt(0)).append("]");
                        }
                        array[row][col] = string + builder1 + "   ";
                    }
                    continue;
                }
                array[row][col] = str;
            }
        }
        for (String[] strings : array) {
            for (String string : strings) {
                builder.append(string);
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
