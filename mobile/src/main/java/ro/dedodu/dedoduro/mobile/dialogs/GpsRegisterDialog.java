package ro.dedodu.dedoduro.mobile.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.maps.model.LatLng;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ro.dedodu.dedoduro.mobile.R;
import ro.dedodu.dedoduro.mobile.dao.CategoryDao;
import ro.dedodu.dedoduro.mobile.dao.DaoFactory;
import ro.dedodu.dedoduro.mobile.model.Category;
import ro.dedodu.dedoduro.mobile.model.GpsRegister;
import ro.dedodu.dedoduro.mobile.model.User;

public class GpsRegisterDialog extends DialogFragment {

    private LatLng latLng;
    private CategoryDao categoryDao;

    private GpsRegister gpsRegister;

    public GpsRegisterDialog(LatLng latLng) {
        this.latLng = latLng;
    }

    @Override
    public Dialog onCreateDialog(Bundle savadInstanceState) {
        initializeCategoryDao();
        return getCustomDialog();
    }

    private AlertDialog getCustomDialog() {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_gps_register, null);

        makeCategoryLayout(dialogView);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder
                .setTitle(R.string.category_dialog_title)
                .setView(dialogView);

        return dialogBuilder.create();
    }

    private void makeCategoryLayout(View dialogView) {
        for (Category category : getCategories()) {

        }
    }

    private List<Category> getCategories() {
        List<Category> categories = new ArrayList<Category>();
        try {
            categories = categoryDao.queryForAll();
        } catch (SQLException ignored) {}
        return categories;
    }

    private void initializeCategoryDao() {
        DaoFactory<CategoryDao> daoDaoFactory =  new DaoFactory<CategoryDao>();
        categoryDao = daoDaoFactory.create(getActivity(), CategoryDao.class);
    }

    private void createGpsRegister(Category category) {
        User user = new User();
        user.setId(1l);

        gpsRegister = new GpsRegister();
        gpsRegister.setUser(user);
        gpsRegister.setCategory(category);
        gpsRegister.setLat(latLng.latitude);
        gpsRegister.setLng(latLng.longitude);
    }
}
