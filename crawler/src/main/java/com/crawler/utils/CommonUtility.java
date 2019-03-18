package com.crawler.utils;

import java.net.URI;
import java.net.URL;

/**
 * 
 * @author Sagar Gangji sagargangji1@gmail.com
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
			return null;
		}
		return null;
	}

	public static String getBaseUrl(String url) {
		try {
			URL urlObj = new URL(url);
			String path = null;
			if(urlObj.getFile().lastIndexOf('/') != -1)
				path = urlObj.getFile().substring(0, urlObj.getFile().lastIndexOf('/'));
			else
				path = urlObj.getFile().substring(0, urlObj.getFile().length());
			
			System.out.println(path);
			return urlObj.getProtocol() + "://" + urlObj.getHost() + path;
		} catch (Exception e) {
			return null;
		}
	}
}
