package com.example.restApi.cors;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class CorsConfig implements WebMvcConfigurer{
	 public void  addCorsMappings(CorsRegistry registry) {
		 registry.addMapping("/**")
		 .allowedOrigins("http://127.0.0.1:5500/") //specify allowed origin
		 .allowedMethods("GET")  //limit allowed HTTP methods as needed
		 .allowedHeaders("*");   //allow all headers (adjust as 
	 }
	
}
