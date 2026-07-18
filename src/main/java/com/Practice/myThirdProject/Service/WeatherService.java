package com.Practice.myThirdProject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Practice.myThirdProject.api.response.WeatherResponse;
import com.Practice.myThirdProject.cache.AppCache;

@Service
public class WeatherService {
	
	private final String apiKey;
	
	
	
	public WeatherService(@Value("${my.weather.api.key}") String apiKey) {
		this.apiKey = apiKey;
	}
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@Autowired
	private AppCache appCache;
	
	public WeatherResponse getWeather(String city) {
		String finalAPI = appCache.APP_CACHE.get("API_URL").replace("<apikey>",apiKey).replace("<city>",city);
		ResponseEntity<WeatherResponse> res  = restTemplate.exchange(finalAPI , HttpMethod.GET,null,WeatherResponse.class);
		WeatherResponse weatherResponse = res.getBody();
 		return weatherResponse;
	}
	
	 
	
}
