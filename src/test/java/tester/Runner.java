package tester;

import game.Game;
import game.board.Board;
import game.board.Tile;
import ui.InterfaceManager;
import utils.LogHandler;

import java.util.logging.Logger;

public class Runner {
    public static void main(String[] args) {
        System.setProperty(LogHandler.getConfigContext(), "true");
        // STOPSHIP: 10/15/2021 Remove during production
        Logger logger = LogHandler.getLogger();
        InterfaceManager.initialize();
        Tile tile = Tile.getSample();
        Game game = new Game();
        Board board = game.getBoard();
        logger.config(board.toString());
    }
}
