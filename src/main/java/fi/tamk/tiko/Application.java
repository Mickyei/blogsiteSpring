
package fi.tamk.tiko;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static String SERVER = "http://localhost:8080/";
    public static String BASEURL = "api/";
    public static String RESOURCE = "blogposts/";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner instructions() {
        return (String... args) -> {
            Log logger = LogFactory.getLog(Application.class);
            logger.info("INSTRUCTIONS");
            logger.info("------------");
            logger.info("GET all blogposts");
            logger.info("    curl -X GET " + SERVER + RESOURCE);
            logger.info("GET one blogpost");
            logger.info("    curl -X GET " + SERVER  + RESOURCE + "1");
            logger.info("DELETE one blogpost");
            logger.info("    curl -X DELETE " + SERVER  + RESOURCE + "1");
            logger.info("POST one blogpost");
            //logger.info("    curl -X POST -H \"Content-type: application/json\" -d \"{\\\"latitude\\\": 10.0, \\\"longitude\\\": 11.0}\" " + SERVER  + RESOURCE);

        };
    }


}