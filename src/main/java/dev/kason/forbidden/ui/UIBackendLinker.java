/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package dev.kason.forbidden.ui;

import com.github.swang04.forbidden.backend.board.Board;
import com.github.swang04.forbidden.backend.board.Tile;
import com.github.swang04.forbidden.backend.players.Player;
import com.github.swang04.forbidden.backend.players.PlayerManager;
import com.github.swang04.forbidden.backend.players.PlayerType;
import com.github.swang04.forbidden.backend.treasure.HeliLiftCard;
import com.github.swang04.forbidden.backend.treasure.InventoryItem;
import com.github.swang04.forbidden.backend.treasure.SandbagsCard;

import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.Set;

public class UIBackendLinker {

    public static void establishClick(Tile tile, MouseEvent event) {
        InventoryItem card = PlayerManager.getCurrentlySelectedItem();
        Player player = PlayerManager.getInstance().getCurrentPlayer();
        if (card instanceof SandbagsCard sandbagsCard) {
            tile.shoreUp();
            PlayerManager.getInstance().discardOf(sandbagsCard);
            PlayerManager.setDefaultItem();
            GameVisualizer.getInstance().updateSelectedItemComponent();
            BoardUI.getInstance().matchGame();
            return;
        } else if(card instanceof HeliLiftCard heliLiftCard) {
            player.getPawn().setTile(tile);
            PlayerManager.getInstance().discardOf(heliLiftCard);
            PlayerManager.setDefaultItem();
            GameVisualizer.getInstance().updateSelectedItemComponent();
            BoardUI.getInstance().matchGame();
            return;
        }
        if(PlayerManager.isTemp) return;
        if (event.getButton() == MouseEvent.BUTTON3) {

            Set<Tile> tiles = player.getPlayerType().getShoreUp();
            if (tiles.contains(tile)) {
                tile.shoreUp();
                if (player.getPlayerType() == PlayerType.ENGINEER) {
                    PlayerManager.getInstance().engineerActions();
                } else {
                    PlayerManager.getInstance().decrementActionsLeft();
                }
            } else {
                JOptionPane.showMessageDialog(null, "You can't shore up that tile.", "Forbidden Island > Shore Up", JOptionPane.WARNING_MESSAGE);
            }
            System.out.println(player.getPlayerType().getName() + " shored up " + tile.getEffectiveName());
        } else {
            if(currentNavigatorSelected != null) {
                if(currentNavigatorSelected.getPlayerType().getDefaultMovements().contains(tile)) {
                    currentNavigatorSelected.getPawn().setTile(tile);
                    currentNavigatorSelected = null;
                    PlayerManager.setDefaultItem();
                    paintMovements();
                    GameVisualizer.getInstance().updateSelectedItemComponent();
                    BoardUI.getInstance().matchGame();
                    PlayerManager.getInstance().decrementActionsLeft();
                } else {
                    JOptionPane.showMessageDialog(null, "You can't make " + currentNavigatorSelected.getPlayerType().getName() + " move there.", "Forbidden Island > Navigator Movement" ,  JOptionPane.WARNING_MESSAGE);
                }
                return;
            }
            if(player.getPlayerType() == PlayerType.NAVIGATOR) {
                Player selectedPlayer = null;
                for (Player player1 : PlayerManager.getInstance().getPlayers()) {
                    if(player1.getPawn().getTile().equals(tile)) {
                        selectedPlayer = player1;
                        break;
                    }
                }
                if(selectedPlayer != null){
                    currentNavigatorSelected = selectedPlayer;
                    paintMovements();
                }
                return;
            }
            if (player.getPawn().getTile().equals(tile)) {
                JOptionPane.showMessageDialog(null, "There are better ways to waste an action. Try pressing the skip button.", "Forbidden Island > Movements", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Set<Tile> tiles = player.getPlayerType().getMovements();
            if (tiles.contains(tile)) {
                player.getPawn().setTile(tile);
                PlayerManager.getInstance().decrementActionsLeft();
            } else {
                JOptionPane.showMessageDialog(null, "You can't move to that tile.", "Forbidden Island > Movements", JOptionPane.WARNING_MESSAGE);
            }
            System.out.println(player.getPlayerType().getName() + " moved to " + tile.getEffectiveName());
        }
        paintMovements();
        GameVisualizer.getInstance().repaintPanels();
        BoardUI.getInstance().matchGame();
    }

    private static Player currentNavigatorSelected;

    public static void paintMovements() {
        Player player = PlayerManager.getInstance().getCurrentPlayer();
        Set<Tile> tiles = player.getPawn().getPossibleTiles();
        Board board = Board.getInstance();
        if (player.getPlayerType().shouldDiffSUAndMovements()) {
            Set<Tile> shoreUp = player.getPlayerType().getShoreUp();
            for (int row = 0; row < 6; row++) {
                for (int col = 0; col < 6; col++) {
                    Tile tile = board.getTileAt(row, col);
                    if (tile == null) continue;
                    if (tiles.contains(tile)) {
                        if (shoreUp.contains(tile)) {
                            tile.getButton().setBackground(new Color(255, 129, 0, 219));
                        } else {
                            tile.getButton().setBackground(Color.YELLOW);
                        }
                    } else if (player.getPawn().getTile().equals(tile)) {
                        tile.getButton().setBackground(Color.RED);
                    } else {
                        tile.getButton().setBackground(BoardUI.BUTTON_COLOR);
                    }
                }
            }
        } else {
            if(currentNavigatorSelected != null) {
                for (int row = 0; row < 6; row++) {
                    for (int col = 0; col < 6; col++) {
                        BoardUI.getInstance().getButtonAt(row, col).setBackground(BoardUI.BUTTON_COLOR);
                    }
                }
                for (Tile defaultMovement : currentNavigatorSelected.getPlayerType().getDefaultMovements()) {
                    defaultMovement.getButton().setBackground(Color.YELLOW);
                }
                currentNavigatorSelected.getPawn().getTile().getButton().setBackground(Color.RED);
                return;
            }
            for (int row = 0; row < 6; row++) {
                for (int col = 0; col < 6; col++) {
                    Tile tile = board.getTileAt(row, col);
                    if (tile == null) continue;
                    if (tiles.contains(tile)) {
                        tile.getButton().setBackground(Color.YELLOW);
                    } else if (player.getPawn().getTile().equals(tile)) {
                        tile.getButton().setBackground(Color.RED);
                    } else {
                        tile.getButton().setBackground(BoardUI.BUTTON_COLOR);
                    }
                }
            }
            if(player.getPlayerType() == PlayerType.NAVIGATOR) {
                for (Player player1 : PlayerManager.getInstance().getPlayers()) {
                    player1.getPawn().getTile().getButton().setBackground(Color.PINK);
                }
            }
        }
        player.getPawn().getTile().getButton().setBackground(Color.RED);
    }

}
