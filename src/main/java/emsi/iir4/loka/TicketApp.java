package emsi.iir4.loka;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class TicketApp {


    public static void main(String[] args) {
     SpringApplication.run(TicketApp.class,args);
    }

    
}
