package ro.dedodu.dedoduro.mobile.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.sql.SQLException;

import ro.dedodu.dedoduro.mobile.R;
import ro.dedodu.dedoduro.mobile.dao.DaoFactory;
import ro.dedodu.dedoduro.mobile.dao.UserDao;
import ro.dedodu.dedoduro.mobile.http.HttpClient;
import ro.dedodu.dedoduro.mobile.http.JacksonJsonRequest;
import ro.dedodu.dedoduro.mobile.model.GpsRegister;
import ro.dedodu.dedoduro.mobile.model.GpsRegisterComment;
import ro.dedodu.dedoduro.mobile.model.GpsRegisterRate;
import ro.dedodu.dedoduro.mobile.model.User;

import static com.android.volley.Request.Method.POST;
import static ro.dedodu.dedoduro.mobile.http.HttpClient.RequestURL.GPS_REGISTER_COMMENT;
import static ro.dedodu.dedoduro.mobile.http.HttpClient.RequestURL.GPS_REGISTER_RATE;

public class GpsRegisterInformationDialog extends DialogFragment {

    private GpsRegister gpsRegister;
    private Button btnAddKarma;
    private Button btnRemoveKarma;
    private UserDao userDao;

    public GpsRegisterInformationDialog(GpsRegister gpsRegister) {
        this.gpsRegister = gpsRegister;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createCustomDialog();
    }

    public Dialog createCustomDialog() {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.gps_register_information_dialog, null);

        TextView txtDescription = (TextView) dialogView.findViewById(R.id.txt_description);
        txtDescription.setText(gpsRegister.getDescription());

        btnAddKarma = (Button) dialogView.findViewById(R.id.btn_add_karma);
        btnAddKarma.setOnClickListener(new RegisterKarmaListener(gpsRegister, 1));
        btnRemoveKarma = (Button) dialogView.findViewById(R.id.btn_remove_karma);
        btnRemoveKarma.setOnClickListener(new RegisterKarmaListener(gpsRegister, -1));

        Button btnAddComment = (Button) dialogView.findViewById(R.id.btn_add_comment);
        btnAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText edtComment = new EditText(getActivity());
                edtComment.setHint("Comentario");

                AlertDialog.Builder commentDialog = new AlertDialog.Builder(getActivity());
                commentDialog.setTitle(R.string.lbl_add_commet);
                commentDialog.setView(edtComment);
                commentDialog.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GpsRegisterComment gpsRegisterComment = new GpsRegisterComment();
                        gpsRegisterComment.setGpsRegister(gpsRegister);
                        gpsRegisterComment.setUser(getUser());
                        gpsRegisterComment.setComment(edtComment.getText().toString());

                        JacksonJsonRequest<GpsRegisterComment, Long> request = new JacksonJsonRequest<GpsRegisterComment, Long>(POST, GPS_REGISTER_COMMENT.value(), gpsRegisterComment, new Response.Listener<Long>() {
                            @Override
                            public void onResponse(Long response) {

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }, Long.class);

                        RequestQueue queue = HttpClient.getClient(getActivity()).getRequestQueue();
                        queue.add(request);
                        queue.start();
                    }
                });

                commentDialog.create().show();
            }
        });

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle(gpsRegister.getCategory().getName())
                .setView(dialogView);

        return dialogBuilder.create();
    }

    private class RegisterKarmaListener implements View.OnClickListener {

        private GpsRegister gpsRegister;
        private int rate;

        public RegisterKarmaListener(GpsRegister gpsRegister, int rate) {
            this.gpsRegister = gpsRegister;
            this.rate = rate;
        }

        @Override
        public void onClick(final View v) {

            GpsRegisterRate gpsRegisterRate = new GpsRegisterRate();
            gpsRegisterRate.setGpsRegister(gpsRegister);
            gpsRegisterRate.setUser(getUser());
            gpsRegisterRate.setRate(rate);

            RequestQueue queue = HttpClient.getClient(getActivity()).getRequestQueue();
            JacksonJsonRequest<GpsRegisterRate, Long> request = new JacksonJsonRequest<GpsRegisterRate, Long>(POST, GPS_REGISTER_RATE.value(), gpsRegisterRate, new Response.Listener<Long>() {
                @Override
                public void onResponse(Long response) {
                    if (rate == 1) {
                        btnAddKarma.setBackgroundResource(R.drawable.like_on);
                        btnRemoveKarma.setBackgroundResource(R.drawable.unlike_off);
                    } else {
                        btnAddKarma.setBackgroundResource(R.drawable.like_off);
                        btnRemoveKarma.setBackgroundResource(R.drawable.unlike_on);
                    }
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

    private User getUser() {
        try {
            DaoFactory<UserDao> daoFactory = new DaoFactory<UserDao>();
            userDao = daoFactory.create(getActivity(), UserDao.class);

            return userDao.queryBuilder().queryForFirst();
        } catch (SQLException ignore) {

        }
        return null;
    }
}
