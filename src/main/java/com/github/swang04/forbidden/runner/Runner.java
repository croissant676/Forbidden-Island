/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.runner;

import com.github.swang04.forbidden.backend.Game;
import com.github.swang04.forbidden.ui.LossView;
import com.github.swang04.forbidden.ui.MenuView;
import com.github.swang04.forbidden.ui.WinView;
import dev.kason.forbidden.logging.Log;

import java.util.logging.Logger;

public class Runner {

    public static final Logger logger = Log.logger();

    public static void main(String[] args) {
        Game game = Game.getGame();
        System.out.println(game);
        MenuView.init();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void initAll() {
        MenuView.init();
        WinView.init();
        LossView.init();
    }
}
