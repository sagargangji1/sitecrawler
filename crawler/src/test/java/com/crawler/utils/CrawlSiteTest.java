package com.crawler.utils;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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

		CopyOnWriteArrayList<String> visitedUrl = new CopyOnWriteArrayList<>();
		visitedUrl.add("http://redhat.com");
		CrawlSite crawlSite = new CrawlSite(true, allowedDomain, "http://redhat.com");
		SiteMap siteMapResponse = crawlSite.crawlUrl("http://redhat.com",  visitedUrl );
		assertEquals(siteMapResponse.getText(), "http://redhat.com");

		// check for failure
		siteMapResponse = crawlSite.crawlUrl("redhat.com",  visitedUrl );
		assertEquals(siteMapResponse.	getChildren(), null);
	}

}
