package com.crawler.pojo;

import java.util.List;

public class CrawlRequest {

	String url;
	//optional
	Integer noOfPagesToCrawl;
	
	List<String> allowedDomain;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getNoOfPagesToCrawl() {
		return noOfPagesToCrawl;
	}

	public void setNoOfPagesToCrawl(Integer noOfPagesToCrawl) {
		this.noOfPagesToCrawl = noOfPagesToCrawl;
	}

	public List<String> getAllowedDomain() {
		return allowedDomain;
	}

	public void setAllowedDomain(List<String> allowedDomain) {
		this.allowedDomain = allowedDomain;
	}
	
}
