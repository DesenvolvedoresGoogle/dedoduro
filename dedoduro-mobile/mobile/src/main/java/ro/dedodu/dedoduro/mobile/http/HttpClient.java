package ro.dedodu.dedoduro.mobile.http;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class HttpClient {

    private static HttpClient client;
    private RequestQueue queue;

    private HttpClient(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    public static HttpClient getClient(Context context) {
        if (client == null) {
            client = new HttpClient(context);
        }
        return client;
    }

    public RequestQueue getRequestQueue() {
        return this.queue;
    }

    public enum RequestURL {
        CATEGORY("categories"),
        USER("users"),
        GPS_REGISTER("registers"),
        GPS_REGISTER_RATE("karma"),
        GPS_REGISTER_COMMENT("comments"),
        QUERY_BY_CATEGORY("registers/byCategory?categoryId=");

        private String value;

        private RequestURL(String value) {
            this.value = "http://192.168.43.182:8080/" + value;
        }

        public String value() {
            return value;
        }
    }
}
