package urlcli;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;



public class CliTest {

    private static final Options opts = new Options();


    public static void main(String[] args) {

        CommandLineParser clp = new PosixParser();

        CommandLine cline = null;


        try{
            opts.addOption("p", "print", true, "입력받은 인자를 프린트합니다.");

            //첫번째 p 옵션 : -p aaa  (aaa 를 출력 하는 것)
            //두번째 p 옵션 : --print aaa (aaa 를 출력 하는 것)
            //세번째 true,false : -p 또는 --print  다음에 프린트 할 argument 를 받는다.

            opts.addOption("h", "help", false, "도움말을 출력합니다");
            cline = clp.parse(opts, args);

        }catch (ParseException e) {
            System.out.println("error");
            usage();
            //에러 발생 하였으므로 사용법을 알려줌
            return;
        }

        List list = cline.getArgList();

        for(int i=0;i<list.size();i++){
            //argument 로 무엇을 받았는지 확인
            System.out.println("list::"+list.get(i));
        }


        if (cline.hasOption("p")) {
            System.out.println(cline.getOptionValue("p"));
            //-p 나  --print 옵션이 들어오면 찍히는 곳

        }


        if (cline.hasOption("h")) {
            //-h 나 --help 옵션이 들어오면 실행 되는 곳
            usage();
        }
    }

    private static void usage() {
        HelpFormatter hf = new HelpFormatter();
        String runProgram = "java "+CliTest.class.getName() + " [options]";
        hf.printHelp(runProgram, opts);
    }



}