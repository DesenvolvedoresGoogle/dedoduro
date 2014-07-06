package ro.dedodu.dedoduro.mobile.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.plus.PlusClient;

import java.sql.SQLException;

import ro.dedodu.dedoduro.mobile.R;
import ro.dedodu.dedoduro.mobile.dao.DaoFactory;
import ro.dedodu.dedoduro.mobile.dao.UserDao;
import ro.dedodu.dedoduro.mobile.http.HttpClient;
import ro.dedodu.dedoduro.mobile.http.JacksonJsonRequest;
import ro.dedodu.dedoduro.mobile.model.User;

import static ro.dedodu.dedoduro.mobile.http.HttpClient.RequestURL.USER;


public class AuthActivity extends Activity implements
        ConnectionCallbacks, OnConnectionFailedListener, View.OnClickListener,
        PlusClient.OnAccessRevokedListener {

    private static final String TAG = "AuthActivity";
    private static final int OUR_REQUEST_CODE = 49404;
    private PlusClient plusClient;
    private boolean resolveOnFail;
    private ConnectionResult connectionResult;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        plusClient = new PlusClient.Builder(this, this, this)
                .setScopes(Scopes.PROFILE, Scopes.PLUS_LOGIN, Scopes.PLUS_ME)
                .build();

        resolveOnFail = false;

        findViewById(R.id.sign_in_button).setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Entrando...");
    }

    @Override
    protected void onStart() {
        super.onStart();
        plusClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        plusClient.disconnect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (result.hasResolution()) {
            connectionResult = result;
            if (resolveOnFail) {
                startResolution();
            }
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        resolveOnFail = false;
        registerUser();
    }

    @Override
    public void onDisconnected() {

    }

    protected void onActivityResult(int requestCode, int responseCode,
                                    Intent intent) {
        if (requestCode == OUR_REQUEST_CODE && responseCode == RESULT_OK) {
            resolveOnFail = true;
            plusClient.connect();
        } else if (requestCode == OUR_REQUEST_CODE && responseCode != RESULT_OK) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in_button:
                if (!plusClient.isConnected()) {
                    progressDialog.show();
                    resolveOnFail = true;
                    if (connectionResult != null) {
                        startResolution();
                    } else {
                        plusClient.connect();
                    }
                }
                break;
        }
    }

    @Override
    public void onAccessRevoked(ConnectionResult status) {
        plusClient.connect();
    }

    private void startResolution() {
        try {
            resolveOnFail = false;
            connectionResult.startResolutionForResult(this, OUR_REQUEST_CODE);
        } catch (IntentSender.SendIntentException e) {
            plusClient.connect();
        }
    }

    private void registerUser() {
        final User user =  new User();
        user.setEmail(plusClient.getAccountName());

        RequestQueue queue = HttpClient.getClient(this).getRequestQueue();
        JacksonJsonRequest<User, Long> request = new JacksonJsonRequest<User, Long>(Request.Method.POST, USER.value(), user, new Response.Listener<Long>() {
            @Override
            public void onResponse(Long response) {
                user.setId(response);

                try {
                    DaoFactory<UserDao> daoFactory = new DaoFactory<UserDao>();
                    final UserDao userDao = daoFactory.create(AuthActivity.this, UserDao.class);

                    userDao.createOrUpdate(user);
                    openMainActivity();

                } catch (SQLException e){ }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) { }
        }, Long.class);

        queue.add(request);
        queue.start();
    }

    private void openMainActivity() {
        progressDialog.dismiss();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
