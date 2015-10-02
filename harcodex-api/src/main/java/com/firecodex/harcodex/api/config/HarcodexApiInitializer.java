package com.firecodex.harcodex.api.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class HarcodexApiInitializer implements WebApplicationInitializer{

	public void onStartup(ServletContext container) throws ServletException {
		AnnotationConfigWebApplicationContext rootContext =new AnnotationConfigWebApplicationContext();
		rootContext.register(HarcodexApiConfig.class);
		container.addListener(new ContextLoaderListener(rootContext));
		
		Dynamic dispatcher = container.addServlet("harcodex-api", new DispatcherServlet(rootContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		dispatcher.setMultipartConfig(new MultipartConfigElement(System.getProperty("java.io.tmpdir"), 25 * 1024 * 1024, 125 * 1024 * 1024, 1 * 1024 * 1024));
	}

}
