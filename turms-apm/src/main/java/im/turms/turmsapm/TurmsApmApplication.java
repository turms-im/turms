package im.turms.turmsapm;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class TurmsApmApplication {

    public static void main(String[] args) {
        SpringApplication.run(TurmsApmApplication.class, args);
    }

}
