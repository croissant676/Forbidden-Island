/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package dev.kason.forbidden.ui;

import com.github.swang04.forbidden.backend.board.Board;
import com.github.swang04.forbidden.backend.board.Tile;
import dev.kason.forbidden.ImageStorage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;

// 3 ways to get this
// BoardUI.getInstance()
// ViewManager.getBoardUI()
// BoardVisualizer.getInstance().getBoardUI()
public class BoardUI {

    private static final Color buttonBackgroundColor = new Color(78, 84, 84);
    private static BoardUI ui;
    private final JButton[][] buttons;
    private final JPanel mergedPanel;

    // All tiles should be 100 x 100
    public BoardUI() {
        buttons = new JButton[6][6];
        mergedPanel = new JPanel();
        ViewManager.setBoardUI(this);
        GridLayout layout = new GridLayout(6, 6);
        mergedPanel.setLayout(layout);
        ui = this;
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                JButton button = new JButton();
                buttons[row][col] = button;
                mergedPanel.add(button);
            }
        }
        matchGame();
    }

    public static BoardUI getInstance() {
        return ui;
    }

    public JPanel getDisplay() {
        return mergedPanel;
    }

    public void matchGame() {
        updateTilesOnly();
    }

    public void updateTilesOnly() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                JButton button = buttons[row][col];
                Tile tile = Board.getInstance().getTileAt(row, col);
                if (tile != null) {
                    switch (tile.getTileState()) {
                        case DRY -> {
                            BufferedImage regularImage = tile.getTileType().getRegularImage();
                            regularImage = ViewManager.getScaledImage(regularImage, 100, 100);
                            ImageIcon icon = new ImageIcon(regularImage);
                            button.setIcon(icon);
                        }
                        case FLOODED -> {
                            BufferedImage regularImage = tile.getTileType().getFloodedImage();
                            regularImage = ViewManager.getScaledImage(regularImage, 100, 100);
                            ImageIcon icon = new ImageIcon(regularImage);
                            button.setIcon(icon);
                        }
                        case SUNK -> {
                            BufferedImage regularImage = ImageStorage.retrieveImage("surrounding_waters.png");
                            regularImage = ViewManager.getScaledImage(regularImage, 100, 100);
                            ImageIcon icon = new ImageIcon(regularImage);
                            button.setIcon(icon);
                        }
                    }
                } else {
                    BufferedImage regularImage = ImageStorage.retrieveImage("surrounding_waters.png");
                    regularImage = ViewManager.getScaledImage(regularImage, 100, 100);
                    ImageIcon icon = new ImageIcon(regularImage);
                    button.setIcon(icon);
                }
                button.setBorder(null);
                button.addMouseListener(new MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        button.setBackground(Color.GRAY);
                    }

                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        button.setBackground(buttonBackgroundColor);
                    }
                });
            }
        }
    }

    public JButton getButtonAt(int x, int y) {
        return buttons[x][y];
    }
}
