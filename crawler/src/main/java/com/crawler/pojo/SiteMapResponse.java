package com.crawler.pojo;

import java.util.concurrent.CopyOnWriteArrayList;

import com.crawler.model.SiteMap;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SiteMapResponse {

	Status status;
	SiteMap siteMap;
	CopyOnWriteArrayList<String> visitedUrl;
	
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
	public CopyOnWriteArrayList<String> getVisitedUrl() {
		return visitedUrl;
	}
	public void setVisitedUrl(CopyOnWriteArrayList<String> visitedUrl) {
		this.visitedUrl = visitedUrl;
	}
}
