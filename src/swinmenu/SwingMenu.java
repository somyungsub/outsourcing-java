package swinmenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SwingMenu {

    // 최초 실행
    public static void main(String[] args){
        SwingMenu swingMenuDemo = new SwingMenu();
        swingMenuDemo.showMenuDemo();
    }

    private JFrame mainFrame;       // 메인프레임
    private JTextArea memoArea;     // 메모장
    private JPanel controlPanel;    // 1번 판 컨트롤영역

    public SwingMenu(){
        prepareGUI();
    }

    /*
        최초 프레임창 UI 설정
     */
    private void prepareGUI(){
        mainFrame = new JFrame("기말시험 연습");
        mainFrame.setSize(400,400);
        mainFrame.setLayout(new BorderLayout());

        memoArea = new JTextArea(); // 메모장 할당

        // 메인프레임 종료시 시스템 종료
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.out.println("close!");
                System.exit(0);
            }
        });

        // 1번판 할당
        controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());

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
        openMenuItem.setActionCommand("Open");

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
            controlPanel.removeAll();   // 1번메뉴 컨트롤 판에 추가된 모든 컨포넌트 삭제
            controlPanel.repaint();     // 다시 그리기
            memoArea.setText("");       // 메모장 초기화

            if ("memo".equals(e.getActionCommand())) {
                controlPanel.add(memoArea); // 메모장 할당
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

    // 3번 게임실행하기
    class GameStart extends JFrame{

        List<GameObject> gameObjects = new ArrayList<>();   // 20개의 객체를 다룰 자료구조
        long stTime; // 다이얼로그에 출력을 위한 게임시작시점의 시간

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

                // 라벨 클릭시 이벤트처리
                this.jLabel.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        clickFlg = true;    // 클릭여부 확인 true
                        jLabel.setForeground(Color.RED);    // 클릭시 글자색 변경을 통해 클릭여부 확인

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
                        dialog.add(new JLabel(String.valueOf(System.currentTimeMillis() - stTime) + "초"));
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

    // 4번 계산기
    class Calculation extends JFrame {
        private JButton[][] buttons;    // 계산기에 쓰이는 버튼 생성
        private JPanel panel;           // 계산기를 다룰 판
        private JPanel panelLabel;      // 결과 및 입력수식 라벨을 다룰 판
        private JLabel resultLabel;     // 결과 값을 표시할 라벨
        private JLabel operLabel;       // 순서대로 입력된 내용 표시를 위한 라벨
        private List<Double> list = new ArrayList<>();  // 데이터를 다룰 자료구조
        private boolean isFlg = false;  // 오퍼레이터 확인용

        // 계산기
        public Calculation() {
            // 프레임 UI 설정
            setSize(400, 400);
            setTitle("계산기");
            setLayout(new GridLayout(2,1));

            // 계산기에 관련된 버튼생성
            buttons = new JButton[][]{
                    { new JButton("Clear"), new JButton("C"), new JButton("<-"), new JButton("/"),}
                    ,{ new JButton("7"), new JButton("8"), new JButton("9"), new JButton("x"),}
                    ,{ new JButton("4"), new JButton("5"), new JButton("6"), new JButton("-"),}
                    ,{ new JButton("1"), new JButton("2"), new JButton("3"), new JButton("+"),}
                    ,{ new JButton("+-"), new JButton("0"), new JButton("."), new JButton("="),}};

            // 계산기에 쓰이는 버튼을 다룰 판 생성 및 추가
            panel = new JPanel();
            panel.setLayout(new GridLayout(5,4,5, 5));  // 5행 4열, 사이간격 : 5
            for (int i = 0; i < buttons.length; i++) {
                for (int j = 0; j < buttons[i].length; j++) {
                    buttons[i][j].setActionCommand(buttons[i][j].getText());
                    buttons[i][j].addActionListener(new CalculationListener());
                    panel.add(buttons[i][j]);
                }
            }

            // 결과 및 입력수식 라벨을 다룰 판
            panelLabel = new JPanel();
            panelLabel.setLayout(new GridLayout(2,1));  // 2행 1열
            operLabel = new JLabel("", SwingConstants.RIGHT);       // 수식입력 라벨
            resultLabel = new JLabel("0", SwingConstants.RIGHT);    // 결과 라벨
            panelLabel.add(operLabel);
            panelLabel.add(resultLabel);

            // 프레임에 판추가
            add(panelLabel);
            add(panel);
            setVisible(true);
        }

        // 계산기이벤트
        class CalculationListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                String clickText = e.getActionCommand();    // 클릭된 액션명령어
                char ch = clickText.charAt(0);  // 입력된 숫자 확인을 위한 데이터 확인용

                // 숫자인경우 or 점인경우
                if (Character.isDigit(ch) || (!resultLabel.getText().contains(".") && ch == '.')) {
                    if (isFlg) {
                        resultLabel.setText("");
                        isFlg = false;
                    }
                    // 결과 라벨의 길이가 1이고, 값이 0이고, 입력된 값이 숫자인경우 -> 라벨의 값 일단 없애기
                    if (resultLabel.getText().length() == 1 && resultLabel.getText().charAt(0) == '0' && Character.isDigit(ch)) {
                        resultLabel.setText("");
                    }

                    // 라벨에 값 입력
                    resultLabel.setText(resultLabel.getText() + ch);
                } else {

                    if (ch == 'C') {    // 현재 입력된 값 지우기
                        resultLabel.setText("");
                        if ("Clear".equals(clickText)) {
                            operLabel.setText("");
                            list.clear();
                        }
                    } else if (ch == '<') { // 1개씩 삭제
                        if (!resultLabel.getText().isEmpty()) {
                            resultLabel.setText(resultLabel.getText().substring(0, resultLabel.getText().length() - 1));
                            if (resultLabel.getText().isEmpty()) {
                                resultLabel.setText("0");
                            }
                        }
                    } else if ("+-".equals(clickText)) {    // +, - 전환
                        if (!resultLabel.getText().isEmpty()) {
                            if (Character.isDigit(resultLabel.getText().charAt(0))) {
                                resultLabel.setText("-" + resultLabel.getText());
                            } else {
                                resultLabel.setText(resultLabel.getText().substring(1));
                            }
                        }
                    } else {    // 그외 -> 연산자인경우
                        isFlg = true;
                        // 현재 입력된 값과 앞에 입력된 수식들을 수식라벨에 제공
                        StringBuilder sb = new StringBuilder();
                        sb.append(operLabel.getText()).append(resultLabel.getText()).append(" ").append(ch).append(" ");
                        operLabel.setText(sb.toString());

                        Double result = null;
                        if (!list.isEmpty()) {

                            // 현재까지 입력된 수식 라벨을 확인한 후 result변수에 결과 값 받기
                            String s[] = operLabel.getText().split(" ");
                            result = calc(list.get(0), Double.parseDouble(resultLabel.getText()), ch == '=' ? String.valueOf(ch): String.valueOf(s[s.length - 3]));
                            if (ch != '=') {
                                list.set(0, result);    // =이 아닌경우 데이터자료 첫번째에 결과 삽입
                            }
                        } else {
                            // 데이터자료에 입력된 결과 추가
                            list.add(Double.parseDouble(resultLabel.getText()));
                        }

                        // 데이터 할당
                        if (result != null) {
                            resultLabel.setText(String.valueOf(result));
                        }
                    }
                }

            }
        }

        // 계산함수
        private Double calc(double d1, double d2, String oper) {
            Double result = null;
            switch (oper) {
                case "+":
                    result = list.get(0) + Double.parseDouble(resultLabel.getText());
                    break;
                case "-":
                    result = list.get(0) - Double.parseDouble(resultLabel.getText());
                    break;
                case "x":
                    result = list.get(0) * Double.parseDouble(resultLabel.getText());
                    break;
                case "/":
                    result = list.get(0) / Double.parseDouble(resultLabel.getText());
                    break;
                case "=":
                    String[] s = operLabel.getText().split(" ");
                    result = calc(list.get(0), Double.parseDouble(resultLabel.getText()), s[s.length - 3]);
                    operLabel.setText("");  // 수식입력라벨 초기화
                    list.clear();       // 데이터부 초기화
                    break;
            }

            return result;
        }
    }

    // 5번 자바설명글
    class JavaInfo extends JFrame {
        public JavaInfo() {
            setSize(400, 400);
            setTitle("자바설명");
            setLayout(new BorderLayout());
            add(new JLabel("자바는 재밌고 즐거운 객체지향프로그래밍 언어입니다.", SwingConstants.CENTER));
            setVisible(true);
        }
    }

}