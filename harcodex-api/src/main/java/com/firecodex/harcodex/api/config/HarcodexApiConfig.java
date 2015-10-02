package com.firecodex.harcodex.api.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.mongodb.MongoClient;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.firecodex.harcodex.api.controller", "com.firecodex.harcodex.api.service"})
@EnableTransactionManagement
@PropertySource("classpath:config.properties")
@EnableAsync
public class HarcodexApiConfig {
	private static final String MONGO_DB_KEY = "mongo.db";
	private static final String MONGO_HOST_KEY = "mongo.host";
	private static final String MONGO_PORT_KEY = "mongo.port";
	private static final String MONGO_USERNAME_KEY = "mongo.username";
	private static final String MONGO_PASSWORD_KEY = "mongo.password";
	private static final String MONGO_PROTECTED_KEY = "mongo.protected";
	
//	private static final String PS_URL_KEY = "ps.url";
	
	@Resource
	private Environment env;
	
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
 
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
	
	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {
		if(env.getRequiredProperty(MONGO_PROTECTED_KEY).equalsIgnoreCase("true")){
			return new SimpleMongoDbFactory(
					new MongoClient(env.getRequiredProperty(MONGO_HOST_KEY), Integer.parseInt(env.getRequiredProperty(MONGO_PORT_KEY))), 
					env.getRequiredProperty(MONGO_DB_KEY), 
					new UserCredentials(env.getRequiredProperty(MONGO_USERNAME_KEY), env.getRequiredProperty(MONGO_PASSWORD_KEY))
			);
		}else{
			return new SimpleMongoDbFactory(
					new MongoClient(env.getRequiredProperty(MONGO_HOST_KEY), Integer.parseInt(env.getRequiredProperty(MONGO_PORT_KEY))), 
					env.getRequiredProperty(MONGO_DB_KEY)
			);
		}
	}
	
	@Bean
	public MongoOperations mongoOperations() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return (MongoOperations) mongoTemplate;
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer getConfigurer() throws Exception {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public MultipartResolver multipartResolver(){
	    CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
	    commonsMultipartResolver.setDefaultEncoding("utf-8");
	    commonsMultipartResolver.setMaxUploadSize(50000000);
	    return commonsMultipartResolver;
	}
}

