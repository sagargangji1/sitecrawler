package com.crawler.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;

import com.crawler.pojo.SiteMapResponse;
import com.crawler.utils.CrawlSite;
/**
 * 
 * @author Sagar Gangji
 * sagargangji1@gmail.com
 */


@Component
public class CrawlService {

	public SiteMapResponse crawlSite(String url , List<String> domain  , String baseUrl , CopyOnWriteArrayList<String> visitedUrl){
		if(visitedUrl == null)
		visitedUrl = new CopyOnWriteArrayList<>();
		visitedUrl.add(url);
		CrawlSite crawlSite = new CrawlSite(true ,domain  , baseUrl);
		SiteMapResponse siteMapResponse = new SiteMapResponse(); 		
		siteMapResponse.setSiteMap( crawlSite.crawlUrl(url, visitedUrl ));
		siteMapResponse.setVisitedUrl(visitedUrl);
		return siteMapResponse;
	}
	
}
