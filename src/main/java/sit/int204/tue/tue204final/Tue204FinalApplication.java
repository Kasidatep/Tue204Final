package sit.int204.tue.tue204final;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import sit.int204.tue.tue204final.properties.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class Tue204FinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(Tue204FinalApplication.class, args);
    }

}
