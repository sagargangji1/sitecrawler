package com.crawler.utils;

import java.util.List;

public class CrawlerUtility {

	public static  String formUrl(String baseUrl , String nextUrl) {
		if (!nextUrl.startsWith("http"))
			nextUrl = baseUrl + "/" + nextUrl;
		return nextUrl;

	}
	

	public static  boolean isMalformedUrl(String url) {
		if (url == null)
			return true;
		if (url.indexOf(':') != url.lastIndexOf(':'))
			return true;
		return false;
	}
	
	public static boolean doCrawlNextPage(String url, List<String> visitedUrl , boolean restrictDomain , List<String> allowedDomains) {
		if (url == null)
			return false;
		if (visitedUrl.contains(url.toLowerCase()))
			return false;

		// Allow All domain to crawl
		if (!restrictDomain)
			return true;

		// If Crawling restricted to specific domain then check for domain to
		// crawl
		String domain = CommonUtility.getDomain(url);
		return restrictDomain && allowedDomains.contains(domain);

	}
}
