package com.crawler.utils;

import java.net.URI;

/**
 * 
 * @author Sagar Gangji
 * sagargangji1@gmail.com
 */
public class CommonUtility {

	public static String getDomain(String url) {
		 try {
	            URI uri = new URI(url);
	            String domain = uri.getHost();
	            if (uri.getScheme() != null) {
	                return domain.startsWith("www.") ? domain.substring(4) : domain;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
		 return null;
	}
}
