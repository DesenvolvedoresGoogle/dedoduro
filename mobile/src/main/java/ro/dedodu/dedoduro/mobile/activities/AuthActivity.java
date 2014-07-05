package ro.dedodu.dedoduro.mobile.activities;

import android.app.Activity;
import android.app.ProgressDialog;

import android.content.Context;
import android.content.Intent;

import android.content.IntentSender;
import android.os.Bundle;

import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.plus.PlusClient;

import ro.dedodu.dedoduro.mobile.R;
import roboguice.inject.InjectView;


public class AuthActivity extends RoboSherlockActivity implements View.OnClickListener, ConnectionCallbacks, OnConnectionFailedListener {

    @InjectView(R.id.sign_out_button)
    Button signOutBtn;

    private static final String TAG = "ExampleActivity";
    private static final int REQUEST_CODE_RESOLVE_ERR = 9000;

    private ProgressDialog mConnectionProgressDialog;
    private PlusClient mPlusClient;
    private ConnectionResult mConnectionResult;

    private final Activity myActivity = this.getParent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mPlusClient = new PlusClient.Builder(this, this, this)
                .setActions(
                    "http://schemas.google.com/AddActivity", "http://schemas.google.com/BuyActivity")
                .setScopes("PLUS_LOGIN")
                // Space separated list of scopes
                //.setVisibleActivities("http://schemas.google.com/AddActivity", "http://schemas.google.com/BuyActivity")
                .build();
        // A barra de progresso deve ser exibida se a falha de conexão não for resolvida.
        mConnectionProgressDialog = new ProgressDialog(this);
        mConnectionProgressDialog.setMessage("Signing in...");


        findViewById(R.id.sign_in_button).setOnClickListener(this);

        signOutBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (mPlusClient.isConnected()) {
                    mPlusClient.clearDefaultAccount();
                    mPlusClient.disconnect();
                    mPlusClient.connect();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mPlusClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPlusClient.disconnect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (mConnectionProgressDialog.isShowing()) {
            // O usuário já clicou no botão de login. Iniciar resolução
            // dos erros de conexão. Aguardar até que o onConnected() dispense a
            // caixa de diálogo de conexão.
            if (result.hasResolution()) {
                try {
                    result.startResolutionForResult(this, REQUEST_CODE_RESOLVE_ERR);
                } catch (IntentSender.SendIntentException e) {
                    mPlusClient.connect();
                }
            }
        }
        // Salvar a intenção para que possamos iniciar uma atividade quando o usuário clicar
        // no botão de login.
        mConnectionResult = result;
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        if (requestCode == REQUEST_CODE_RESOLVE_ERR && responseCode == RESULT_OK) {
            mConnectionResult = null;
            mPlusClient.connect();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        String accountName = mPlusClient.getAccountName();
        Toast.makeText(this, accountName + " is connected.", Toast.LENGTH_LONG).show();
        // Resolvemos todos os erros de conexão.
        mConnectionProgressDialog.dismiss();
    }

    @Override
    public void onDisconnected() {
        Log.d(TAG, "disconnected");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.sign_in_button && !mPlusClient.isConnected()) {
            if (mConnectionResult == null) {
                mConnectionProgressDialog.show();
            } else {
                try {
                    mConnectionResult.startResolutionForResult(this, REQUEST_CODE_RESOLVE_ERR);
                } catch (IntentSender.SendIntentException e) {
                    // Tente se conectar novamente.
                    mConnectionResult = null;
                    mPlusClient.connect();
                }
            }
        }
    }

}
