package com.scb.ms.mule.transformer;

import java.util.List;
import java.util.Map;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomDistributionTransformer extends AbstractMessageTransformer {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomDistributionTransformer.class);

	@Override
	public Object transformMessage(MuleMessage response, String arg1) throws TransformerException {
		// TODO Auto-generated method stub
		String resString = null;
		try {
			List<Map<String,String>> mapList = (List<Map<String,String>>)response.getPayload();
			response.setInvocationProperty("distributionRequest",
					"<distribution_request><customerId></customerId><name></name><street_1></street_1><street_2></street_2><zipcode></zipcode><city></city><country></country><policynumber></policynumber><policytype></policytype><price></price></distribution_request>");
			
			for(Map<String,String> map : mapList){
				if(null!=map.get("name")){
					response.setInvocationProperty("name", map.get("name"));
				}
				if(null!=map.get("street_1")){
					response.setInvocationProperty("street_1", map.get("street_1"));
				}
				if(null!=map.get("street_2")){
					response.setInvocationProperty("street_2", map.get("street_2"));
				}
				if(null!=map.get("zipcode")){
					response.setInvocationProperty("zipcode", map.get("zipcode"));
				}
				if(null!=map.get("city")){
					response.setInvocationProperty("city", map.get("city"));
				}
				if(null!=map.get("country")){
					response.setInvocationProperty("country", map.get("country"));
				}
				if(null!=map.get("policy")){
					response.setInvocationProperty("policynumber", map.get("policy"));
				}
				if(null!=map.get("type")){
					response.setInvocationProperty("policytype", map.get("type"));
				}
				if(null!=map.get("premium") && null!=map.get("currency")){
					response.setInvocationProperty("price", map.get("premium")+" "+map.get("currency"));
				}
				if(null!=map.get("customerId")){
					response.setInvocationProperty("customerId", response.getInvocationProperty("customerId"));
				}
			}
			
		} catch (Exception e) {
			LOGGER.error("Exception :" + e);
			e.printStackTrace();
		}
		return resString;
	}
}