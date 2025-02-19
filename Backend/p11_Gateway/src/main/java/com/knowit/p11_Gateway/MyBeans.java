package com.knowit.p11_Gateway;
import java.util.Arrays;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class MyBeans {
		//http://localhost:8080/api1/welcome   --  localhost:8081
		 //http://localhost:8080/api1/welcome   -- localhost:8082
		
		@Bean
		public RouteLocator customRouterLocator(RouteLocatorBuilder builder) {
			return builder.routes() 
					.route("REGISTRATIONAPI", r -> r.path("/auth/**")
						 .uri("lb://REGISTRATIONAPI"))
					.route("P11CRUD", r -> r.path("/crud/**")
						 .uri("lb://P11CRUD"))
						 //.uri("http://localhost:8110"))
						 				
					.route("TRANSACTION", r -> r.path("/TenantProperty/**")
						 .uri("lb://TRANSACTION"))
					
					.build();
		}
		
		@Bean
	    public CorsWebFilter corsWebFilter() {
			System.out.println("In CorsWebFilter");
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration config = new CorsConfiguration();
	         
	       config.setAllowCredentials(true);
	        config.setAllowedOrigins(Arrays.asList("http://localhost:3011")); // Frontend URL
	        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE","OPTIONS"));
	        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type","Accept"));
	        //config.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept, Authorization"));
	        
	       source.registerCorsConfiguration("/**", config);
	        return new CorsWebFilter(source);
	        
//	        config.addAllowedOriginPattern("http://localhost:*");  
//	       // config.addAllowedOrigin("*"); // Allow all origins
//	        config.addAllowedMethod("*"); // Allow all methods (GET, POST, PUT, DELETE, etc.)
//	        config.addAllowedHeader("*"); // Allow all headers
//	        config.setAllowCredentials(true); // Allow credentials (cookies, authentication, etc.)
//
//	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	        source.registerCorsConfiguration("/**", config); // Apply the CORS config to all paths
//
//	        return new CorsWebFilter(source);
	    }
}
