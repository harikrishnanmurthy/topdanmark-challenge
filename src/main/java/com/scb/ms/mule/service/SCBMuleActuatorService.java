//package com.scb.ms.mule.service;
//
//import org.mule.api.MuleEventContext;
//import org.mule.api.MuleMessage;
//import org.mule.api.lifecycle.Callable;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.actuate.endpoint.BeansEndpoint;
//import org.springframework.boot.actuate.endpoint.DumpEndpoint;
//import org.springframework.boot.actuate.endpoint.EnvironmentEndpoint;
//import org.springframework.boot.actuate.endpoint.HealthEndpoint;
//import org.springframework.boot.actuate.endpoint.InfoEndpoint;
//import org.springframework.boot.actuate.endpoint.MetricsEndpoint;
//import org.springframework.boot.actuate.endpoint.TraceEndpoint;
//import org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter;
//
//import com.scb.ms.core.util.SCBObjectMapper;
//
//public class SCBMuleActuatorService implements Callable {
//
//	@Autowired
//	HealthEndpoint healthEndpoint;
//	
//	@Autowired
//	InfoEndpoint infoEndpoint;
//
//	@Autowired
//	MetricsEndpoint metricsEndpoint;
//
//	@Autowired
//	TraceEndpoint traceEndpoint;
//	
//	@Autowired
//	DumpEndpoint dumpEndpoint;
//	
//	@Autowired
//	EnvironmentEndpoint envEndpoint;
//	
//	@Autowired
//	BeansEndpoint beansEndpoint;
//	
//	@Autowired
//	SCBObjectMapper mapper;
//
//	@Override
//	public Object onCall(MuleEventContext arg0) throws Exception {
//		MuleMessage message = arg0.getMessage();
//
//		String enpoint = arg0.getFlowConstruct().getName();
//
//		switch (enpoint) {
//		case "get:/info:actuator-config":
//			message.setPayload(mapper.writeValueAsString(infoEndpoint.invoke()));
//			break;
//		case "get:/health:actuator-config":
//			message.setPayload(mapper.writeValueAsString(healthEndpoint.invoke()));
//			break;
//		case "get:/metrics:actuator-config":
//			message.setPayload(mapper.writeValueAsString(metricsEndpoint.invoke()));
//			break;
//		case "get:/trace:actuator-config":
//			message.setPayload(mapper.writeValueAsString(traceEndpoint.invoke()));
//			break;
//		case "get:/dump:actuator-config":
//			message.setPayload(mapper.writeValueAsString(dumpEndpoint.invoke()));
//			break;
//		case "get:/env:actuator-config":
//			message.setPayload(mapper.writeValueAsString(envEndpoint.invoke()));
//			break;
//		case "get:/beans:actuator-config":
//			message.setPayload(mapper.writeValueAsString(beansEndpoint.invoke()));
//			break;
//		default:
//			message.setPayload("Endpoint not found.");
//			break;
//		}
//		return message;
//	}
//
//}
