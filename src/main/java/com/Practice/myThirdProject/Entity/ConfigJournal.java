package com.Practice.myThirdProject.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="config_properties")
public class ConfigJournal {
	
	
	@Id
	private String id;
	
	@Field("KEY_NAME")
	private String key_name;
	
	@Field("KEY_VALUE")
	private String key_value;

	public String getKey_name() {
		return key_name;
	}

	public void setKey_name(String key_name) {
		this.key_name = key_name;
	}

	public String getKey_value() {
		return key_value;
	}

	public void setKey_value(String key_value) {
		this.key_value = key_value;
	}

	
	
}
