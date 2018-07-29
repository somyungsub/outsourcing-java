package swingjlist;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class JListTest {
    public static void main(String args[]) {
        String labels[] = { "1번 벽지", "2번 벽지", "1번 바닥", "2번 바닥", "1번 문", "2번 문"};
        JFrame frame = new JFrame("Selecting JList");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane();

        JList jlist = new JList(labels);
        JScrollPane scrollPane1 = new JScrollPane(jlist);
        contentPane.add(scrollPane1, BorderLayout.WEST);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.setSize(500, 350);

        JLabel label = new JLabel(new ImageIcon("src/swingjlist/1번벽지.jpg"));
        jPanel.add(label, "Center");

        JPanel panel2 = new JPanel() {
            public boolean isOptimizedDrawingEnabled() {
                return false;
            }
        };
        LayoutManager overlay = new OverlayLayout(panel2);
        panel2.setLayout(overlay);


        JLabel label2 = new JLabel(new ImageIcon("src/swingjlist/1번벽지.jpg"));
        JLabel label3 = new JLabel(new ImageIcon("src/swingjlist/1번문.jpg"));
        panel2.add(label3);
        panel2.add(label2);

        jPanel.add(panel2, "East");

        JLabel label4 = new JLabel(new ImageIcon("src/swingjlist/1번바닥.jpg"));
        jPanel.add(label4, "South");

        contentPane.add(jPanel, BorderLayout.CENTER);


        ListSelectionListener listSelectionListener = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                if (!listSelectionEvent.getValueIsAdjusting()) {
                    JList list = (JList) listSelectionEvent.getSource();
                    switch (list.getSelectedValue().toString()) {
                        case "1번 벽지":
                            label.setIcon(new ImageIcon("src/swingjlist/1번벽지.jpg"));
                            label2.setIcon(new ImageIcon("src/swingjlist/1번벽지.jpg"));
                            break;
                        case "2번 벽지":
                            label.setIcon(new ImageIcon("src/swingjlist/2번벽지.jpg"));
                            label2.setIcon(new ImageIcon("src/swingjlist/2번벽지.jpg"));
                            break;
                        case "1번 바닥":
                            label4.setIcon(new ImageIcon("src/swingjlist/1번바닥.jpg"));
                            break;
                        case "2번 바닥":
                            label4.setIcon(new ImageIcon("src/swingjlist/2번바닥.jpg"));
                            break;
                        case "1번 문":
                            label3.setIcon(new ImageIcon("src/swingjlist/1번문.jpg"));
                            break;
                        case "2번 문":
                            label3.setIcon(new ImageIcon("src/swingjlist/2번문.jpg"));
                            break;

                    }
                }
            }
        };
        jlist.addListSelectionListener(listSelectionListener);

        frame.setSize(800, 450);
        frame.setVisible(true);
    }
}
