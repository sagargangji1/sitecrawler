package com.crawler.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.crawler.model.SiteMap;
import com.crawler.utils.CrawlSite;
/**
 * 
 * @author Sagar Gangji
 * sagargangji1@gmail.com
 */


@Component
public class CrawlService {

	public SiteMap crawlSite(String url , List<String> domain ,int noOfPagesToCrawl , String baseUrl){
		SiteMap root = new SiteMap(url ,url);
		if(noOfPagesToCrawl < 2)
			return root;
		List<String> visitedUrl = new LinkedList<>();
		visitedUrl.add(url);
		CrawlSite crawlSite = new CrawlSite(true ,domain , noOfPagesToCrawl , baseUrl);
		return  crawlSite.crawlUrl(url, visitedUrl ,root);
	}
	
}
