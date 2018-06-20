package urlcli;

public class Main {
    public static void main(String[] args) {
        String[] str = {"http://www.naver.com"
                        , "http://www.google.com/"};
        new CliRunner().run(str);

    }
}
