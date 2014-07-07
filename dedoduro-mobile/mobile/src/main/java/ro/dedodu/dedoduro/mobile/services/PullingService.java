package ro.dedodu.dedoduro.mobile.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import ro.dedodu.dedoduro.mobile.dao.CategoryDao;
import ro.dedodu.dedoduro.mobile.dao.DaoFactory;
import ro.dedodu.dedoduro.mobile.http.HttpClient;
import ro.dedodu.dedoduro.mobile.http.JacksonJsonRequest;
import ro.dedodu.dedoduro.mobile.model.Category;

import static ro.dedodu.dedoduro.mobile.http.HttpClient.RequestURL.CATEGORY;

public class PullingService extends Service {

    private final CategoryDao categoryDao;

    public PullingService() {
        DaoFactory<CategoryDao> daoFactory = new DaoFactory<CategoryDao>();
        categoryDao = daoFactory.create(this, CategoryDao.class);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        new Timer().schedule(new PullingTimerTask(), 1000, 1000 * 60);
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class PullingTimerTask extends TimerTask {

        @Override
        public void run() {
            JacksonJsonRequest<Void, List> jacksonJsonRequest =
                    new JacksonJsonRequest<Void, List>(Request.Method.GET, CATEGORY.value(), null, new SuccessListener(), null, List.class);

            RequestQueue queue = HttpClient.getClient(PullingService.this)
                    .getRequestQueue();

            queue.add(jacksonJsonRequest);
            queue.start();
        }
    }

    private class SuccessListener implements Response.Listener<List> {

        @Override
        public void onResponse(List response) {
            try {
                if (response != null) {
                    for (Object category : response) {
                        categoryDao.createOrUpdate(Category.Converter.from((HashMap) category));
                    }
                }
            } catch (SQLException e) {}
        }
    }
}
