//package less2020.config;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import less2020.controle.Servlet;
//
//@Configuration
//public class Restbucks extends SpringBootServletInitializer {
//
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(Restbucks.class, ComponentConfiguration.class);
//    }
//
//    @Bean
//    public Servlet dispatcherServlet() {
//        return new Servlet();
//    }
//
//	@Bean
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//    public ServletRegistrationBean dispatcherServletRegistration() {
//        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet());
//        Map<String,String> params = new HashMap<String,String>();
//        params.put("org.atmosphere.servlet","org.springframework.web.servlet.DispatcherServlet");
//        params.put("contextClass","org.springframework.web.context.support.AnnotationConfigWebApplicationContext");
//        params.put("contextConfigLocation","net.org.selector.animals.config.ComponentConfiguration");
//        registration.setInitParameters(params);
//        return registration;
//    }
//
//}
