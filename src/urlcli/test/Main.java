package urlcli.test;

import org.apache.commons.cli.*;

public class Main {
    String sourcePath;

    String sourceLabelName;

    String sourceLabelPos;


    String targetPath;

    String targetLabelName;

    String targetLabelPos;


    String coOccurrenceAnalyzerOption;



    public static void main(String[] args) {
        String[] s = {"java -jar Simulator.jar -sourcefile data/AEEEM/EQ.arff -srclabelname class -possrclabel buggy -targetfile data/ReLink/Apache.arff -tgtlabelname isDefective -postgtlabel TRUE -analyzer regression"};
        new Main().run(s);
    }
    void run(String[] args){


        Options options = createOptions();

        if(args.length < 7){


            HelpFormatter formatter = new HelpFormatter();

            formatter.printHelp( "CrossPredictionSimulator", options );

        }


        parseOptions(options, args);

//        loadSourceAndTargetArffs(sourcePath,targetPath);


    }

    void parseOptions(Options options,String[] args){

        CommandLineParser parser = new BasicParser();

        try {

            CommandLine cmd = parser.parse(options, args);


            sourcePath = cmd.getOptionValue("sourcefile");

            sourceLabelName = cmd.getOptionValue("srclabelname");

            sourceLabelPos = cmd.getOptionValue("possrclabel");


            targetPath = cmd.getOptionValue("targetfile");

            targetLabelName = cmd.getOptionValue("tgtlabelname");

            targetLabelPos = cmd.getOptionValue("postgtlabel");


            coOccurrenceAnalyzerOption = cmd.getOptionValue("analyzer");


        } catch (ParseException e) {

            e.printStackTrace();

        }

    }


    Options createOptions(){


        Options options = new Options();



        options.addOption("sourcefile", true, "Source arff file path");

        options.addOption("srclabelname", true, "Source label name");

        options.addOption("possrclabel", true, "Positive label of source");


        options.addOption("targetfile", true, "Target arff file path");

        options.addOption("tgtlabelname", true, "Target label name");

        options.addOption("postgtlabel", true, "Positive label of target");


        options.addOption("analyzer", true, "Select Co-occurrence analyzer");


        return options;

    }




}
