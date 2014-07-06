package ro.dedodu.dedoduro.mobile.model;

public class GpsRegisterRate {

    private GpsRegister gpsRegister;
    private User user;
    private Integer rate;

    public GpsRegister getGpsRegister() {
        return gpsRegister;
    }

    public void setGpsRegister(GpsRegister gpsRegister) {
        this.gpsRegister = gpsRegister;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
