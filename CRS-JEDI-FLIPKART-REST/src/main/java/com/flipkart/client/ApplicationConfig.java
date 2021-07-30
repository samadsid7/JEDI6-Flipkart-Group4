package com.flipkart.client;

import org.glassfish.jersey.server.ResourceConfig;



public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        // All the web servies to be registered Here
     register(AdminRESTAPIController.class);
    // register(studentRESTAPIController.class);
        register(ProfessorRESTAPIController.class);
   // register(studentRESTAPIController.class);
        //register(HelloController.class);

    }

}