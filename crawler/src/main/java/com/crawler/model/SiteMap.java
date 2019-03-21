package com.crawler.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SiteMap implements Cloneable {

	String text;
	String url;
	List<SiteMap> children;

	public SiteMap() {

	}

	public SiteMap(String text ,String url) {
		this.text = text;
		this.url = url;
	}

	public void addChildren(SiteMap siteMap) {
		if (children == null)
			children = new ArrayList<>();
		children.add(siteMap);
	}

	public void addChildrens(List<SiteMap> siteMaps) {
		if (children == null)
			children = new ArrayList<>();
		children.addAll(siteMaps);
	}
	public String getText() {
		return text;
	}

	public List<SiteMap> getChildren() {
		return children;
	}

	public void setChildren(List<SiteMap> childeren) {
		this.children = childeren;
	}

	public void setName(String text) {
		this.text = text;
	}

	@Override
	protected SiteMap clone() throws CloneNotSupportedException {
		SiteMap siteMap = new SiteMap();
		siteMap.setName(this.getText());
		siteMap.setName(this.getText());
		List<SiteMap> clone = new ArrayList<>(children.size());
		for (SiteMap item : children)
			clone.add(item.clone());
		siteMap.setChildren(clone);
		return siteMap;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
