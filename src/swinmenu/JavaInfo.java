package swinmenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
    5번 자바설명글
 */
public class JavaInfo extends JFrame {
    private String[] txtList;
    public JavaInfo() {

        JTabbedPane tabbedPane = new JTabbedPane();
        txtList = new String[7];
        initTxt();  // 텍스트 내용 초기화
        String titles[] = { "1", "2", "3", "4", "5", "6", "7"}; // 여기에 순서대로 탭타이틀 입력하시면 됩니다
        for (int i = 0, n = titles.length; i < n; i++) {
            addTab(tabbedPane, titles[i], i);
        }

        ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
            }
        };
        tabbedPane.addChangeListener(changeListener);
        add(tabbedPane, BorderLayout.CENTER);
        setSize(600, 500);
        setTitle("자바설명");
        setVisible(true);
    }

    private void initTxt() {
        txtList[0] = "운영체제에 독립적<br>" +
                "1. 자바는 운영체제 위에 JVM 이라는 가상 머신을 두어 우리가 작성한<br> 자바 어플리케이션이 JVM 위에서 동작하게 설계<br>" +
                "2. 각각의 운영체제에 맞는 JVM만 셋팅해 두고 어플리케이션은 한 번만 작성하여<br> 각각의 JVM 위에서 동작하게 되는 장점이 있습니다. <br>이렇게 한 번만 작성한 어플리케이션을 어떤 환경에서든 구동시킬 수 있는 것을<br> Write Once, Run Anywhere 라고 표현하기도 합니다.";
        txtList[1] = "객체지향 언어<br>" +
                "1. 객체지향 패러다임의 특징인 캡슐화, 상속, 추상화, 다형성이 적용<br>" +
                "2. 높은 재사용성 및 유지보수성 생산성 등의 장점";
        txtList[2] = "가비지 컬렉션(Garbage Collection)<br>" +
                "자바는 가비지 컬렉터가 프로그램이 더 이상 사용하지 않는<br> 메모리를 찾아 해제시켜 주기 때문에 개발자는 메모리 관리에 신경 쓰지 않아도 되며, <br>메모리 관리 때문에 작성해야 하는 코드가 사라지기 때문에 코드가 간결해 지는 장점";
        txtList[3] = "다양한 API 및 오픈소스<br>" +
                "1. 많은 오픈소스 라이브러리들이 존재하며, 기본적으로 자바 언어에서 제공하는 API들도 풍부<br>" +
                "2. 자바 개발자는 필요한 기능이 있으면 새로 기능을 개발하는 것이 아니라 <br>기존에 개발된 수많은 오픈소스와 API들을 선택할 수 있다는 뜻";
        txtList[4] = "JVM(Java Virtual Machine)<br>" +
                "JVM은 운영체제 위에서 동작하며, 자바에서는 여러가지 운영체제 버전에 맞는 JVM을 제공.<br>" +
                "운영체제 위에 JVM이라는 하나의 중간계층을 두는 이유는 호환성을 높이기 위함.";
        txtList[5] = "1.자바는 작성된 프로그램(.java)을 컴파일하여 나온 결과물인<br> 바이트코드(.class)를 JVM이라는 가상 머신에서 구동시키고, <br>운영체제와의 호환성은 JVM이 담당해주기 때문에 호환성이 보장 <br>" +
                "2.단 각각의 JVM은 운영체제에 종속적이기 때문에<br> 자바 프로그램을 구동시키기 위해서는 운영체제에 맞는 JVM이 설치";
        txtList[6] = "1. 자바는 그림과 같이 컴파일러와 인터프리터 두 가지를 사용하는 언어<br>" +
                "2. JVM 설명에서도 나와 있지만 최초 작성된 소스(.java 확장자) 원본은<br> 컴파일러를 통해 JVM 인터프리터가 해석할 수 있는 바이트코드로 변환되며(.class 확장자)<br> 이러한 바이트코드를 JVM이 해석하여 운영체제가 이해할 수 있는<br> 기계어로 변환하여 실행";
    }
    // 탭에 라벨 및 내용 추가
    private void addTab(JTabbedPane tabbedPane, String label, int i) {
        JPanel panel = new JPanel();
        panel.setSize(500, 500);
        panel.setLayout(new FlowLayout());
        StringBuilder sb = new StringBuilder("<html>");
        for (int j = 0; j < txtList[i].length(); j++) {
            sb.append(txtList[i].charAt(j));
//            if (j != 0 && j % 30 == 0) {
//                sb.append("<br>");
//            }
        }
        sb.append("</html>");
        JLabel jLabel = new JLabel(sb.toString());

        panel.add(jLabel);

        // 이미지 추가 -> 숫자번호로 판단 하여 추가
        if (i == 4 || i == 5 || i == 6) {
            try {
                BufferedImage image = ImageIO.read(new File("src\\swinmenu\\" + (i + 1) + ".png"));   // 이미지경로
                JLabel jLabel2 = new JLabel(new ImageIcon(image));
                jLabel2.setBounds(30, 270, 350, 230);
                panel.add(jLabel2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        tabbedPane.addTab(label, panel);
    }
}