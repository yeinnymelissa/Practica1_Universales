package com.practica1.apiRest.getPeticion;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.JsonStreamParser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONArray;

@RestController
@RequestMapping("/rest")
@CrossOrigin
public class Practica1Controller {
	@RequestMapping("/hello")
	public String hello() {
		return "Hello Yeinny";
	}
	
	@GetMapping(value = "/callClientHello")
	private String getHelloClient() {
		String link = "http://localhost:9099/hello";
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(link, String.class);
		return result;
	}
	
    //FUNCION DE TIPO GET, ESTA FUNCION AYUDA A ENCONTRAR (POR MEDIO DE LA URL) 
	//INFORMACIÓN SOBRE UN ARTISTA Y SU TRABAJO EN ESPECIFICO, CONSULTANDO DOS APIS
	//LA API DE ITUNES Y LA DE TVMAZE
	@GetMapping(value = "/find/{nameC}")
	private List findTodo(@PathVariable("nameC") String nameC) {
		
		String[] splitName = nameC.split("%20");
		
		String nameSinger = "";
		
		for (int i=0;i<splitName.length;i++) {
		      if(i == 0){
		    	  nameSinger += splitName[i];
		      }else {
		    	  nameSinger += "+" + splitName[i];
		      }
		}
		RestTemplate restTemplate = new RestTemplate();
		String linkITunes = "https://itunes.apple.com/search?term="+nameSinger+"&attribute=allArtistTerm";
		String result = restTemplate.getForObject(linkITunes, String.class);
		
		
		String linkTVMaze = "https://api.tvmaze.com/search/people?q="+nameC;
		String resultTV = restTemplate.getForObject(linkTVMaze, String.class);

		
		JSONObject jsonITunes = new JSONObject(result); 
		ArrayList<PeopleInfo> listPeople = new ArrayList<PeopleInfo>();
		
		JSONArray objectInfo = (JSONArray) jsonITunes.get("results");
		
		int cont = 0;
        while(cont < objectInfo.length()){
            JSONObject person = (JSONObject) objectInfo.get(cont);
            String name = person.get("artistName").toString();
            String track = person.get("trackName").toString();
            String type = person.get("kind").toString();
            String service = "API ITunes";
            PeopleInfo one = new PeopleInfo(name, track, type, service, linkITunes);
            listPeople.add(one);
            cont++;
        }
        
        JSONArray jsonTVMaze = new JSONArray(resultTV);
        
        int contTV = 0;
        while(contTV < jsonTVMaze.length()){
            JSONObject score = (JSONObject) jsonTVMaze.get(contTV);
            JSONObject person = (JSONObject) score.get("person");
            String name = person.get("name").toString();
            String type = "People";
            String service = "API TVMaze";
            PeopleInfo one = new PeopleInfo(name, null, type, service, linkTVMaze);
            listPeople.add(one);
            contTV++;
        }
		
		ObjectMapper mapper = new ObjectMapper();
		
		String strProb= "[";
		
		for(int i = 0; i < listPeople.size(); i++) {

		    mapper.enable(SerializationFeature.INDENT_OUTPUT);
		    if(i == 0) {
		    	
			    try {
					strProb += mapper.writeValueAsString(listPeople.get(i));
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }else {
		    	try {
					strProb += ","+mapper.writeValueAsString(listPeople.get(i));
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
			
		}
		
		strProb += "]";

		Gson gson = new Gson();
		
		List objectJson = gson.fromJson(strProb, List.class);
		
		return objectJson;
	}

    //FUNCION DE TIPO GET, ESTA FUNCION AYUDA A REALIZAR UNA BUSQUEDA (POR MEDIO DE LA URL) 
	//DE UNA PALABRA Y ENCUENTRA TODAS LAS COINCIDENCIAS POSIBLES MOSTRANDO SU URL PARA
	//ENCONTRARLO MAS FACIL, SE BUSCA EN LA API DE ITUNES Y LA DE TVMAZE
	@GetMapping(value = "/searchLink/{parameter}")
	private List searchLink(@PathVariable("parameter") String parameter) {
		
		String[] splitParameter = parameter.split("%20");
		
		String nameParameter = "";
		
		for (int i=0;i<splitParameter.length;i++) {
		      if(i == 0){
		    	  nameParameter += splitParameter[i];
		      }else {
		    	  nameParameter += "+" + splitParameter[i];
		      }
		}
		RestTemplate restTemplate = new RestTemplate();
		String linkITunes = "https://itunes.apple.com/search?term="+nameParameter;
		String result = restTemplate.getForObject(linkITunes, String.class);
		
		
		String linkTVMaze = "https://api.tvmaze.com/search/shows?q="+parameter;
		String resultTV = restTemplate.getForObject(linkTVMaze, String.class);

		
		JSONObject jsonITunes = new JSONObject(result); 
		ArrayList<LinkInfo> listLink = new ArrayList<LinkInfo>();
		
		JSONArray objectInfo = (JSONArray) jsonITunes.get("results");
		
		int cont = 0;
        while(cont < objectInfo.length()){
            JSONObject person = (JSONObject) objectInfo.get(cont);
            String name = person.get("trackName").toString();
            String type = person.get("kind").toString();
            String link = person.get("trackViewUrl").toString();
            String country = person.get("country").toString();
            String service = "API ITunes";
            LinkInfo one = new LinkInfo(name, type, link, country, service);
            listLink.add(one);
            cont++;
        }
        
        JSONArray jsonTVMaze = new JSONArray(resultTV);
        
        int contTV = 0;
        while(contTV < jsonTVMaze.length()){
            JSONObject score = (JSONObject) jsonTVMaze.get(contTV);
            JSONObject show = (JSONObject) score.get("show");
            String name = show.get("name").toString();
            String link = show.get("url").toString();
            String type = "Show";
            String service = "API TVMaze";
            if(!show.isNull("network")) {
            	JSONObject network = (JSONObject) show.get("network");
            	JSONObject country = (JSONObject) network.get("country");
                String countryName = country.get("name").toString();
                LinkInfo one = new LinkInfo(name, type, link, countryName, service);
                listLink.add(one);
            } else {
            	LinkInfo one = new LinkInfo(name, type, link, null, service);
                listLink.add(one);
            }
            contTV++;
        }
		
		ObjectMapper mapper = new ObjectMapper();
		
		String strProb= "[";
		
		for(int i = 0; i < listLink.size(); i++) {

		    mapper.enable(SerializationFeature.INDENT_OUTPUT);
		    if(i == 0) {
		    	
			    try {
					strProb += mapper.writeValueAsString(listLink.get(i));
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }else {
		    	try {
					strProb += ","+mapper.writeValueAsString(listLink.get(i));
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
			
		}
		
		strProb += "]";

		Gson gson = new Gson();
		
		List objectJson = gson.fromJson(strProb, List.class);
		
		return objectJson;
	}
	
	
}
