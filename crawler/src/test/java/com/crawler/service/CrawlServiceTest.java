package com.crawler.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.crawler.model.SiteMap;
import com.crawler.pojo.SiteMapResponse;

@RunWith(SpringRunner.class)
public class CrawlServiceTest {

	@InjectMocks
	private CrawlService crawlService;

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
		SiteMapResponse siteMapResponse= crawlService.crawlSite("hsttp://redhat.com", allowedDomain, "htstp://redhat.com",visitedUrl);
		assertEquals(siteMapResponse.getSiteMap().getChildren(), null);

		siteMapResponse = crawlService.crawlSite("http://redhat.com", allowedDomain, "http://redhat.com",visitedUrl);
		assertEquals(siteMapResponse.getSiteMap().getChildren().size() > 0, true);
	}
}
