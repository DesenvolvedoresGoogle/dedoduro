package ro.dedodu.dedoduro.mobile.model;

import java.util.HashMap;

public class GpsRegister {

    private Long id;
    private User user;
    private Double lat;
    private Double lng;
    private String description;
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public static class Converter {
        public static GpsRegister from(HashMap map) {
            GpsRegister gpsRegister = new GpsRegister();
            gpsRegister.setId(Long.valueOf((Integer) map.get("id")));
            gpsRegister.setDescription((String) map.get("description"));
            gpsRegister.setLat((Double) map.get("lat"));
            gpsRegister.setLng((Double) map.get("lng"));
            gpsRegister.setCategory(Category.Converter.from((HashMap) map.get("category")));
            return gpsRegister;
        }
    }
}
