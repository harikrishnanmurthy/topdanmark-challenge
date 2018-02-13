//package com.scb.ms.mule.service;
//
//import org.apache.log4j.Logger;
//import org.mule.api.MuleEventContext;
//import org.mule.api.MuleMessage;
//import org.mule.api.lifecycle.Callable;
//import org.mule.api.transport.PropertyScope;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.scb.core.common.exception.SCBException;
//import com.scb.ms.mule.util.SCBMuleCloudConfigProperties;
//import com.scb.ribbon.client.SCBRegistryService;
//
//public class SCBServiceDiscoveryLookup implements Callable {
//	
//	private static Logger logger = Logger.getLogger(SCBServiceDiscoveryLookup.class);
//	private static final String EUREKA_ENABLED = "eureka.enable";
//	private static final String EUREKA_HOST = "eureka.client.service-url.defaultZone";
//	private static final String MASTERDOC_HOST = "masterdocument.host";
//	private static final String MASTERDOC_PORT = "masterdocument.port";
//	private static final String REGISTRATION_HOST = "registration.host";
//	private static final String REGISTRATION_PORT = "registration.port";
//	private static final String DEALREF_HOST = "dealref.host";
//	private static final String DEALREF_PORT = "dealref.port";
//	private static final String BACKENDPROC_HOST = "backendproc.host";
//	private static final String BACKENDPROC_PORT = "backendproc.port";
//	private static final String ADVICE_HOST = "advice.host";
//	private static final String ADVICE_PORT = "advice.port";
//	private static final String MASTER_INTEREST_HOST = "masterinterest.host";
//	private static final String MASTER_INTEREST_PORT = "masterinterest.port";
//	private static final String MASTER_SETTLEMENT_HOST = "mastersettlement.host";
//	private static final String MASTER_SETTLEMENT_PORT = "mastersettlement.port";
//	private static final String CACHE_HOST = "cache.host";
//	private static final String CACHE_PORT = "cache.port";
//	private static final String MASTER_PAYMENT_HOST = "masterpayment.host";
//	private static final String MASTER_PAYMENT_PORT = "masterpayment.port";
//	private static final String MASTER_FINANCE_HOST = "masterfinance.host";
//	private static final String MASTER_FINANCE_PORT = "masterfinance.port";
//	
//	private static String eurekaHost="";
//	
//	@Autowired
//	SCBRegistryService registryService;
//	
//	@Autowired
//	SCBMuleCloudConfigProperties cloudConfigProperties;
//	
//	@Override
//	public Object onCall(MuleEventContext eventContext) {
//		MuleMessage message = eventContext.getMessage();
//		String eurekaId="";
//		String eurekaServiceId="";
//		String activeEnv="";
//		String fallbackInstance="";
//		boolean eurekaEnable = false;
//		String[] eurekaInsArr= new String[]{};
//		
//		try {
//			activeEnv = message.getProperty("activeEnv", PropertyScope.INVOCATION).toString().toUpperCase(); 
//			eurekaId = message.getProperty("eurekaId", PropertyScope.INVOCATION); 
//			eurekaServiceId = eurekaId+"-"+activeEnv;
//			eurekaEnable = Boolean.parseBoolean(((cloudConfigProperties.get(EUREKA_ENABLED) !=null)? String.valueOf(cloudConfigProperties.get(EUREKA_ENABLED)) : "false")); 
//			eurekaHost = (String) cloudConfigProperties.get(EUREKA_HOST);
//			if (eurekaEnable){
//				logger.info("Discovering from EurekaHost:: "+eurekaHost);
//				fallbackInstance=retrieveMSfromProperty(eurekaId);
//				String eurekaInstance = registryService.getServiceInstance(eurekaServiceId, fallbackInstance);
//				if (eurekaInstance != null && !eurekaInstance.equals("")){
//					eurekaInsArr = eurekaInstance.split(":");
//				} else {
//					eurekaInsArr = fallbackInstance.split(":");
//					logger.info("EurekaEnabled::"+eurekaEnable+", but eurekaId:["+eurekaServiceId+"] seems not registered. Retrieving from propertyFile - [host::"+eurekaInsArr[0]+", port::"+eurekaInsArr[1]+"]");
//				}
//			} else {
//				fallbackInstance=retrieveMSfromProperty(eurekaId);
//				eurekaInsArr = fallbackInstance.split(":");
//				logger.info("EurekaEnabled::"+eurekaEnable+" for eurekaId:["+eurekaServiceId+"], retrieving from propertyFile - [host::"+eurekaInsArr[0]+", port::"+eurekaInsArr[1]+"]");
//			}
//			
//			message.setProperty("msHost",eurekaInsArr[0], PropertyScope.INVOCATION);
//			message.setProperty("msPort",eurekaInsArr[1], PropertyScope.INVOCATION);
//			message.setProperty("eurekaEnable",eurekaEnable, PropertyScope.INVOCATION);
//			
//			
//		} catch (Exception e) {
//			logger.error(e);
//		}
//		
//		return message;
//	}
//	
//	private String retrieveMSfromProperty(String eurekaId) throws SCBException{
//		if (eurekaId.contains("TP-OTP-REGISTRATION-MS")){
//			return cloudConfigProperties.get(REGISTRATION_HOST)+":"+cloudConfigProperties.get(REGISTRATION_PORT);
//		} else if (eurekaId.contains("TP-OTP-DEALREFERENCE-MS")){
//			return cloudConfigProperties.get(DEALREF_HOST)+":"+cloudConfigProperties.get(DEALREF_PORT);
//		} else if (eurekaId.contains("TP-OTP-MASTERDOCUMENT-MS")){
//			return cloudConfigProperties.get(MASTERDOC_HOST)+":"+cloudConfigProperties.get(MASTERDOC_PORT);
//		} else if (eurekaId.contains("TP-OTP-BACKENDPROC-MS")){
//			return cloudConfigProperties.get(BACKENDPROC_HOST)+":"+cloudConfigProperties.get(BACKENDPROC_PORT);
//		} else if (eurekaId.contains("TP-OTP-ADVICE-MS")){
//			return cloudConfigProperties.get(ADVICE_HOST)+":"+cloudConfigProperties.get(ADVICE_PORT);
//		} else if (eurekaId.contains("TP-OTP-REFERENCEDATA-MS")){
//			return cloudConfigProperties.get(CACHE_HOST)+":"+cloudConfigProperties.get(CACHE_PORT);
//		} else if (eurekaId.contains("TP-OTP-MASTERINTEREST-MS")){
//			return cloudConfigProperties.get(MASTER_INTEREST_HOST)+":"+cloudConfigProperties.get(MASTER_INTEREST_PORT);
//		} else if (eurekaId.contains("TP-OTP-MASTERSETTLEMENT-MS")){
//			return cloudConfigProperties.get(MASTER_SETTLEMENT_HOST)+":"+cloudConfigProperties.get(MASTER_SETTLEMENT_PORT);
//		} else if (eurekaId.contains("TP-OTP-MASTERPAYMENT-MS")){
//			return cloudConfigProperties.get(MASTER_PAYMENT_HOST)+":"+cloudConfigProperties.get(MASTER_PAYMENT_PORT);
//		} else if (eurekaId.contains("TP-OTP-MASTERFINANCE-MS")){
//			return cloudConfigProperties.get(MASTER_FINANCE_HOST)+":"+cloudConfigProperties.get(MASTER_FINANCE_PORT);
//		} 
//		else {
//			throw new SCBException("Eureka Service ID ("+eurekaId+") is not yet setup.");
//		}
//	}
//	
//}
//
