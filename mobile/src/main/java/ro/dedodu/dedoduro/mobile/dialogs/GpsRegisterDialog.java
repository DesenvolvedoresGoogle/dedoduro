package ro.dedodu.dedoduro.mobile.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import ro.dedodu.dedoduro.mobile.R;
import ro.dedodu.dedoduro.mobile.http.HttpClient;
import ro.dedodu.dedoduro.mobile.http.JacksonJsonRequest;
import ro.dedodu.dedoduro.mobile.model.GpsRegister;

import static com.android.volley.Request.Method.POST;
import static com.android.volley.Response.Listener;
import static ro.dedodu.dedoduro.mobile.http.HttpClient.RequestURL.GPS_REGISTER;

public class GpsRegisterDialog extends DialogFragment {

    private GpsRegister gpsRegister;

    public GpsRegisterDialog(GpsRegister gpsRegister) {
        this.gpsRegister = gpsRegister;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return getCustomDialog();
    }

    private Dialog getCustomDialog() {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.gps_register_dialog, null);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder
                .setView(dialogView)
                .setTitle(R.string.register_activity)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton(R.string.send, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        fillGpsRegister(dialogView);
                        registerActivity();
                    }
                });

        return dialogBuilder.create();
    }

    private void fillGpsRegister(View dialogView) {
        EditText edtDescription = (EditText) dialogView.findViewById(R.id.edt_description);
        gpsRegister.setDescription(edtDescription.getText().toString());
    }

    private void registerActivity() {
        RequestQueue queue = HttpClient.getClient(getActivity()).getRequestQueue();

        JacksonJsonRequest<GpsRegister, Long> request =  new JacksonJsonRequest<GpsRegister, Long>(POST, GPS_REGISTER.value(), gpsRegister, new Listener<Long>() {
            @Override
            public void onResponse(Long response) {
                gpsRegister.setId(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }, Long.class);

        queue.add(request);
        queue.start();
    }
}
