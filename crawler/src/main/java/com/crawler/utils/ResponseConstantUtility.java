package com.crawler.utils;

import com.crawler.pojo.Status;

/**
 * 
 * @author Sagar Gangji
 * sagargangji1@gmail.com
 */
public class ResponseConstantUtility {
	
	public static Status getSuccessResponse(){
		Status status = new Status();
		status.setCode(200);
		status.setMessage("Successfully Processed.");
		return status;
	}
	public static Status getInvalidDomainResponse(){
		Status status = new Status();
		status.setCode(201);
		status.setMessage("Invalid Domain.");
		return status;
	}
	
	public static Status getInvalidNoOfPagesToCrawl(){
		Status status = new Status();
		status.setCode(202);
		status.setMessage("System can Crawl max 1000 Pages At current .");
		return status;
	}
	
	public static Status getJsonParseException(){
		Status status = new Status();
		status.setCode(203);
		status.setMessage("Unable To Parse Json .");
		return status;
	}
	
	public static Status getInvalidPayloadException(){
		Status status = new Status();
		status.setCode(204);
		status.setMessage("Invalid payload in request.");
		return status;
	}
	
	public static Status getGeneralExceptionMessage(){
		Status status = new Status();
		status.setCode(205);
		status.setMessage("System unable to process your request at this point of time.");
		return status;
	}
}
