package com.crawler.utils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;

import org.jsoup.nodes.Element;

import com.crawler.model.SiteMap;

public class CrawlThread implements Callable<SiteMap> {
	private List<Element> elementsToCrawl;
	private String baseUrl;
	private boolean restrictDomain;
	private List<String> allowedDomains;
	private CopyOnWriteArrayList<String> visitedUrl;

	public CrawlThread(List<Element> elementsToCrawl, String baseUrl ,boolean restrictDomain, List<String> allowedDomains,CopyOnWriteArrayList<String> visitedUrl) {
		this.elementsToCrawl = elementsToCrawl;
		this.baseUrl = baseUrl;
		this.restrictDomain = restrictDomain;
		this.allowedDomains = allowedDomains;
		this.visitedUrl= visitedUrl;
	}

	@Override
	public SiteMap call() throws Exception {
		SiteMap Childeren = new SiteMap();
		List<SiteMap> childs = new ArrayList<>();
		for (Element link : elementsToCrawl) {
			String text = link.text();
			String nextUrl = link.attr("href");
			nextUrl = CrawlerUtility.formUrl(baseUrl, nextUrl);
			if (text == null || text.isEmpty() || CrawlerUtility.isMalformedUrl(nextUrl))
				continue;
			try {
				new URL(nextUrl);
				if (CrawlerUtility.doCrawlNextPage(nextUrl, visitedUrl,restrictDomain,allowedDomains)) {
					SiteMap siteMap = new SiteMap(link.text(), nextUrl);
					siteMap.setChildren(new ArrayList<>());
					childs.add(siteMap);
					visitedUrl.add(nextUrl.toLowerCase());
				}
			} catch (Exception e) {
				continue;
			}

		}
		Childeren.addChildrens(childs);
		return Childeren;
	}

}
