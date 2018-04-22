package convertDigit;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;


public class FileController {

//    LogBack lb = new LogBack();
//    Logger logger = (Logger) LoggerFactory.getLogger(FileController.class);

    //저장소로 만들 폴더생성기

    public void createFoler(String creatPath) {

        File folder = new File(creatPath);

        //없으면

        if (!folder.exists()) {
            folder.mkdirs();

        }

    }

    //해당 경로가 폴더인지 파일인지 파악하기 위한 함수
    public int checkFolder(String folderPath) {

        int folderType = 0;

        //폴더Path
        File foler = new File(folderPath);
        //폴더라면
        if (foler.isDirectory()) {

            folderType = 1;

            //폴더가 아니라 파일이라면

        } else if (foler.isFile()) {

            folderType = 2;

        }

        return folderType;

    }

    //경로로부터 파일리스트 가져오기.
    //폴더일경우 파일리스트를
    //파일일경우 파일이름을 가져온다.

    public ArrayList<String> getFileList(String folderPath) {

        //파일리스트를 담을 변수
        ArrayList<String> resultFileList = new ArrayList<String>();

        //폴더Path
        File foler = new File(folderPath);

        //폴더타입 체크
        int type = checkFolder(folderPath);
        if (type == 1) {//폴더라면

            //하위 파일리스트 가져오기
            File[] fileList = foler.listFiles();

            //반복문을 통해서 fileList 가져오기
            for (File file : fileList) {

                if (file.isFile()) {

                    //String tempPath=tempFile.getParent();
                    String fileName = file.getName();
                    resultFileList.add(fileName);

                }

            }

        } else if (type == 2) {//파일이라면

            //파일을 resultList에 담기
            String fileName = foler.getName();
            resultFileList.add(fileName);

        }

        return resultFileList;
    }

    //변환시킬 파일리스트
//    public void formatFileList(LinkedProperties props, ArrayList<String> fileList, LinkedProperties fileProp) {
//
//        //LogController.setLogMessage("파일변환시작", null);
//
//        String oriPath = fileProp.getProperty("path.folder.goal");
//        String backupPath = fileProp.getProperty("path.folder.backup");
//        String changePath = fileProp.getProperty("path.folder.change");
//        String errorPath = fileProp.getProperty("path.folder.error");
//
//        //폴더타입 체크하는건
//        int type = checkFolder(oriPath);
//        if (type == 1) {
//
//            //파일 반복문
//            for (String fileName : fileList) {
//
//                int fileCode = formatFile(props, oriPath, changePath, fileName);
//
//                File file = new File(oriPath + "/" + fileName);
//                File changeFile = new File(changePath + fileName);
//
//                if (fileCode == 0) {//변환되지않았다면.
//
//                    //파일삭제
//                    changeFile.delete();
//
//                } else if (fileCode == 2) {//오류가 있다면
//                    File errorFile = new File(errorPath + fileName);
//                    changeFile.renameTo(errorFile);
//                    changeFile.delete();
//
//                }
//
//                File backupFile = new File(backupPath + fileName);
//                file.renameTo(backupFile);
//                file.delete();
//            }
//
//        } else if (type == 2) {
//            File file = new File(oriPath);
//            String parentPath = file.getParent();
//            String fileName = file.getName();
//
//            int fileCode = formatFile(props, parentPath, changePath, fileName);
//
//            File changeFile = new File(parentPath + changePath + fileName);
//
//            if (fileCode == 0) {//변환되지않았다면..
//                //변환폴더에서 삭제
//                changeFile.delete();
//            } else if (fileCode == 2) {//오류가 있다면
//                //에러파일로 이동시키고 파일삭제
//                File errorFile = new File(parentPath + errorPath + fileName);
//                changeFile.renameTo(errorFile);
//                changeFile.delete();
//            }
//
//            //원본을 백업폴더로 이동시킨고 원래장소에서는 지운다.
//            File backupFile = new File(parentPath + backupPath + fileName);
//            file.renameTo(backupFile);
//            file.delete();
//        }
//
//        //	LogController.setLogMessage("파일변환 끝", null);
//    }

