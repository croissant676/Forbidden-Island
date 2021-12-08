/*
 * This code is the work of Team StephanieW, Forbidden Island.
 * Please do not use without permission.
 */

package com.github.swang04.forbidden.ui;

import dev.kason.forbidden.ImageStorage;
import dev.kason.forbidden.Log;
import dev.kason.forbidden.ui.View;
import dev.kason.forbidden.ui.ViewManager;
import org.jetbrains.annotations.NotNull;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.util.Random;
import java.util.logging.Logger;

public class MenuView extends View {

    private static final MenuView view = new MenuView();
    private BufferedImage bufferedImage;

    private MenuView() {
        super("Menu");
    }

    @SuppressWarnings("UnusedReturnValue")
    public static MenuView getInstance() {
        return view;
    }

    private final JFrame instructions = new JFrame("Forbidden Island > Instructions");

    @Override
    public @NotNull JComponent getDisplay() {
        if (bufferedImage == null) {
            bufferedImage = ViewManager.getScaledImage(ImageStorage.retrieveImage("regular_background.png"), 500, 300);
        }
        JPanel menu = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bufferedImage, 0, 0, null);
            }
        };
        menu.setLayout(new BorderLayout());
        menu.setSize(500, 300);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Forbidden Island");
        title.setFont(new Font("Trebuchet MS", Font.PLAIN, 40));
        title.setAlignmentX(0.5f);
        topPanel.add(Box.createVerticalStrut(20));
        topPanel.add(title);
        topPanel.setBackground(new Color(0, 0, 0, 0.0f));
        menu.add(topPanel, BorderLayout.NORTH);
        title.setHorizontalAlignment(JLabel.CENTER);
        JPanel panel = new JPanel();
        panel.setAlignmentX(0.5f);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalStrut(45));
        JButton play = new JButton("Play Game!");
        play.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        panel.add(play, BorderLayout.CENTER);
        play.setAlignmentX(0.5f);
        panel.add(Box.createVerticalStrut(15));
        JButton howTo = new JButton("How to play");
        howTo.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        howTo.setAlignmentX(0.5f);
        panel.add(howTo, BorderLayout.SOUTH);
        menu.add(panel);
        JLabel label = new JLabel("Team StephanieW, Period 6.");
        label.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        menu.add(label, BorderLayout.SOUTH);
        panel.setBackground(new Color(0, 0, 0, 0.0f));
        howTo.addActionListener((event) -> {
            Logger logger = Log.logger();
            logger.info("Hello World");
            showInstructions();
        });
        play.addActionListener((event) -> {
            System.out.println("Something may happen ");
            System.out.println("Same window");
            JFrame board;
        });
        ViewManager.getMainFrame().setResizable(false);
        return menu;
    }

    public void showInstructions() {
        JTextArea textArea = new JTextArea();
        String instructions = """
                All files are created in the same directory that the jar file is run from
                The file called BingoWinners.txt contains all of the bingo winners, and what day and round they won on
                The file called BingoBalls.txt contains all the balls, and in what order, day, and round they should be drawn on
                The file called BingoCards.pdf contains all the blank bingo cards with their corresponding ids""";
        textArea.setText(instructions);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setBackground(new Color(0, 0, 0, 0.0f));
        textArea.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        textArea.setForeground(Color.DARK_GRAY);
        textArea.setSize(500, 700);
        wrapper.add(textArea);
        JButton download = new JButton("View Rulebook");
        download.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        download.addActionListener((e) -> {
            try {
                Random rand = new Random();
                if (rand.nextInt(1000) < 1) {
                    Desktop.getDesktop().browse(URI.create("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
                } else {
                    Desktop.getDesktop().browse(URI.create("https://www.fgbradleys.com/rules/rules2/ForbiddenIsland-rules.pdf"));
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        wrapper.add(download);
        this.instructions.setIconImage(ImageStorage.retrieveImage("icon_1.png"));
        this.instructions.add(wrapper);
        this.instructions.setSize(500, 600);
        this.instructions.setVisible(true);
        this.instructions.setResizable(false);
    }
}
