
package fi.tamk.tiko;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
public class Application {

    public static String SERVER = "206.189.15.232";
    public static String COMMENTRESOURCE = "comments/";
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
            logger.info("    curl -X POST -H \"Content-type: application/json\" -d \"{\\\"comments\\\": [], \\\"points\\\": 0, \\\"title\\\": \\\"Random title\\\"," +
                    "\\\"text\\\": \\\"Hello everyone\\\",\\\"author\\\": \\\"Ernest Hemingway\\\"}\" " + SERVER  + RESOURCE);
            logger.info("GET all comments");
            logger.info("    curl -X GET " + SERVER + COMMENTRESOURCE);
            logger.info("GET one comment");
            logger.info("    curl -X GET " + SERVER + COMMENTRESOURCE + "1");
            logger.info("POST one comment");
            logger.info("    curl -X POST -H \"Content-type: application/json\" -d \"{\\\"amount\\\": 0, \\\"blogpost\\\": 4," +
                    "\\\"comment\\\": \\\"Hello everyone\\\",\\\"username\\\": \\\"Ernest Hemingway\\\"}\" " + SERVER  + COMMENTRESOURCE);
            logger.info("DELETE one comment");
            logger.info("    curl -X DELETE " + SERVER  + COMMENTRESOURCE + "1");
        };
    }

    @Bean
     public FilterRegistrationBean corsFilter() {
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      CorsConfiguration config = new CorsConfiguration();
      config.setAllowCredentials(true);
      config.addAllowedOrigin("http://localhost:3001");
      config.addAllowedHeader("*");
      config.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE"));
     source.registerCorsConfiguration("*//**", config);
     FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
     bean.setOrder(0);
      return bean;
     }

}