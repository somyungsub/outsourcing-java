package swinggame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel {

    private JLabel[] jLabel;
    private JLabel labelText;

    public GamePanel(){
        JFrame container = new JFrame();
        container.setSize(600, 300);
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLayout(new FlowLayout());
        container.setTitle("숫자맞추기");

        JPanel panel = new JPanel();
//        panel.setFocusable(true);
//        panel.requestFocus();

        jLabel = new JLabel[3];
        for (int i = 0; i < jLabel.length; i++) {
            jLabel[i] = new JLabel("0", JLabel.CENTER);
            panel.add(jLabel[i]);
        }
        labelText = new JLabel("시작합니다.",JLabel.CENTER);
        panel.add(labelText);
        container.add(panel);
        container.setVisible(true);

        container.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) {
                    for (int i = 0; i < jLabel.length; i++) {
                        int x = (int) (Math.random() * 5);
                        jLabel[i].setText(String.valueOf(x));
                    }

                    if (jLabel[0].getText().equals(jLabel[1].getText())
                            && jLabel[0].getText().equals(jLabel[2].getText())
                            && jLabel[1].getText().equals(jLabel[2].getText())) {
                        labelText.setText("축하합니다!!");
                    } else {
                        labelText.setText("아쉽군요");
                    }

                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }
}
