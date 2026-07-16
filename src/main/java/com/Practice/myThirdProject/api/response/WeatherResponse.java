package com.Practice.myThirdProject.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherResponse {
    private Location location;
    private Current current;

    public WeatherResponse() {}

    public WeatherResponse(Location location, Current current) {
        this.location = location;
        this.current = current;
    }

    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }

    public Current getCurrent() { return current; }
    public void setCurrent(Current current) { this.current = current; }

    @Override
    public String toString() {
        return "WeatherResponse{" +
                "location=" + location +
                ", current=" + current +
                '}';
    }

    // Nested Location class
    public static class Location {
    	@JsonProperty("name")
        private String name;
    	
    	@JsonProperty("region")
        private String region;
    	
    	@JsonProperty("country")
        private String country;
    	
    	@JsonProperty("lat")
        private double lat;
    	
    	@JsonProperty("lon")
        private double lon;
    	
    	@JsonProperty("tz_id")
        private String tzId;
    	
    	@JsonProperty("localtime_epoch")
        private int localtimeEpoch;
    	
    	@JsonProperty("localtime")
        private String localtime;
        public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getRegion() {
			return region;
		}
		public void setRegion(String region) {
			this.region = region;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public double getLat() {
			return lat;
		}
		public void setLat(double lat) {
			this.lat = lat;
		}
		public double getLon() {
			return lon;
		}
		public void setLon(double lon) {
			this.lon = lon;
		}
		public String getTzId() {
			return tzId;
		}
		public void setTzId(String tzId) {
			this.tzId = tzId;
		}
		public int getLocaltimeEpoch() {
			return localtimeEpoch;
		}
		public void setLocaltimeEpoch(int localtimeEpoch) {
			this.localtimeEpoch = localtimeEpoch;
		}
		public String getLocaltime() {
			return localtime;
		}
		public void setLocaltime(String localtime) {
			this.localtime = localtime;
		}
		

        // getters/setters
    }

    // Nested Current class
    public static class Current {
    	@JsonProperty("temp_c")
        private double tempC;
    	
    	@JsonProperty("temp_f")
        private double tempF;
		public double getTempC() {
			return tempC;
		}
		public void setTempC(double tempC) {
			this.tempC = tempC;
		}
		public double getTempF() {
			return tempF;
		}
		public void setTempF(double tempF) {
			this.tempF = tempF;
		}

        // getters/setters
    }
}
