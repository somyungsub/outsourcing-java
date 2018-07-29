package swinmenu;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class SwingMenu {

    // 최초 실행
    public static void main(String[] args){
        SwingMenu swingMenuDemo = new SwingMenu();
        swingMenuDemo.showMenuDemo();
    }

    private JFrame mainFrame;       // 메인프레임
    private JTextArea memoArea;     // 메모장
    private JButton openBtn;        // 메모장 불러오기 버튼
    private JButton colorBtn;       // 컬러 선택하기 위한 버튼
    private JFileChooser jfc;       // 파일 선택
    private JPanel controlPanel;    // 1번 판 컨트롤영역
    private JScrollPane scrollPane; // 메모장넣기

    public SwingMenu(){
        prepareGUI();
    }

    /*
        최초 프레임창 UI 설정
     */
    private void prepareGUI(){
        mainFrame = new JFrame("메모장 연습");
        mainFrame.setSize(400,400);
        mainFrame.setLayout(new BorderLayout());

        // 메모장 관련 초기 작업
        start();

        // 메인프레임 종료시 시스템 종료
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.out.println("close!");
                System.exit(0);
            }
        });

        // 1번판 할당
        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2,1));

        // 1번판 영역 추가
        mainFrame.add(controlPanel);
        mainFrame.setVisible(true);
    }

    // 최초창 프레임 실행부
    private void showMenuDemo(){
        // 메뉴바 생성
        final JMenuBar menuBar = new JMenuBar();

        // 메뉴 생성
        JMenu oneMenu = new JMenu("1번메뉴");
        JMenu twoMenu = new JMenu("2번메뉴");

        // 메뉴 하위아이템 생성
        // setActionCommand를 통해 이벤트 처리시 객체 확인을 위한 작업
        JMenuItem memoItem = new JMenuItem("메모장");
        memoItem.setActionCommand("memo");

        JMenuItem openMenuItem = new JMenuItem("1-2");
        openMenuItem.setActionCommand("1-2");

        JMenuItem saveMenuItem = new JMenuItem("1-3");
        saveMenuItem.setActionCommand("1-3");

        JMenuItem gameItem = new JMenuItem("게임시작");
        gameItem.setActionCommand("game");

        JMenuItem calcItem = new JMenuItem("계산기");
        calcItem.setActionCommand("calc");

        JMenuItem javaItem = new JMenuItem("자바란");
        javaItem.setActionCommand("java");

        // 통합 공통 리스너 등록
        MenuItemListener menuItemListener = new MenuItemListener();
        memoItem.addActionListener(menuItemListener);
        openMenuItem.addActionListener(menuItemListener);
        saveMenuItem.addActionListener(menuItemListener);
        gameItem.addActionListener(menuItemListener);
        calcItem.addActionListener(menuItemListener);
        javaItem.addActionListener(menuItemListener);

        // 1번메뉴에 하위 아이템 추가 (메모장-2번문제, 임의1-2, 임의1-3)
        oneMenu.add(memoItem);
        oneMenu.add(openMenuItem);
        oneMenu.add(saveMenuItem);

        // 2번메뉴에 하위 아이템 추가 (게임시작-3번문제, 계산기-4번문제, 자바설명-5번문제)
        twoMenu.add(gameItem);
        twoMenu.add(calcItem);
        twoMenu.add(javaItem);

        // 메뉴바에 생성된 1번, 2번메뉴 추가
        menuBar.add(oneMenu);
        menuBar.add(twoMenu);

        // 최초메인프레임에 메뉴바 추가
        mainFrame.setJMenuBar(menuBar);
        mainFrame.setVisible(true);
    }

    /*
        메뉴바 관련 이벤트처리 - 통합리스너 처리
        해당객체를 확인 위에서 메뉴 설정시 setActionCommand를 통해 설정된
        내용을 활용 ( e.getActionCommand() )
     */
    class MenuItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            if ("memo".equals(e.getActionCommand())) {
                controlPanel.removeAll();   // 1번메뉴 컨트롤 판에 추가된 모든 컨포넌트 삭제
                controlPanel.repaint();     // 다시 그리기
                memoArea.setText("");       // 메모장 초기화
                controlPanel.add(scrollPane); // 메모장 할당

                // 메모장 버튼부 판
                JPanel panel = new JPanel();
                panel.setLayout(new FlowLayout(FlowLayout.CENTER));
                panel.add(openBtn);     // 메모장열기 버튼 추가
                panel.add(colorBtn);    // 글자색바꾸기 버튼 추가
                controlPanel.add(panel);
            } else if ("1-2".equals(e.getActionCommand())) {
                System.out.println("1번메뉴의 2번째 할 이벤트처리");    // 임의 처리2
            } else if ("1-3".equals(e.getActionCommand())) {
                System.out.println("1번메뉴의 3번째 할 이벤트처리");    // 임의 처리3
            } else if ("game".equals(e.getActionCommand())) {
                new GameStart();    // 3번문제 게임실행하기
            } else if ("calc".equals(e.getActionCommand())) {
                new Calculation();  // 4번문제 계산기
            } else if ("java".equals(e.getActionCommand())) {
                new JavaInfo();     // 5번문제 자바정보출력
            }
            mainFrame.setVisible(true);
        }
    }


    // 메모장 관련 할당
    public void start(){
        memoArea = new JTextArea("",10,10); // 메모장 할당
        scrollPane = new JScrollPane(memoArea);
        openBtn = new JButton("열기"); // 메모장불러오기
        colorBtn = new JButton("글자색");  // 글자색 바꾸기
        jfc = new JFileChooser();

        // 글자색 바꾸기 버튼 이벤트 처리
        colorBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 컬러 설정하기 위한 JColorChooser 다이얼로그 보여주기
                Color newColor = JColorChooser.showDialog(
                        controlPanel,
                        "Choose Color",
                        controlPanel.getBackground());
                if(newColor != null){
                    memoArea.setForeground(newColor);   // 메모장 글자색 바꾸기
                }
            }
        });

        // 메모장 파일 불러오기 이벤트 처리
        openBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jfc.showOpenDialog(controlPanel) == JFileChooser.APPROVE_OPTION) {
                    // showopendialog 열기 창을 열고 확인 버튼을 눌렀는지 확인

                    // 읽은 파일 쓰기
                    File file = jfc.getSelectedFile();      // 파일경로를 통해 File 객체 생성
                    StringBuilder sb = new StringBuilder(); // 데이터 쓰기 위한 클래스
                    try {
                        FileInputStream fis = new FileInputStream(file);    // 파일읽기위한 스트림
                        BufferedReader br = new BufferedReader(new InputStreamReader(fis, "utf-8"));    // 파일읽기 위한 보조스트림
                        String str = null; // 데이터쓰기

                        // 데이터가 null 이 아닐 때까지 읽어들이기
                        while ((str = br.readLine()) != null) {
                            sb.append(str).append(System.lineSeparator());
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    // 메모장에 넣기
                    memoArea.setText(sb.toString());
                }
            }
        });

        jfc.setFileFilter(new FileNameExtensionFilter("txt", "txt"));
        // 파일 필터
        jfc.setMultiSelectionEnabled(false);//다중 선택 불가
    }



}