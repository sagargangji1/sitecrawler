package com.crawler.pojo;

import com.crawler.model.SiteMap;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SiteMapResponse {

	Status status;
	SiteMap siteMap;
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public SiteMap getSiteMap() {
		return siteMap;
	}
	public void setSiteMap(SiteMap siteMap) {
		this.siteMap = siteMap;
	}
	
	
}
