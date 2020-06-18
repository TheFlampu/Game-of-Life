package life;

import javax.swing.*;
import java.awt.*;

public class GameOfLife extends JFrame {
    private static JLabel generationLabel = new JLabel();
    private static JLabel aliveLabel = new JLabel();
    private static JPanel panel = new JPanel(new GridLayout(20,20, -1, -1));
    private static JToggleButton pauseButton = new JToggleButton();
    private static JButton resetButton = new JButton();

    public GameOfLife() {
        super("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(800, 600);
        setVisible(true);

        generationLabel.setName("GenerationLabel");
        aliveLabel.setName("AliveLabel");
        pauseButton.setName("PlayToggleButton");
        resetButton.setName("ResetButton");

        generationLabel.setText("Generation #0");
        aliveLabel.setText("Alive: 0");
        pauseButton.setText("P");
        resetButton.setText("R");

        generationLabel.setBounds(0,50, 150,50);
        aliveLabel.setBounds(0,150, 150,50);
        pauseButton.setBounds(0,0,50,50);
        resetButton.setBounds(100,0,50,50);

        add(generationLabel);
        add(aliveLabel);
        add(pauseButton);
        add(resetButton);

        pauseButton.addActionListener(e -> {
            if(Main.isPause()) {
                Main.setPause(false);
            } else {
                Main.setPause(true);
                Main main = new Main();
                main.start();
            }
        });

        resetButton.addActionListener(e -> Main.firstGeneration());
    }

    public void draw(String[][] matrix, int alive, int generation) {
        remove(panel);
        generationLabel.setText("Generation #" + generation);
        aliveLabel.setText("Alive: " + alive);

        panel = new JPanel(new GridLayout(Main.getSize(),Main.getSize(), -1, -1));
        panel.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        panel.setBounds(220,0,560,560);

        for (int i = 0; i< Main.getSize(); i++){
            for (int j = 0; j < Main.getSize(); j++) {
                JPanel live = new JPanel();
                if (matrix[i][j].equals("O")) {
                    live.setBackground(Color.DARK_GRAY);
                } else {
                    live.setBackground(Color.WHITE);
                }
                live.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panel.add(live);
            }

        }

        add(panel);
        revalidate();
    }
}