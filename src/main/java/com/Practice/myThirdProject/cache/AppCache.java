package com.Practice.myThirdProject.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Practice.myThirdProject.Entity.ConfigJournal;
import com.Practice.myThirdProject.Repository.ConfigJournalRepository;

import jakarta.annotation.PostConstruct;

@Component
public class AppCache {
	
	
	@Autowired
	private ConfigJournalRepository configJournalRepo;
	
	
	
	public Map<String,String> APP_CACHE = new HashMap<>();
	
	
	@PostConstruct
	public void init() {
		List<ConfigJournal> ls =configJournalRepo.findAll();
		for (ConfigJournal configJournal : ls) {
			APP_CACHE.put(configJournal.getKey_name(),configJournal.getKey_value());
		}
	}
	

}
