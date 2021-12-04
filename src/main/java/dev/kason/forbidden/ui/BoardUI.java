/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package dev.kason.forbidden.ui;

import com.github.swang04.forbidden.backend.Game;
import com.github.swang04.forbidden.backend.board.Board;
import com.github.swang04.forbidden.backend.board.Tile;
import com.github.swang04.forbidden.backend.players.Pawn;
import dev.kason.forbidden.ImageStorage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.List;

// 3 ways to get this
// BoardUI.getInstance()
// ViewManager.getBoardUI()
// BoardVisualizer.getInstance().getBoardUI()
public class BoardUI {

    private static BoardUI ui;
    private final JButton[][] buttons;
    private final JPanel mergedPanel;

    public static final int[] x = {5, 50, 5, 50};
    public static final int[] y = {5, 5, 50, 50};

    // All tiles should be 100 x 100
    public BoardUI() {
        buttons = new JButton[6][6];
        mergedPanel = new JPanel();
        ViewManager.setBoardUI(this);
        GridLayout layout = new GridLayout(6, 6, 5, 5);
        mergedPanel.setBackground(ViewManager.getTransparent());
        mergedPanel.setLayout(layout);
        ui = this;
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                JButton button = new JButton();
                buttons[row][col] = button;
                button.setSize(110, 110);
                mergedPanel.add(button);
                int finalRow = row;
                int finalCol = col;
                button.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        UIBackendLinker.establishClick(Board.getInstance().getTileAt(finalRow, finalCol), e);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                });
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

    private static BufferedImage copyImage(BufferedImage source) {
        BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics g = b.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return b;
    }

    public void matchGame() {
        showTilesAndPawns();
    }

    public JButton getButtonAt(int x, int y) {
        return buttons[x][y];
    }

    public void showTilesAndPawns() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                JButton button = buttons[row][col];
                Tile tile = Board.getInstance().getTileAt(row, col);
                if (tile != null) {
                    switch (tile.getTileState()) {
                        case DRY -> {
                            BufferedImage regularImage = tile.getTileType().getRegularImage();
                            ImageIcon icon = new ImageIcon(addPawns(regularImage, tile));
                            button.setIcon(icon);
                        }
                        case FLOODED -> {
                            BufferedImage regularImage = tile.getTileType().getFloodedImage();
                            ImageIcon icon = new ImageIcon(addPawns(regularImage, tile));
                            button.setIcon(icon);
                        }
                        case SUNK -> {
                            BufferedImage regularImage = ViewManager.getSurroundingWatersCropped();
                            ImageIcon icon = new ImageIcon(addPawns(regularImage, tile));
                            button.setIcon(icon);
                        }
                    }
                } else {
                    ImageIcon icon = new ImageIcon(ViewManager.getSurroundingWatersCropped());
                    button.setIcon(icon);
                }
            }
        }
    }

    private BufferedImage addPawns(BufferedImage reg, Tile tile) {
        List<Pawn> pawnList = Game.getGame().getPawnManager().getPawnsFor(tile);
        if (pawnList.isEmpty()) return reg;
        BufferedImage copy = copyImage(reg);
        Graphics2D graphics2D = copy.createGraphics();
        for (int index = 0; index < pawnList.size(); index++) {
            Pawn pawn = pawnList.get(index);
            BufferedImage image = ImageStorage.retrieveImage(pawn.getPlayerType().getFileLocation());
            graphics2D.drawImage(ViewManager.getScaledImage(image, 25, 45), x[index], y[index], null);
        }
        return copy;
    }
}
