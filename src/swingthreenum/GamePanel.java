package swingthreenum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel {

    // 실행
    public static void main(String[] args) {
        GamePanel panel = new GamePanel();
    }

    private JLabel[] jLabel;    // 숫자 레이블
    private JLabel labelText;   // 텍스트 레이블

    public GamePanel(){
        /*
            전체 컨텐트팬 설정
         */
        JFrame container = new JFrame();
        container.setSize(600, 300);
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLayout(new FlowLayout());
        container.setTitle("숫자맞추기");

        /*
            panel 설정
             - 상단 : 숫자 3개
             - 하단 : 텍스트 1개
         */
        JPanel panel = new JPanel();    // 상단 패널
        JPanel panel2 = new JPanel();   // 하단패널
        JPanel [] panel1_1 = new JPanel[3]; // 상단 패널에서 숫자레이블 3개를 다룰 패널

        panel.setLayout(new FlowLayout()); // 레이아웃 종류 설정
        panel.setPreferredSize(new Dimension(600,100));     // 상단 크기 설정
        panel2.setPreferredSize(new Dimension(600,130));    // 하단 크기 설정

        /*
            레이블 데이터 설정 및 디자인 설정
         */
        jLabel = new JLabel[3];
        for (int i = 0; i < jLabel.length; i++) {

            /*
                상단 패널에서 각 숫자레이블을 다룰 패널 설정
             */
            panel1_1[i] = new JPanel();
            panel1_1[i].setPreferredSize(new Dimension(150,60));    // 레이아웃 크기 설정
            panel1_1[i].setLayout(new BorderLayout());  // 레이아웃 종류 설정
            panel1_1[i].setBackground(Color.MAGENTA);   // 배경 색

            /*
                레이블 설정
             */
            jLabel[i] = new JLabel("0", JLabel.CENTER);                   // 첫 숫자는 0
            jLabel[i].setSize(new Dimension(150,50));           // 크기 설정
            jLabel[i].setFont(new Font("Serif", Font.BOLD, 40));  // 폰트 설정
            jLabel[i].setForeground(Color.ORANGE);                              // 폰트 색
            panel1_1[i].add(jLabel[i]); // 각 숫자 패널에 숫자 레이블 추가
            panel.add(panel1_1[i]);     // 상단 패널에 각 숫자 패널 추가
        }
        // 텍스트 레이블 설정 및 추가
        labelText = new JLabel("시작합니다.",JLabel.CENTER);
        panel2.add(labelText);

        // 컨탠트팬에 상단, 하단패널 추가
        container.add(panel);
        container.add(panel2);
        container.setVisible(true);

        // 키이벤트 처리
        container.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {

                // 엔터키 입력인 경우
                if(e.getKeyChar() == KeyEvent.VK_ENTER) {

                    // 숫자 레이블 데이터 재 설정 (0~4 랜덤발생)
                    for (int i = 0; i < jLabel.length; i++) {
                        int x = (int) (Math.random() * 5);
                        jLabel[i].setText(String.valueOf(x));
                    }

                    /*
                        if      3개의 숫자가 일치하는 경우
                        else    3개의 숫자가 일치하지 않는 경우
                     */
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
