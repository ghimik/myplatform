package com.myplatform.myplatform.embedded.server;


import com.myplatform.myplatform.util.CommandLineArgumentsParser;
import org.kohsuke.args4j.CmdLineException;

public final class MyBootApplication {

    private static final CommandLineArgumentsParser parser
            = new CommandLineArgumentsParser();

    private static final MyServer server = new MyServer();

    public static void run(String[] args) {
        try {
            parser.parse(args);
        } catch (CmdLineException e) {
            System.out.println(e.getLocalizedMessage());
            System.exit(-1);
        }
        server.run(parser.getPort());
    }
}
