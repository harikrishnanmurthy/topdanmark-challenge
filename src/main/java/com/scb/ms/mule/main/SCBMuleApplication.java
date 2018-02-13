package com.scb.ms.mule.main;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.mule.api.MuleContext;
import org.mule.config.spring.SpringXmlConfigurationBuilder;
import org.mule.context.DefaultMuleContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.StaticApplicationContext;

import com.scb.ms.mule.config.SCBMuleConfigResources;

@SpringBootApplication
@ComponentScan(basePackages = {"com.scb.ms"})
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class, DispatcherServletAutoConfiguration.class,
		WebMvcAutoConfiguration.class, EmbeddedServletContainerAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class })
public class SCBMuleApplication implements CommandLineRunner {

		private static final Logger log = Logger.getLogger(SCBMuleApplication.class);
		
		@Autowired
		SCBMuleConfigResources resources;

		@Autowired
		private ApplicationContext context;

		@Override
		public void run(String... args) throws Exception {
			DefaultMuleContextFactory muleContextFactory = new DefaultMuleContextFactory();
			SpringXmlConfigurationBuilder configBuilder = null;

			if (resources.getResources()==null){
				throw new Exception("Failed to start SCBMuleApplication due to missing [config.resources] in the YAML file...");
			} 

			String resourcesStr = StringUtils.join(resources.getResources(),",");
			log.info("Loaded Mule Application Resources from config.resources::"+resources.getResources());
			StaticApplicationContext staticApplicationContext = new StaticApplicationContext(context);
			configBuilder = new SpringXmlConfigurationBuilder(resourcesStr);
			staticApplicationContext.refresh();
			configBuilder.setParentContext(staticApplicationContext);
			MuleContext muleContext = muleContextFactory.createMuleContext(configBuilder);
			muleContext.start();					
		}

		public static void main(String[] args) {
			try {
				log.info("Starting SpringBootApplication...");
				SpringApplication app = new SpringApplication(SCBMuleApplication.class);
				app.setWebEnvironment(false);
				app.run(args);
				log.info("SpringBootApplication has started...");
			} catch (Exception e) {
				log.error("Error Starting SCBMuleApplication...", e);
			}
		}

	}
