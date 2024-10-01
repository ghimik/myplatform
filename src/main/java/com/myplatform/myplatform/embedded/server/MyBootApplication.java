package com.myplatform.myplatform.embedded.server;


import com.myplatform.myplatform.BeanConfig;
import com.myplatform.myplatform.JpaConfig;
import com.myplatform.myplatform.embedded.util.CommandLineArgumentsParser;
import com.myplatform.myplatform.flyway.FlywayMigration;
import com.myplatform.myplatform.repo.UserRepository;
import org.kohsuke.args4j.CmdLineException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
        FlywayMigration.migrate();
        server.run(parser.getPort());
    }
}
