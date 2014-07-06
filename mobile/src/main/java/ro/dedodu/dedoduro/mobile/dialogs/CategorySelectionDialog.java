package ro.dedodu.dedoduro.mobile.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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

public class CategorySelectionDialog extends DialogFragment {

    private LatLng latLng;
    private CategoryDao categoryDao;

    private GpsRegister gpsRegister;

    public CategorySelectionDialog(LatLng latLng) {
        this.latLng = latLng;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initializeCategoryDao();
        return getCustomDialog();
    }

    private void initializeCategoryDao() {
        DaoFactory<CategoryDao> daoDaoFactory =  new DaoFactory<CategoryDao>();
        categoryDao = daoDaoFactory.create(getActivity(), CategoryDao.class);
    }

    private AlertDialog getCustomDialog() {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.category_selection_dialog, null);

        makeCategoryLayout(dialogView);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder
                .setTitle(R.string.category_dialog_title)
                .setView(dialogView);

        return dialogBuilder.create();
    }

    private void makeCategoryLayout(View dialogView) {
        List<Category> categories = getCategories();
        LinearLayout llCategories = (LinearLayout) dialogView.findViewById(R.id.ll_categories);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        layoutParams.setMargins(5,5,5,5);

        LinearLayout categoryRow = new LinearLayout(dialogView.getContext());
        categoryRow.setLayoutParams(layoutParams);

        for (int i = 0; i < categories.size(); i++) {
            final Category category = categories.get(i);
            ImageButton imageButton = new ImageButton(dialogView.getContext());
            imageButton.setImageResource(category.imageResource());

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openGpsRegisterDialog(category);
                }
            });

            categoryRow.addView(imageButton);

            if ((((i + 1) % 3 == 0) && i > 0) || ((i + 1) == categories.size()) ) {
                llCategories.addView(categoryRow);
                categoryRow = new LinearLayout(dialogView.getContext());
                categoryRow.setLayoutParams(layoutParams);
            }
        }
    }

    private List<Category> getCategories() {
        List<Category> categories = new ArrayList<Category>();
        try {
            categories = categoryDao.queryForAll();
        } catch (SQLException ignored) {}
        return categories;
    }

    private void openGpsRegisterDialog(final Category category) {
        this.dismiss();
        GpsRegisterDialog gpsRegisterDialog = new GpsRegisterDialog(createGpsRegister(category));
        gpsRegisterDialog.show(getFragmentManager(), null);
    }

    private GpsRegister createGpsRegister(Category category) {
        User user = new User();
        user.setId(1l);

        gpsRegister = new GpsRegister();
        gpsRegister.setUser(user);
        gpsRegister.setCategory(category);
        gpsRegister.setLat(latLng.latitude);
        gpsRegister.setLng(latLng.longitude);

        return gpsRegister;
    }
}
