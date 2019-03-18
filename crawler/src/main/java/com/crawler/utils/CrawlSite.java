package com.crawler.utils;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.crawler.model.SiteMap;

/**
 * 
 * @author Sagar Gangji
 * sagargangji1@gmail.com
 */

public class CrawlSite {

	private boolean restrictDomain;

	private List<String> allowedDomains;

	private String baseUrl;
	
	private AtomicInteger counter;

	private int maxUrlToBeCrawl;

	public CrawlSite(boolean restrictDomain, List<String> allowedDomains, int maxUrlToBeCrawl,String baseUrl) {
		this.restrictDomain = restrictDomain;
		this.allowedDomains = allowedDomains;
		this.maxUrlToBeCrawl = maxUrlToBeCrawl;
		this.counter = new AtomicInteger(1);
		this.baseUrl= baseUrl;
	}

	public SiteMap crawlUrl(String url, String text, Map<String, SiteMap> visitedUrl) {
		String lowerCaseUrl = url.toLowerCase();
		if ((text == null || text.isEmpty() ) && visitedUrl.size() > 0 )
			return null;
		
		if (visitedUrl.containsKey(lowerCaseUrl)) {
			return new SiteMap(text);
		}

		SiteMap parentSiteMap = null;
		if(text == null || text.isEmpty()  )
			text= url;
		parentSiteMap = new SiteMap(text);
		visitedUrl.put(url, parentSiteMap);
		counter.incrementAndGet();
		try {
			Document doc = Jsoup.connect(url).get();
			// get all links and recursively call the crawlUrl method
			Elements questions = doc.select("a[href]");
			for (Element link : questions) {
				String nextUrl = link.attr("href");
				nextUrl = formUrl(nextUrl);
				if (doCrawlNextPage(nextUrl) && link.text() != null && !link.text().isEmpty()) {
					SiteMap siteMap = crawlUrl(nextUrl, link.text(), visitedUrl);
					if(siteMap != null)
						parentSiteMap.addChildren(siteMap);
				}

			}
		}
		// Handle Malformed Url Exception
		catch (MalformedURLException e) {

		} catch (Exception e) {

		}

		return parentSiteMap;
	}

	private String formUrl(String nextUrl) {
		if(!nextUrl.startsWith("http"))
			nextUrl = baseUrl + "/" +nextUrl;
		return nextUrl;
		
	}
	private boolean doCrawlNextPage(String url) {
		if (counter.get() >= this.maxUrlToBeCrawl)
			return false;
		if (url == null)
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
