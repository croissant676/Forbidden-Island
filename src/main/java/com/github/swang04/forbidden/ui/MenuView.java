package com.github.swang04.forbidden.ui;

import dev.kason.forbidden.logging.Log;
import dev.kason.forbidden.ui.View;
import org.jetbrains.annotations.NotNull;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

public class MenuView extends View {

    private static final MenuView view = new MenuView();
    private BufferedImage bufferedImage;

    private MenuView() {
        super("Menu");
    }

    @SuppressWarnings("UnusedReturnValue")
    public static MenuView init() {
        return view;
    }

    @Override
    public @NotNull JComponent getDisplay() {
        JPanel menu = new JPanel();
        menu.setSize(500, 500);
        JLabel title = new JLabel("Forbidden Island");
        menu.add(title);
        JButton play = new JButton("Play");

        menu.add(play);
        JButton howTo = new JButton("How To");
        menu.add(howTo);
        Log.logger().config("Hello wrodl");
        JPanel rules = new JPanel();
        howTo.addActionListener((event) -> {
            System.out.println(event.getActionCommand());
            showInstructions();
        });

        play.addActionListener((event) -> {
            System.out.println("Something may happen ");
            System.out.println("Same window");
            JFrame board;
        });

//        try {
//            BufferedImage myPicture = ImageIO.read(new File(""));
//            picLabel = new JLabel(new ImageIcon(myPicture));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return menu;
    }

    public void showInstructions() {
        JFrame frame = new JFrame();
        JDialog dialog = new JDialog(frame, "Instructions", true);
        dialog.setLayout(new FlowLayout());
        JTextArea textArea = new JTextArea();
        String instructions = """
                All files are created in the same directory that the jar file is run from
                The file called BingoWinners.txt contains all of the bingo winners, and what day and round they won on
                The file called BingoBalls.txt contains all the balls, and in what order, day, and round they should be drawn on
                The file called BingoCards.pdf contains all the blank bingo cards with their corresponding ids""";
        textArea.setText(instructions);
        textArea.setEditable(false);
        dialog.add(textArea);
        dialog.setSize(300, 300);
        dialog.setVisible(true);
    }
}