package com.crawler.utils;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.crawler.model.SiteMap;

public class CrawlSiteTest {

	@Test
	public final void testCrawl() {
		List<String> allowedDomain = new ArrayList<String>() {
			private static final long serialVersionUID = 1L;
			{
				add("redhat.com");
			}
		};

		List<String> visitedUrl = new LinkedList<>();
		visitedUrl.add("http://redhat.com");
		CrawlSite crawlSite = new CrawlSite(true, allowedDomain, 5 , "http://redhat.com");
		SiteMap siteMapRoot = new SiteMap("http://redhat.com","http://redhat.com"); 
		SiteMap siteMap = crawlSite.crawlUrl("http://redhat.com",  visitedUrl ,siteMapRoot);
		System.out.println(siteMap);
		System.out.println(siteMap.getText());
		assertEquals(siteMap.getText(), "http://redhat.com");

		// check for failure
		siteMap = crawlSite.crawlUrl("redhat.com",  visitedUrl ,siteMapRoot);
		assertEquals(siteMap, null);
	}

}
