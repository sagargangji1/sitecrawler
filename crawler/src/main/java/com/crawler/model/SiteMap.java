package com.crawler.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SiteMap implements Cloneable {

	String name;
	List<SiteMap> childerens;

	public SiteMap() {

	}

	public SiteMap(String name) {
		this.name = name;
	}

	public void addChildren(SiteMap siteMap) {
		if (childerens == null)
			childerens = new ArrayList<>();
		childerens.add(siteMap);
	}

	public void addChildrens(List<SiteMap> siteMaps) {
		if (childerens == null)
			childerens = new ArrayList<>();
		childerens.addAll(siteMaps);
	}
	public String getName() {
		return name;
	}

	public List<SiteMap> getChilderens() {
		return childerens;
	}

	public void setChilderens(List<SiteMap> childerens) {
		this.childerens = childerens;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	protected SiteMap clone() throws CloneNotSupportedException {
		SiteMap siteMap = new SiteMap();
		siteMap.setName(this.getName());
		siteMap.setName(this.getName());
		List<SiteMap> clone = new ArrayList<>(childerens.size());
		for (SiteMap item : childerens)
			clone.add(item.clone());
		siteMap.setChilderens(clone);
		return siteMap;
	}
}
