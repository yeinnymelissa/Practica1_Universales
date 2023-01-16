package com.practica1.apiRest.getPeticion;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"name", "trackName", "type", "service", "serviceUrl"})
public class PeopleInfo {
	private String name;
	private String trackName;
	private String type;
	private String service;
	private String serviceUrl;
	

	public PeopleInfo(String name, String trackName, String type, String service, String serviceUrl) {
		super();
		this.name = name;
		this.trackName = trackName;
		this.type = type;
		this.service = service;
		this.serviceUrl = serviceUrl;
	}

	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getTrackName() {
		return trackName;
	}


	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getService() {
		return service;
	}


	public void setService(String service) {
		this.service = service;
	}


	public String getServiceUrl() {
		return serviceUrl;
	}


	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}	
	
}
