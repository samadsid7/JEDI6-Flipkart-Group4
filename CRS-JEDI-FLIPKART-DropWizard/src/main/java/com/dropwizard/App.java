package com.dropwizard;
import com.flipkart.client.AdminRESTAPIController;
import com.flipkart.client.ProfessorRESTAPIController;
import com.flipkart.client.StudentRESTAPIController;
import com.flipkart.client.UserRESTAPIController;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.flipkart.controller.HelloRestController;
/**
 * Hello world!
 *
 */
// To run the server -
// mvn clean package
// java -jar target/DropWizardExample-1.0-SNAPSHOT.jar server src/main/java/config.yaml
public class App extends Application<Configuration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    @Override
    public void initialize(Bootstrap<Configuration> b) {
    }
    @Override
    public void run(Configuration c, Environment e) throws Exception {
        LOGGER.info("Registering REST resources");
        //  e.jersey().register(new EmployeeRESTController(e.getValidator()));
        e.jersey().register(new HelloRestController());
        e.jersey().register(new AdminRESTAPIController());
        e.jersey().register(new ProfessorRESTAPIController());
        e.jersey().register(new StudentRESTAPIController());
        e.jersey().register(new UserRESTAPIController());
    }
    public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}