//package com.scb.ms.mule.service;
//
//import java.util.UUID;
//
//import javax.activation.DataHandler;
//
//import org.apache.commons.io.IOUtils;
//import org.apache.log4j.Logger;
//import org.mule.api.MuleEventContext;
//import org.mule.api.MuleMessage;
//import org.mule.api.lifecycle.Callable;
//import org.mule.module.http.internal.multipart.HttpPartDataSource;
//
//import com.scb.core.common.util.SCBCoreDTOUtils;
//import com.scb.ms.communication.SCBCommObj;
//import com.scb.ms.communication.SCBSection;
//
//public class SCBMuleUploadService implements Callable {
//
//	private Logger logger = Logger.getLogger(this.getClass());
//
//	@Override
//	public Object onCall(MuleEventContext eventContext) {
//		SCBCommObj commObj = new SCBCommObj();
//		MuleMessage message = eventContext.getMessage();
//
//		commObj.getHeader().setUUID(UUID.randomUUID().toString());
//		SCBSection section = new SCBSection();
//		try {
//			for (String name : message.getInboundAttachmentNames()) {
//				DataHandler dh = message.getInboundAttachment(name);
//
//				switch (name) {
//				case "bankGroupCode":
//					commObj.getHeader().setBankGroupCode((String) dh.getContent());
//					break;
//				case "cityCode":
//					commObj.getHeader().setCtyCode((String) dh.getContent());
//					break;
//				case "ctyCode":
//					commObj.getHeader().setCtyCode((String) dh.getContent());
//					break;					
//				case "user_id":
//					commObj.getHeader().setUserId((String) dh.getContent());
//					break;
//				case "uploaded_file":
//					commObj.getBody().addSection(SCBCoreDTOUtils
//							.binaryDataToSection(IOUtils.toByteArray(dh.getInputStream()), "UPLOAD_FILE"));
//					HttpPartDataSource httpDs= (HttpPartDataSource) dh.getDataSource();
//					String content = httpDs.getHeader("Content-Disposition");
//					String[] arr = content.split(";");
//					String[] filename = arr[2].split("=");
//					section.putStringValue("originalFilename", filename[1]);
//					break;
//					
//				default:
//					section.putStringValue(name, (String) dh.getContent());
//					break;
//				}
//
//			}
//
//			commObj.setBodySection("UPLOAD_SECTION", section);
//			message.setPayload(commObj);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return message;
//	}
//}
