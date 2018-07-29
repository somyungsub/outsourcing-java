package swingjlist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

public class OverlaySample {
    public static void main(String args[]) {
        JFrame frame = new JFrame("Overlay Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            public boolean isOptimizedDrawingEnabled() {
                return false;
            }
        };
        LayoutManager overlay = new OverlayLayout(panel);
        panel.setLayout(overlay);

        JButton button = new JButton("Small");
        button.setMaximumSize(new Dimension(25, 25));
        button.setBackground(Color.white);
        button.setAlignmentX(0.5f);
        button.setAlignmentY(0.5f);
        panel.add(button);

        button = new JButton("Medium");
        button.setMaximumSize(new Dimension(50, 50));
        button.setBackground(Color.gray);
        panel.add(button);

        button = new JButton("Large");
        button.setMaximumSize(new Dimension(100, 100));
        button.setBackground(Color.black);
        panel.add(button);

        frame.add(panel, BorderLayout.CENTER);

        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}