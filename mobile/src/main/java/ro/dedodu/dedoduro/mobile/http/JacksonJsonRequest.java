package ro.dedodu.dedoduro.mobile.http;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JacksonJsonRequest<K, T> extends Request<T> {

    private ObjectMapper mapper = new ObjectMapper();

    private static final String CHARSET = "utf-8";
    private static final String CONTENT_TYPE = "application/json";
    private static final int DEFAULT_TIMEOUT = 10000;

    private K requestBody;
    private Response.Listener<T> responseListener;
    private Class<T> responseClass;

    public JacksonJsonRequest(int method, String url, K requestBody, Response.Listener<T> responseListener, Response.ErrorListener errorListener, Class<T> responseClass) {
        super(method, url, errorListener);

        this.requestBody = requestBody;
        this.responseListener = responseListener;
        this.responseClass = responseClass;

        setRetryPolicy(new DefaultRetryPolicy(
                DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            T result = mapper.readValue(response.data, responseClass);
            return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void deliverResponse(T response) {
        responseListener.onResponse(response);
    }

    @Override
    public String getBodyContentType() {
        return CONTENT_TYPE + "; charset=" + CHARSET;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        try{
            return requestBody == null ? null : mapper.writeValueAsBytes(requestBody);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
