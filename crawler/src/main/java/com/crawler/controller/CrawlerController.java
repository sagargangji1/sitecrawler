package com.crawler.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crawler.commons.Constants;
import com.crawler.model.SiteMap;
import com.crawler.pojo.CrawlRequest;
import com.crawler.pojo.SiteMapResponse;
import com.crawler.service.CrawlService;
import com.crawler.utils.CommonUtility;
import com.crawler.utils.ResponseConstantUtility;
/**
 * 
 * @author Sagar Gangji
 * sagargangji1@gmail.com
 */
@RestController
public class CrawlerController {

	@Autowired
	private CrawlService crawlService;

	@PostMapping("/crawler/crawl/site")
	public SiteMapResponse crawlPage(@RequestBody CrawlRequest crawlRequest) {
		SiteMapResponse siteMapResponse = new SiteMapResponse();
		String domain = CommonUtility.getDomain(crawlRequest.getUrl());
		
		String baseUrl = CommonUtility.getBaseUrl(crawlRequest.getUrl());

		if (domain == null || baseUrl ==  null)
			siteMapResponse.setStatus(ResponseConstantUtility.getInvalidDomainResponse());
		else if (crawlRequest.getNoOfPagesToCrawl() != null
				&& crawlRequest.getNoOfPagesToCrawl() > Constants.MAX_PAGES_TO_CRAWL)
			siteMapResponse.setStatus(ResponseConstantUtility.getInvalidNoOfPagesToCrawl());
		else {
			Integer noOfPagesToCrawl = crawlRequest.getNoOfPagesToCrawl();
			if (noOfPagesToCrawl == null)
				noOfPagesToCrawl = Constants.DEFULT_NO_OF_PAGES_TO_CRAWL;
			List<String> allowedDomain = crawlRequest.getAllowedDomain();
			if (allowedDomain == null) {
				allowedDomain = new ArrayList<>();
				allowedDomain.add(domain);
			}
			SiteMap m = crawlService.crawlSite(crawlRequest.getUrl(), allowedDomain, noOfPagesToCrawl , baseUrl);
			siteMapResponse.setSiteMap(m);
			siteMapResponse.setStatus(ResponseConstantUtility.getSuccessResponse());
		}
		return siteMapResponse;

	}
}