    //파일 변환하기
    public int formatFile(String filePath, String changePath, String fileName) {

        int resultCode = 0;

        BufferedReader br = null;
        BufferedWriter bw = null;
        Properties props = new Properties();
        String path = "src\\convertDigit\\checkword3.properties";
        fileName = "src\\convertDigit\\「이지폰 서비스」이용 약관.txt";
        changePath = "src\\convertDigit\\「이지폰 서비스」이용 약관_save.txt";
//        fileName = "src\\convertDigit\\Shinhan BNPP 자산운용사 위험등급 변경.txt";
//        changePath = "src\\convertDigit\\Shinhan BNPP 자산운용사 위험등급 변경_save.txt";

        try {
            FileInputStream fis = new FileInputStream(new File(path));
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            props.load(isr);
            // 파일 선언 및 읽어오기
            //System.out.println(filePath+"/"+fileName);

            br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "utf-8"));
            // 파일 생성하기
            //System.out.println(filePath+"/"+fileName);
//            logger.info(filePath + fileName);

            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(changePath), "utf-8"));


            String s = null;

            // 한줄씩 읽어오기
            int index = 0;
            Set<Object> set = props.keySet();   // 프로퍼티 키 값
            while ((s = br.readLine()) != null) {

                System.out.println(s);
                index++;

                // charAt를 이용하여 숫자가 아니면 넘기는 식으로 해서 뽑아 낼 수 있다.
                // 문자열에서 찾아낸 숫자를 변화할 타이밍을 찾기 위한 변수
                // 필요이유 : 한문장에 여러개의 숫자가 있을 수 있기 때문에.

                List<Map<String, String>> chLogList = new ArrayList<Map<String, String>>();
                List<Map<String, String>> digitLogList = new ArrayList<Map<String, String>>();

                // 특수문자 변환
                for (Object obj : set) {
                    String key = obj.toString();
                    if (s.contains(key.trim())) {
                        Map<String, String> map = new HashMap<>();
                        map.put(key, props.get(key).toString());
                        chLogList.add(map);
                    }
                    s = s.replace(key, props.get(key).toString());
                }

                // 숫자 변환
                String number = "";
                String save = s.toString(); // 변환 된 문자열 저장 데이터
                int size = s.length();
                String preStr = "";
                for (int i = 0; i < size; i++) {
                    char ch = s.charAt(i);
                    if (Character.isDigit(ch)) {
                        number += s.charAt(i);
                        continue;
                    } else if (!number.isEmpty()){
                        resultCode = 1;
                        String chgNum = !preStr.isEmpty() && "점".equals(preStr)
                                        ? changeNumber2(number) : changeNumber(number);
                        Map<String, String> map = new HashMap<String, String>();
                        map.put(number, chgNum);
                        digitLogList.add(map);
                        save = save.replaceFirst(number, chgNum);
                        number = "";
                    }
                    preStr = String.valueOf(ch);
                }
                // if 마지막 데이터 숫자 then 변환
                boolean lastNumFlg = Pattern.matches("^[0-9]*$", number);
                if(!number.isEmpty() && lastNumFlg){
                    Map<String, String> map = new HashMap<String, String>();
                    String chgNum = !preStr.isEmpty() && "점".equals(preStr)
                                    ? changeNumber2(number) : changeNumber(number);
                    map.put(number, chgNum);
                    digitLogList.add(map);
                    save = save.replaceFirst(number, chgNum);
                }
                // 특수문자 변환 로그
                if (!chLogList.isEmpty()) {
                    showLog(chLogList, index,"특수문자");
                }
                // 숫자 변환 로그
                if (!digitLogList.isEmpty()) {
                    showLog(digitLogList, index,"숫자");
                }

                //DB연동시작!
//                Connection conn = null;
////                Statement st = null;
////                PreparedStatement pst = null;
////
////                try {
////                    //jar 파일 라이브러리에 추가
////                    Class.forName("com.mysql.jdbc.Driver");
////                    //포트번호, 데이터베이스명, id,pw 입력
////                    conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3308/test01", "root", "jyyoo");
////                    System.out.println("연결성공");
////
////                    st = (Statement) conn.createStatement();
////
////                    //String now = "dateTimeFormat.format(dateTime)";
////                    //String bbb = fileName;
////                    //String ccc = "s";
////                    //String sql = "INSERT INTO  test (time_stamp, name, story) values (?, ?, ?)";
////
////                    String sql = String.format("insert into test01.test(time_stamp, name, story) values(sysdate(), '%s', '%s')", fileName, save);
////                    pst = (PreparedStatement) conn.prepareStatement(sql);
////
////                    //System.out.println(sql);
////                    //ResultSet rs = st.executeQuery(sql);
////                    st.executeUpdate(sql);
////
////                    //while (rs.next()) {
////                    //String sqlRecipeProcess = rs.getString("test");
////                    //}
////
////                    /*
////                     * while (rs.next()) {
////                     * //String sqlRecipeProcess = rs.getString("name");
////                     *  System.out.println(rs.getNString("name"));
////                     *  }
////                     */
////
////                    //rs.close();
////                    st.close();
////                    conn.close();
////
////                } catch (SQLException se1) {
////                    se1.printStackTrace();
////
////                } catch (Exception ex) {
////                    ex.printStackTrace();
////
////                } finally {
////
////                    try {
////                        if (st != null)
////                            st.close();
////
////                    } catch (SQLException se2) {
////
////                    }
////
////                    try {
////                        if (conn != null)
////                            conn.close();
////
////                    } catch (SQLException se) {
////                        se.printStackTrace();
////
////                    }
////
////
////                }

                // 변환된 내용 넣기
                bw.write(save);
                bw.newLine();


            }
            // TODO Auto-generated method stub
            ;
            //	LogController.setLogMessage("변환 파일명 -"+ fileName+ ", 변환여부(0:변환없음, 1:변환성공, 2:에러) - "+resultCode ,null);


        } catch (Exception e) {

            resultCode = 2;
            //	LogController.setLogMessage("변환 파일명 -"+ fileName+ ", 변환여부(0:변환없음, 1:변환성공, 2:에러) - "+resultCode, e);
            e.printStackTrace();

        } finally {
            // 파일스트림 닫기
            if (br != null)

                try {
                    br.close();

                } catch (IOException e) {

                }

            if (bw != null)

                try {
                    bw.close();

                } catch (IOException e) {

                }
        }
        return resultCode;

    }
    // 로그
    private void showLog(List<Map<String, String>> list, int lineIdx, String caseStr) {
        for (Map<String, String> map: list) {
            StringBuilder sb = new StringBuilder();
            for (String key : map.keySet()) {
                sb.append("[").append(caseStr).append(" 변환 Line Number : ").append(lineIdx).append("] ")
                        .append("(AS-IS) : ").append(key).append(" (TO-BE) : ").append(map.get(key));
            }
//            logger.debug(sb.toString());
        }
    }
    private String keys() {
        // TODO Auto-generated method stub
        return null;
    }


    private String dateTimeFormat() {
        // TODO Auto-generated method stub
        return null;
    }
	
	/*private String LogController() {
		// TODO Auto-generated method stub
		return null;
		}
	*/
    // 숫자를 한글로 바꾸기 위한 메서드2 (소수점)
    public static String changeNumber2(String number) {
        StringBuilder sb = new StringBuilder();
        String[] kr1 = {"영", "일", "이", "삼", "사", "오", "육", "칠", "팔", "구"};
        int size = number.length(); // 숫자 길이
        for (int i = 0; i < size; i++) {
            int s = Integer.parseInt(String.valueOf(number.charAt(i)));
            sb.append(kr1[s]);
        }
        return sb.toString();
    }

    //숫자를 한글로 바꾸기 위한 메소드
    public String changeNumber(String number) {
        StringBuilder sb = new StringBuilder();

        String[] kr1 = {"", "이", "삼", "사", "오", "육", "칠", "팔", "구"};
        String[] kr2 = {"", "만", "억", "조", "경"};
        String[] kr3 = {"천","","십","백"};

        int size = number.length(); // 숫자 길이

        // 소수점 앞자리 0인 경우 예외처리
        if (size == 1 && "0".equals(number)) {
            return "영";
        }

        // 0으로 시작하는 경우 or 단위기준 넘는 경우
        if (size > 0 && number.charAt(0) == '0' || number.length() > (kr2.length * 4)) {
            return changeNumber2(number);
        }

        for (int i = 0; i < size; i++) {

            // 0 인경우  -> 문자열 불필요
            if (Integer.parseInt(String.valueOf(number.charAt(i))) <= 0) {
                continue;
            }

            int reminder = (size - i) % 4;  // kr3 기준
            int mock = (size - i) / 4;      // kr2 기준
            int idx = Integer.parseInt(String.valueOf(number.charAt(i))) - 1; // kr1 기준
            /*
                a = 단위 앞의 숫자 (ex 이, 삼, 사 ... 1인 경우 x)
                b = 단위 기준      (ex ~만, ~억, ~천 등등)
             */
            if(kr2.length < mock){
//                logger.debug("에러 : 단위 표현이 불가능한 숫자단위 입니다.");
            }
            String a = kr1[idx];
            String b = reminder == 1 ? kr2[mock] : kr3[reminder];

            /*
                억 이상 단위 기준 : 1인 경우 -> 일 o (ex 일억, 일조, 일경 ...)
                억 미만 단위 기준 : 1인 경우 -> 일 x (ex 만, 천 ...) - 일만, 일천 x
             */
            if (a.isEmpty() && (b.isEmpty() || (size > 8 && reminder == 1))) {
                sb.append("일").append(b);
            } else {
                sb.append(a).append(b);
            }

        }
        return sb.toString();

    }

}