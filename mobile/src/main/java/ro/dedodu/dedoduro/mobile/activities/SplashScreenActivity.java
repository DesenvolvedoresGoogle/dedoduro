package ro.dedodu.dedoduro.mobile.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.sql.SQLException;

import ro.dedodu.dedoduro.mobile.dao.DaoFactory;
import ro.dedodu.dedoduro.mobile.dao.UserDao;
import ro.dedodu.dedoduro.mobile.services.PullingService;

public class SplashScreenActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startPullingService();

        Class activityClass = AuthActivity.class;
        if (hasUserAuthenticated()) {
            activityClass = MainActivity.class;
        }
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }

    public void startPullingService() {
        Intent service = new Intent(this, PullingService.class);
        startService(service);
    }

    public boolean hasUserAuthenticated() {
        try {
            DaoFactory<UserDao> daoFactory = new DaoFactory<UserDao>();
            final UserDao userDao = daoFactory.create(this, UserDao.class);

            return userDao.countOf() > 0;
        } catch (SQLException e) {
            return false;
        }
    }
}
