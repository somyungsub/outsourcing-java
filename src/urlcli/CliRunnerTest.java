package urlcli;

public class CliRunnerTest {
    public static void main(String[] args) {
        String[] str = {"CliRunnerTest -d c:\\basic -u http://www.naver.com"
                        , "CliRunnerTest -d c:\\basic -u http://www.naver.com"};
        new CliRunner().run(str);

    }
}
