/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden;

import com.github.swang04.forbidden.backend.Game;
import com.github.swang04.forbidden.ui.LossView;
import com.github.swang04.forbidden.ui.MenuView;
import com.github.swang04.forbidden.ui.WinView;
import dev.kason.forbidden.Log;

import java.util.logging.Logger;

public class Runner {

    public static final Logger logger = Log.logger();

    public static void main(String[] args) {
        Game game = Game.getGame();
        logger.info(game.toString());
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void initAll() {
        MenuView.init();
        WinView.init();
        LossView.init();
    }
}
