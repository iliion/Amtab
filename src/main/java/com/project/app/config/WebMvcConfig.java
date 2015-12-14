package com.project.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "com.project.app" })
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment env;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("hello");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/bootstrap").setViewName("bootstrap");
    }

    @Bean
    public ApplicationContextProvider applicationContextProvider() {
        return new ApplicationContextProvider();
    }

    /**
     * Start Thymeleaf specific configuration
     **/

    // ServletContextTemplateResolver: It resolves templates with provided prefix and suffix and
    // other settings.
    @Bean(name = "templateResolver")
    public ServletContextTemplateResolver getTemplateResolver() {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/views/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        return templateResolver;
    }

    // SpringTemplateEngine: It processes templates. We need to assign
    // ServletContextTemplateResolver instance to it. Bean name must be templateEngine.
    @Bean(name = "templateEngine")
    public SpringTemplateEngine getTemplateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(getTemplateResolver());
        return templateEngine;
    }

    // ThymeleafViewResolver: It runs after controller ends its execution. It receives the view name
    // to be processed.
    @Bean(name = "viewResolver")
    public ThymeleafViewResolver getViewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(getTemplateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }

    /**
     * End Thymeleaf specific configuration
     **/

}