package com.practica1.apiRest.getPeticion;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"name", "type", "urlSearch", "country", "service"})
public class LinkInfo {

	@JsonProperty(index = 1)
	private String name;
	@JsonProperty(index = 2)
	private String type;
	@JsonProperty(index = 3)
	private String urlSearch;
	@JsonProperty(index = 4)
	private String country;
	@JsonProperty(index = 5)
	private String service;
	
	public LinkInfo(String name, String type, String urlSearch, String country, String service) {
		super();
		this.name = name;
		this.type = type;
		this.urlSearch = urlSearch;
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
	public String getUrlSearch() {
		return urlSearch;
	}
	public void setUrlSearch(String urlSearch) {
		this.urlSearch = urlSearch;
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
