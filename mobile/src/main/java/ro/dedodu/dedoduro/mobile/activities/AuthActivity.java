package ro.dedodu.dedoduro.mobile.activities;

import android.app.Activity;
import android.app.ProgressDialog;

import android.content.Context;
import android.content.Intent;

import android.content.IntentSender;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.plus.PlusClient;

import java.io.IOException;

import ro.dedodu.dedoduro.mobile.R;


public class AuthActivity extends Activity implements
        ConnectionCallbacks, OnConnectionFailedListener, View.OnClickListener,
        PlusClient.OnAccessRevokedListener {

    private static final String TAG = "AuthActivity";
    private static final int OUR_REQUEST_CODE = 49404;
    private PlusClient plusClient;
    private boolean resolveOnFail;
    private ConnectionResult connectionResult;
    private ProgressDialog progressDialog;

    private TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        plusClient = new PlusClient.Builder(this, this, this)
                .setScopes(Scopes.PROFILE, Scopes.PLUS_LOGIN, Scopes.PLUS_ME)
                .build();

        resolveOnFail = false;

        Button signOutBtn = (Button) findViewById(R.id.sign_out_button);
        textView1 = (TextView) findViewById(R.id.textView);

        findViewById(R.id.sign_in_button).setOnClickListener(this);
        signOutBtn.setOnClickListener(this);
        findViewById(R.id.sign_out_button).setVisibility(View.INVISIBLE);
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
        textView1.setText(plusClient.getAccountName());
        resolveOnFail = false;
        progressDialog.dismiss();
        findViewById(R.id.sign_in_button).setVisibility(View.INVISIBLE);
        findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);

        final Context context = this.getApplicationContext();
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object... params) {
                String scope = "oauth2:" + Scopes.PLUS_LOGIN;
                try {
                    String token = GoogleAuthUtil.getToken(context,  plusClient.getAccountName(), scope);

                } catch (UserRecoverableAuthException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (GoogleAuthException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        task.execute((Void) null);
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
            case R.id.sign_out_button:
                if (plusClient.isConnected()) {
                    plusClient.clearDefaultAccount();

                    plusClient.revokeAccessAndDisconnect(new PlusClient.OnAccessRevokedListener() {
                        @Override
                        public void onAccessRevoked(ConnectionResult status) {
                        }
                    });

                    plusClient.disconnect();
                    plusClient.connect();

                    findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
                    findViewById(R.id.sign_out_button).setVisibility(View.INVISIBLE);

                    textView1.setText("");

                }
                break;
        }
    }

    @Override
    public void onAccessRevoked(ConnectionResult status) {
        plusClient.connect();

        findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
        findViewById(R.id.sign_out_button).setVisibility(View.INVISIBLE);
    }

    private void startResolution() {
        try {
            resolveOnFail = false;
            connectionResult.startResolutionForResult(this, OUR_REQUEST_CODE);
        } catch (IntentSender.SendIntentException e) {
            plusClient.connect();
        }
    }
}
