package com.crawler.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.crawler.model.SiteMap;

/**
 * 
 * @author Sagar Gangji sagargangji1@gmail.com
 */

public class CrawlSite {

	private boolean restrictDomain;

	private List<String> allowedDomains;

	private String baseUrl;

	private AtomicInteger counter;

	private int maxUrlToBeCrawl;

	public CrawlSite(boolean restrictDomain, List<String> allowedDomains, int maxUrlToBeCrawl, String baseUrl) {
		this.restrictDomain = restrictDomain;
		this.allowedDomains = allowedDomains;
		this.maxUrlToBeCrawl = maxUrlToBeCrawl;
		this.counter = new AtomicInteger(1);
		this.baseUrl = baseUrl;
	}

	public SiteMap crawlUrl(String url, List<String> visitedUrl, SiteMap parentSiteMap) {
		if (isCounterExceed())
			return null;
		try {
			Document doc = Jsoup.connect(url).get();
			Elements questions = doc.select("a[href]");

			// get all links and recursively call the crawlUrl method
			List<SiteMap> childs = new ArrayList<>();
			for (Element link : questions) {
				if (isCounterExceed())
					break;
				String text = link.text();
				if (text == null || text.isEmpty())
					continue;
				String nextUrl = link.attr("href");
				nextUrl = formUrl(nextUrl);
				try {
					new URL(nextUrl);
					if (doCrawlNextPage(nextUrl, visitedUrl)) {
						SiteMap siteMap = new SiteMap(link.text(), nextUrl);
						// crawlUrl(nextUrl, link.text(), visitedUrl);
						childs.add(siteMap);
						visitedUrl.add(nextUrl.toLowerCase());
						counter.incrementAndGet();
					}
				} catch (Exception e) {
					continue;
				}
				

			}
			parentSiteMap.setChildren(childs);
			for (SiteMap siteMap : childs) {
				if (isCounterExceed())
					break;
				crawlUrl(siteMap.getUrl(), visitedUrl, siteMap);
			}

		}
		// Handle Malformed Url Exception
		catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parentSiteMap;
	}

	private String formUrl(String nextUrl) {
		if (!nextUrl.startsWith("http"))
			nextUrl = baseUrl + "/" + nextUrl;
		return nextUrl;

	}

	private boolean isCounterExceed() {
		if (counter.get() > this.maxUrlToBeCrawl)
			return true;
		return false;
	}

	private boolean doCrawlNextPage(String url, List<String> visitedUrl) {
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
