package wordfind;

/*
    단어를 담는 객체
 */
public class Word {
    private String engName; // 영어단어
    private String korName; // 영단어 뜻

    /*
        영어단어, 영단어 뜻 초기화
     */
    public Word(String engName, String korName) {
        this.engName = engName;
        this.korName = korName;
    }

    /*
        영어단어 얻기
     */
    public String getEngName() {
        return engName;
    }

    /*
        영단어 뜻 얻기
     */
    public String getKorName() {
        return korName;
    }
}
