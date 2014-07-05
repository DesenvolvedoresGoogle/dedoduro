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

    private static HttpClient getClient(Context context) {
        if (client == null) {
            client = new HttpClient(context);
        }
        return client;
    }

    public RequestQueue getRequestQueue() {
        return this.queue;
    }
}
