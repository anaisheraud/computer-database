package com.excilys.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@Configuration
@ComponentScan(basePackages = "com.excilys")
public class config extends AbstractContextLoaderInitializer 
{
	 @Override
	 protected WebApplicationContext createRootApplicationContext() 
	 {
		 AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		 rootContext.register(config.class);
		 return rootContext;
	 }
}
