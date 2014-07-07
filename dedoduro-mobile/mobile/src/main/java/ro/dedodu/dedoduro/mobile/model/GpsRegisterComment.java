package ro.dedodu.dedoduro.mobile.model;

import java.util.HashMap;

public class GpsRegisterComment {

    private GpsRegister gpsRegister;
    private User user;
    private String comment;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private static class Convert {
        public static GpsRegisterComment from(HashMap map) {
            GpsRegisterComment gpsRegisterComment = new GpsRegisterComment();
            gpsRegisterComment.setComment((String) map.get("comment"));

            return gpsRegisterComment;
        }
    }
}
