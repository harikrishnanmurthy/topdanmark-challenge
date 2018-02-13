package com.scb.ms.mule.config;
//package com.scb.ms.mule.util;
//
//import java.util.Properties;
//
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
//import org.springframework.cloud.config.client.ConfigClientProperties;
//import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.env.CompositePropertySource;
//import org.springframework.core.env.MapPropertySource;
//import org.springframework.core.env.PropertySource;
//import org.springframework.core.env.StandardEnvironment;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SCBMuleCloudConfigProperties extends Properties {
//
//	private static final Logger log = Logger.getLogger(SCBMuleCloudConfigProperties.class);
//	
//	private static final long serialVersionUID = 5688668322414817172L;
//	private ConfigClientProperties configClientProperties;
//	private String env;
//	private String appName;
//	
//	@Autowired
//	private ApplicationContext appContext;
//	
//	private ConfigClientProperties generateClientConfigProperties() {
//		String cloudUriSystemProp = System.getProperty("spring.cloud.config.uri");
//		if (StringUtils.isEmpty(cloudUriSystemProp)){
//			cloudUriSystemProp = appContext.getEnvironment().getProperty("spring.cloud.config.uri");
//		}
//		
//		log.info("spring.cloud.config.uri = " + cloudUriSystemProp);
//		
//		if (cloudUriSystemProp == null){
//			throw new RuntimeException("spring.cloud.config.uri not set");				
//		}
//		
//		if (env == null){
//			throw new RuntimeException("spring.profiles.active not set");
//		}
//		
//		String label = "master";
//		if (StringUtils.isNotEmpty(System.getProperty("spring.cloud.config.label"))) {
//			label = System.getProperty("spring.cloud.config.label");
//		}
//		
//		ConfigClientProperties prop = new ConfigClientProperties(new StandardEnvironment());
//		prop.setUri(cloudUriSystemProp);
//		prop.setLabel(label); //git branch
//		prop.setName(appName); // application name
//		prop.setProfile(env);
//		return prop;
//	}
//	
//	protected boolean initFromCloud() {
//		ConfigServicePropertySourceLocator locator = new ConfigServicePropertySourceLocator(configClientProperties);
//		CompositePropertySource propertySource  = (CompositePropertySource) locator.locate(appContext.getEnvironment());
//		if (propertySource != null){
//			log.info("Loading properties from cloud config server for ["+appName+":"+env+"]....");
//			for (PropertySource<?> mapSource : propertySource.getPropertySources()) {
//				log.info("property source = " + ((MapPropertySource) mapSource).getSource());
//				super.putAll(((MapPropertySource) mapSource).getSource());
//			}
//			
//			if (CollectionUtils.isEmpty(propertySource.getPropertySources())){
//				log.info("unable to load properties from cloud config server. Hint: maybe your selected spring profile is not define in the cloud config stash repository.");
//				return false;
//			} else {
//				log.info("Loaded properties from cloud config server for ["+appName+":"+env+"]....");
//				return true;
//			}
//	
//		} else {
//			log.info("unable to load properties from cloud config server. Hint: maybe your selected spring profile is not define in the cloud config stash repository.");
//			return false;
//		}
//	}
//	
//	@Bean
//	public boolean loadCloudConfig(){
//		if (appContext != null){
//			this.env = System.getProperty("spring.profiles.active");
//			this.appName = appContext.getEnvironment().getProperty("spring.application.name");
//			this.configClientProperties = generateClientConfigProperties();
//			if (!initFromCloud()){
//				initFromClasspath();
//			}
//		}
//		return true;
//	}
//	
//	public void initFromClasspath() {
//		log.info("Loading properties from classpath for ["+appName+":"+env+"]....");
//		log.info("NOTE: you are seeing this message either you are running on your local or the spring active profile is not define in the cloud config stash repository.");
//		YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
//		try {
//			ClassPathResource yamlFile = new ClassPathResource("application-local.yml");
//			if (yamlFile.exists()){
//				yaml.setResources(yamlFile);
//				super.putAll(yaml.getObject());
//				log.info("Loaded properties from application-local.yml for ["+appName+":"+env+"]....");			
//			} else {
//				log.info("unable to locate yaml file. Please make sure that the application-local.yml is there in src/main/resources/ path.");		
//			}
//			
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 
//	}
//}