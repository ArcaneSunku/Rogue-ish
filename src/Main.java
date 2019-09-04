import arcane.Game;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        Canvas canvas = new Canvas();
        JFrame frame = new JFrame("Launcher");
        Dimension dimension = new Dimension(250, 250);

        JButton play_button = new JButton("Play");
        JButton exit_button = new JButton("Exit");

        String[] resolutions = {
                "640, 380",
                "800, 450",
                "1280, 720"
        };

        int w = 75, h = 25;

        JComboBox<String> resolution_list = new JComboBox<>(resolutions);

        resolution_list.setSelectedIndex(0);

        play_button.addActionListener(e -> {
            int width = 0, height = 0, scale = 0;

            switch(resolution_list.getSelectedIndex()) {
                case 0:
                    width = 640;
                    height = 380;
                    scale = 2;
                    break;
                case 1:
                    width = 800;
                    height = 450;
                    scale = 2;
                    break;
                case 2:
                    width = 1280;
                    height = 720;
                    scale = 3;
                    break;
                default:
                    System.exit(-2);
                    break;
            }

            new Game("Rogue-ish", width, height, scale).start();

            canvas.setVisible(false);
            frame.dispose();
        });

        exit_button.addActionListener(e -> {
            frame.dispose();
            System.exit(0);
        });

        canvas.setMinimumSize(dimension);
        canvas.setMaximumSize(dimension);
        canvas.setPreferredSize(dimension);

        frame.add(play_button);
        frame.add(exit_button);
//        frame.add(resolution_list);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(canvas, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        play_button.setSize(w, h);
        exit_button.setSize(w, h);
        resolution_list.setSize(100, h);

        play_button.setLocation((canvas.getWidth() - w) / 2, (canvas.getHeight() - h) / 3);
        exit_button.setLocation(play_button.getX(), (canvas.getHeight() - h) / 2);
        resolution_list.setLocation((canvas.getWidth() - 100) / 2, (exit_button.getY() + h) + 10);

    }

}
