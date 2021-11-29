/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.runner;

import com.github.swang04.forbidden.backend.board.Board;
import com.github.swang04.forbidden.ui.LossView;
import com.github.swang04.forbidden.ui.MenuView;
import com.github.swang04.forbidden.ui.WinView;

public class Runner {
    public static void main(String[] args) {
        MenuView.init();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void initAll() {
        MenuView.init();
        WinView.init();
        LossView.init();
    }
}
