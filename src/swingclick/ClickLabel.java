package swingclick;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClickLabel extends JFrame {

    // 실행
    public static void main(String[] args) {
        new ClickLabel();
    }

    private JLabel cLabel;
    public ClickLabel() {

        setTitle("클릭응용프로그래밍");      // 제목설정
        setVisible(true);                    // 보여지기 기본 설정
        setSize(500, 500);  // 창 크기 설정
        setLayout(null);                    // 레이아웃 설정 -> null
        cLabel = new JLabel("C");    // C가 쓰이는 레이블 생성 및 텍스트 설정
        cLabel.setBounds(100,100,10,10);    // 위치설정

        // 라벨을 컨텐트팬에 추가
        add(cLabel);

        // 마우스 이벤트 등록
        addMouseListener(new MouseListener() {

            // 마우스 클릭시 이벤트 처리
            @Override
            public void mouseClicked(MouseEvent e) {
                // 클릭위치 와 C레이블 위치 비교 후 같으면 랜덤으로 다른 곳으로 위치 설정
                if(cLabel.getX() + 5 < e.getX() && cLabel.getX() + 20 > e.getX()
                        && cLabel.getY() + 28 < e.getY() && cLabel.getY() + 45 > e.getY()){
//                    cLabel.setLocation((int)(Math.random() * 450),(int)(Math.random() * 450));
                    int x = (int) (Math.random() * (getSize().width - 25));
                    int y = (int)(Math.random() * (getSize().height - 40)) ;
                    cLabel.setLocation(x, y);
                }
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

        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension di = e.getComponent().getSize();
//                jp.setPreferredSize( di );
                setPreferredSize(di);
                cLabel.setLocation((int)(Math.random() * (getSize().width-50)),(int)(Math.random() * (getSize().height-50)));
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });

    }

}

