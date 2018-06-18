package swinmenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


/*
    4번 계산기
 */
public class Calculation extends JFrame {
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
