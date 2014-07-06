package ro.dedodu.dedoduro.mobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockActivity;

import java.sql.SQLException;

import ro.dedodu.dedoduro.mobile.R;
import ro.dedodu.dedoduro.mobile.dao.DaoFactory;
import ro.dedodu.dedoduro.mobile.dao.UserDao;
import ro.dedodu.dedoduro.mobile.services.PullingService;

public class SplashScreenActivity extends RoboSherlockActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        startPullingService();

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Class activityClass = AuthActivity.class;
                if (hasUserAuthenticated()) {
                    activityClass = MainActivity.class;
                }
                Intent intent = new Intent(SplashScreenActivity.this, activityClass);
                startActivity(intent);

                SplashScreenActivity.this.finish();
            }
        }, 1500);
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
