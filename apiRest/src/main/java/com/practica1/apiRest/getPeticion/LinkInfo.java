package com.practica1.apiRest.getPeticion;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"name", "type", "link", "country", "service"})
public class LinkInfo {
	
	private String name;
	private String type;
	private String link;
	private String country;
	private String service;
	
	public LinkInfo(String name, String type, String link, String country, String service) {
		super();
		this.name = name;
		this.type = type;
		this.link = link;
		this.country = country;
		this.service = service;
	}
	
	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getLink() {
		return link;
	}



	public void setLink(String link) {
		this.link = link;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public String getService() {
		return service;
	}



	public void setService(String service) {
		this.service = service;
	}
	
}
