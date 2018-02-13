//package com.scb.ms.mule.service;
//
//import org.mule.api.MuleEvent;
//import org.mule.api.MuleException;
//import org.mule.api.MuleMessage;
//import org.mule.api.transport.PropertyScope;
//import org.mule.interceptor.AbstractEnvelopeInterceptor;
//import org.mule.management.stats.ProcessingTime;
//
//import com.scb.core.common.util.CommObjSerializationUtil;
//import com.scb.ms.communication.SCBCommObj;
//
//public class SCBMuleFlowInterceptor extends AbstractEnvelopeInterceptor {
//
//	@Override
//	public MuleEvent last(MuleEvent event, ProcessingTime time, long startTime, boolean exceptionWasThrown)
//			throws MuleException {
//		logger.info(event.getMessage().getProperty("UUID", PropertyScope.INVOCATION)+" - Flow:: " + event.getFlowConstruct().getName() + ", Procesing Time:: " + (System.currentTimeMillis() - startTime)
//				+ " millisecond(s)");
//		return event;
//	}
//
//	@Override
//	public MuleEvent before(MuleEvent event) throws MuleException {
//		try {
//			MuleMessage message = event.getMessage();
//			SCBCommObj commObj = CommObjSerializationUtil.fromJson(message.getPayloadAsString());
//			message.setProperty("UUID",commObj.getUUID(), PropertyScope.INVOCATION);
//			event.setMessage(message);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return event;
//	}
//
//	@Override
//	public MuleEvent after(MuleEvent event) throws MuleException {
//
//		return event;
//	}
//}
