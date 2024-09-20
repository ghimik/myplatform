package com.myplatform.myplatform.embedded.util;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class CommandLineArgumentsParser {

    private CmdLineParser parser;

    @Option(name = "-p",
            aliases = "--port",
            usage = "Sets a port to listen",
            help = true,
            required = true)
    private Integer port;

    public Integer getPort() {return this.port;}

    public void parse(String[] args) throws CmdLineException {
        parser = new CmdLineParser(this);
        parser.parseArgument(args);
    }

}
