package org.yesee.hinet_vcpe_for_client.configuration;
import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
 
public class MySitemeshFilter extends ConfigurableSiteMeshFilter {
 
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
    	builder.addDecoratorPath("/","/WEB-INF/decorator/main.jsp").addExcludedPath("/login/*");
//    			
//        builder.addDecoratorPath("/", "/decorators/decorator.html").addExcludedPath("/excluded/*")
//                .addExcludedPath("/resources/*").addExcludedPath("/decorators/*");
    }
}