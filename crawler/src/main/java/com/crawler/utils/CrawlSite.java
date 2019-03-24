package com.crawler.utils;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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

	public CrawlSite(boolean restrictDomain, List<String> allowedDomains, String baseUrl) {
		this.restrictDomain = restrictDomain;
		this.allowedDomains = allowedDomains;
		this.baseUrl = baseUrl;
	}

	public SiteMap crawlUrl(String url, CopyOnWriteArrayList<String> visitedUrl) {
		SiteMap siteMap = new SiteMap();
		siteMap.setText(url);
		siteMap.setUrl(url);
		try {
			Document doc = Jsoup.connect(url).get();
			Elements questions = doc.select("a[href]");

			int totalRecord = questions.size();
			if(totalRecord == 0 )
				return null;

			int start = totalRecord;
			
			if (start > 3)
				start = totalRecord / 3;
			ExecutorService executor = Executors.newFixedThreadPool(3);
			List<Callable<SiteMap>> callableTasks = new ArrayList<>();
			callableTasks.add(new CrawlThread(questions.subList(0, start), baseUrl, restrictDomain,
					allowedDomains, visitedUrl));
			if (start != totalRecord) {
				int end = start * 2;
				if (end > totalRecord)
					end = totalRecord;
				callableTasks.add(new CrawlThread(questions.subList(start, end), baseUrl, restrictDomain,
						allowedDomains, visitedUrl));
				if (end < totalRecord){
					callableTasks.add(new CrawlThread(questions.subList(end, totalRecord), baseUrl,
						restrictDomain, allowedDomains, visitedUrl));
				}
			}
			List<Future<SiteMap>> childs = executor.invokeAll(callableTasks);
			for (Future<SiteMap> future : childs) {
				if (future.get() != null && future.get().getChildren() != null)
					siteMap.addChildrens(future.get().getChildren());
			}

		} catch (MalformedURLException e) {
		} catch (Exception e) {
		}
		return siteMap;
	}

}