package com.Practice.myThirdProject.api.response;

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
        private String name;
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
		private String region;
        private String country;
        private double lat;
        private double lon;
        private String tzId;
        private int localtimeEpoch;
        private String localtime;

        // getters/setters
    }

    // Nested Condition class
    public static class Condition {
        private String text;
        private String icon;
        private int code;
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getIcon() {
			return icon;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}
		public int getCode() {
			return code;
		}
		public void setCode(int code) {
			this.code = code;
		}

        // getters/setters
    }

    // Nested Current class
    public static class Current {
        private double tempC;
        private double tempF;
        private Condition condition;
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
		public Condition getCondition() {
			return condition;
		}
		public void setCondition(Condition condition) {
			this.condition = condition;
		}

        // getters/setters
    }
}
