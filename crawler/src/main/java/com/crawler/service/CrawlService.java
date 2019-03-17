package com.crawler.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public SiteMap crawlSite(String url , List<String> domain ,int noOfPagesToCrawl){
		Map<String, SiteMap> visitedUrl = new HashMap<>();
		CrawlSite crawlSite = new CrawlSite(true ,domain , noOfPagesToCrawl);
		return  crawlSite.crawlUrl(url, null,  visitedUrl );
	}
	
}
