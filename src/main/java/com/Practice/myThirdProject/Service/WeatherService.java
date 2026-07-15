package com.Practice.myThirdProject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Practice.myThirdProject.api.response.WeatherResponse;

@Service
public class WeatherService {
	
	private final String apiKey;
	
	private final String city;
	
	private static String API="https://api.weatherapi.com/v1/current.json?key=apikey&q=city";
	
	public WeatherService(@Value("${my.weather.api.key}") String apiKey,@Value("${my.weather.api.city}") String city) {
		this.apiKey = apiKey;
		this.city = city;
	}
	
	@Autowired
	private RestTemplate restTemplate;
	
	public WeatherResponse getWeather(String city) {
		String finalAPI = API.replace("apikey",apiKey).replace("city",city);
		ResponseEntity<WeatherResponse> res  = restTemplate.exchange(finalAPI , HttpMethod.GET,null,WeatherResponse.class);
		WeatherResponse weatherResponse = res.getBody();
		return weatherResponse;
	}
	
	
	
}
