package tester;

import game.Game;
import game.board.Board;
import ui.InterfaceManager;
import utils.LogHandler;

import java.util.logging.Logger;

public class Runner {
    public static void main(String[] args) {
        System.setProperty(LogHandler.getConfigContext(), "true");
        // STOPSHIP: 10/15/2021 Remove during production
        Logger logger = LogHandler.getLogger();
        InterfaceManager.initialize();
        Game game = new Game(1);
        Board board = game.getBoard();
        logger.config(board.toString());
    }
}
