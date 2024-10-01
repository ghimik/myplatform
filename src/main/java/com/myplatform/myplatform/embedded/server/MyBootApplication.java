package com.myplatform.myplatform.embedded.server;

import com.myplatform.myplatform.PlatformApplication;
import com.myplatform.myplatform.embedded.util.CommandLineArgumentsParser;
import com.myplatform.myplatform.flyway.FlywayMigration;
import org.kohsuke.args4j.CmdLineException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
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
        FlywayMigration.migrate();

        ApplicationContext context = new AnnotationConfigApplicationContext(PlatformApplication.class);
        MyServer server = context.getBean(MyServer.class);

        server.run(parser.getPort());
    }
}
