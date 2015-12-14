package com.project.app.initializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer {

    private static final String CONFIG_LOCATION = "com.project.app.config";
    private static final String MAPPING_URL = "/";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        // Create the 'root' Spring application context
        WebApplicationContext context = getContext();

        // Manage the lifecycle of the root application context
        servletContext.addListener(new ContextLoaderListener(context));

        // Register and map the dispatcher servlet
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet",
                new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping(MAPPING_URL);

    }

    private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(CONFIG_LOCATION);
        // context.register(WebMvcConfig.class);

        return context;
    }

}