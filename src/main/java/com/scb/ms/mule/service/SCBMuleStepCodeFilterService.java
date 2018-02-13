//package com.scb.ms.mule.service;
//
//import org.apache.log4j.Logger;
//import org.mule.api.MuleEventContext;
//import org.mule.api.MuleMessage;
//import org.mule.api.lifecycle.Callable;
//import org.mule.module.json.JsonData;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.scb.ms.communication.SCBCommObj;
//import com.scb.ms.core.util.SCBObjectMapper;
//
//public class SCBMuleStepCodeFilterService implements Callable {
//
//	private Logger logger = Logger.getLogger(this.getClass());
//
//	@Autowired
//	SCBObjectMapper mapper;
//	
//	private static final String NULL_PAYLOAD = "{NullPayload}";
//	
//	@Override
//	public Object onCall(MuleEventContext eventContext) {
//		MuleMessage message = eventContext.getMessage();
//		SCBCommObj commObj = new SCBCommObj();
//
//		try {
//			Object src = message.getPayload();
//			if (src instanceof org.mule.module.json.JsonData){
//				JsonData jsonData = (JsonData) src;
//				commObj = (SCBCommObj)mapper.readerFor(SCBCommObj.class).readValue(jsonData.toString());	
//			} else if (src instanceof SCBCommObj) {
//				commObj = (SCBCommObj) src;
//			} else if (src instanceof String) {
//				if (NULL_PAYLOAD.equals(src)){
//					commObj = new SCBCommObj();
//				} else {
//					commObj = (SCBCommObj)mapper.readerFor(SCBCommObj.class).readValue((String) src);
//				}
//			}
//			
//			if (commObj.getBodySection("otp_cust_reg_section") != null){
//				String stepCode = commObj.getBodySection("otp_cust_reg_section").getStringValue("step_code");
//				logger.info("stepCode:"+stepCode);
//				message.setOutboundProperty("stepCode", stepCode);
//			
//			} else {
//				message.setOutboundProperty("stepCode", "");
//			}
//		} catch (Exception e) {
//			message.setOutboundProperty("stepCode", "ERROR");
//		}
//		return message;
//	}
//}
