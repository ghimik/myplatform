package com.myplatform.myplatform.server;


import com.myplatform.myplatform.util.CommandLineArgumentsParser;
import org.kohsuke.args4j.CmdLineException;

public final class MyBootApplication {

    private static final CommandLineArgumentsParser parser
            = new CommandLineArgumentsParser();

    public static void run(String[] args) {
        try {
            parser.parse(args);
        } catch (CmdLineException e) {
            System.out.println(e.getLocalizedMessage());
            System.exit(-1);
        }
        MyServer.run(parser.getPort());
    }
}
