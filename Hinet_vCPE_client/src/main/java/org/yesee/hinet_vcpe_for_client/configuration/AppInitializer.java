package org.yesee.hinet_vcpe_for_client.configuration;

import java.util.EnumSet;
import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.Log4jConfigListener;

public class AppInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext container) throws ServletException {
		container.setInitParameter("log4jConfiguration", "classpath:log4j.properties");
		container.addListener(Log4jConfigListener.class);

		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(PocConfiguration.class);
		ctx.register(PocWebConfiguration.class);
		ctx.setServletContext(container);
		addSitemeshFilterToServletContext(container);
		ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}

	private void addSitemeshFilterToServletContext(ServletContext servletContext) {
	        FilterRegistration.Dynamic sitemesh = servletContext.addFilter("sitemesh", new MySitemeshFilter());
	        EnumSet<DispatcherType> sitemeshDispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
	        sitemesh.addMappingForUrlPatterns(sitemeshDispatcherTypes, true, "/*");
	    }
}