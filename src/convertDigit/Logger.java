package convertDigit;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;

import java.io.IOException;

public class Logger {
    static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Logger.class);
    public static void main(String[] args) throws IOException {

        String str = "aaaabbbbcccc, ajdiasjdaisg";
        str = str.replace("a", "A");
        System.out.println("str = " + str);

        String pattern = "[%d{yyyy-MM-dd HH:mm:ss}] %-5p [%l] - %m%n";
        String datePattern = ".yyyy-MM-dd";
        PatternLayout layout = new PatternLayout(pattern);
        DailyRollingFileAppender appender = new DailyRollingFileAppender(layout, "log.txt", datePattern);
        logger.addAppender(appender);
        logger.setLevel(Level.DEBUG);
        logger.debug("aaa");




    }
}
