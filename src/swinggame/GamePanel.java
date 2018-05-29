package swinggame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel {

    public GamePanel(){
        JFrame container = new JFrame();
        container.setSize(600, 300);
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLayout(new FlowLayout());
        container.setTitle("숫자맞추기");

        JPanel panel = new JPanel();

        JLabel label1 = new JLabel("0", JLabel.CENTER);
        JLabel label2 = new JLabel("0", JLabel.CENTER);
        JLabel label3 = new JLabel("0",JLabel.CENTER);
        JLabel label4 = new JLabel("시작합니다.",JLabel.CENTER);

        label1.setBackground(new Color(Color.HSBtoRGB(100, 100, 100)));
        label2.setBackground(Color.MAGENTA);
        label3.setBackground(Color.MAGENTA);
        label1.setSize(100,50);
        label2.setSize(100,50);
        label3.setSize(100,50);

//        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
//        flowLayout.addLayoutComponent("label1", label1);
//        flowLayout.addLayoutComponent("label2", label2);
//        flowLayout.addLayoutComponent("label3", label3);

        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
//        panel.add(label4);

        container.add(panel);
        container.setVisible(true);

        int x = (int) (Math.random() * 5);


        panel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) {



                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
}
