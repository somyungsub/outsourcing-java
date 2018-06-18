package swinmenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
    3번 게임실행하기
 */
public class GameStart extends JFrame {

    List<GameObject> gameObjects = new ArrayList<>();   // 20개의 객체를 다룰 자료구조
    long stTime; // 다이얼로그에 출력을 위한 게임시작시점의 시간
    int preNum;         // 클릭된 앞 숫자 체크

    public GameStart() {
        stTime = System.currentTimeMillis();    // 게임시작시 시작시간 저장
        Random random = new Random();

        // 게임실행 프레임 UI설정
        setSize(400, 400);
        setTitle("게임실행");
        setLayout(new BorderLayout());

        // 실행부에서 다룰 판 설정
        JPanel jPanel = new JPanel();
        jPanel.setSize(300, 300);
        jPanel.setLayout(new GridLayout(5, 4)); // 판에 5행 4열의 구조 설정

        // 20개의 데이터 생성 및 중복안되게
        List<String> list = new ArrayList<>();
        while (list.size() < 20) {
            String num = String.valueOf(random.nextInt(20) + 1);
            if (!list.contains(num)) {
                list.add(num);
            }
        }

        // 생성된 숫자를 GameObject에 할당
        for (String s : list) {
            GameObject gameObject = new GameObject(new JLabel(s, SwingConstants.CENTER));
            gameObjects.add(gameObject);
            jPanel.add(gameObject.jLabel);
        }

        // 프레임에 판추가
        add(jPanel);
        setVisible(true);

    }

    // 게임에서 다루는 자료구조
    class GameObject {
        private JLabel jLabel;      // 라벨
        private boolean clickFlg;   // 클릭여부 확인

        public GameObject(JLabel label) {
            this.jLabel = label;    // 외부에서 설정된 라벨 전달
            clickFlg = false;       // 클릭여부 최초 false로 설정
            preNum = 0;
            // 라벨 클릭시 이벤트처리
            this.jLabel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    // 클릭한 숫자 값이랑 이전에 입력된 값 체크
                    if (jLabel.getText().equals(String.valueOf(preNum + 1))) {
                        preNum++;
                        clickFlg = true;    // 클릭여부 확인 true
                        jLabel.setForeground(Color.RED);    // 클릭시 글자색 변경을 통해 클릭여부 확인
                    }

                    // 20개 모두 클릭되었는지 확인하는 작업
                    for (GameObject obj : gameObjects) {
                        // 1개라도 클릭안된게 있으면 함수 종료
                        if (!obj.clickFlg) {
                            return;
                        }
                    }


                    // 20개 모두 클릭하였을 경우 다이얼로그 설정
                    JDialog dialog = new JDialog();
                    dialog.setSize(100, 100);
                    dialog.setLayout(new FlowLayout());
                    JButton jButton = new JButton("확인");

                    // 버튼클릭시 재시작
                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dialog.dispose();   // 다이얼로그창 종료
                            dispose();          // 게임창 종료
                            new GameStart();    // 재 시작
                        }
                    });

                    // 출력 추가
                    dialog.add(new JLabel(String.valueOf((System.currentTimeMillis() - stTime) / 1000) + "초"));
                    dialog.add(jButton);
                    dialog.setVisible(true);
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }

    }
}
