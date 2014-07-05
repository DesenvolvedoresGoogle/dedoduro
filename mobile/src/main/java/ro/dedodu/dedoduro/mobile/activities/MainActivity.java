package ro.dedodu.dedoduro.mobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ListView;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import java.sql.SQLException;
import java.util.List;

import ro.dedodu.dedoduro.mobile.R;
import ro.dedodu.dedoduro.mobile.adapter.CategoryAdapter;
import ro.dedodu.dedoduro.mobile.dao.CategoryDao;
import ro.dedodu.dedoduro.mobile.dao.DaoFactory;
import ro.dedodu.dedoduro.mobile.model.Category;
import ro.dedodu.dedoduro.mobile.services.PullingService;
import roboguice.inject.InjectView;

public class MainActivity extends RoboSherlockActivity {

    @InjectView(R.id.left_drawer)
    private ListView listView;

    @InjectView(R.id.drawer_layout)
    private DrawerLayout drawerLayout;

    private GoogleMap map;
    private ActionBarDrawerToggle drawerToggle;
    private CategoryDao categoryDao;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaoFactory<CategoryDao> daoFactory = new DaoFactory<CategoryDao>();
        categoryDao = daoFactory.create(this, CategoryDao.class);

        Intent service = new Intent(this, PullingService.class);
        startService(service);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        initializeDrawerMenu();
        fillCategoryList();
        initializeMap();
    }

    private void initializeDrawerMenu() {
        drawerToggle =  new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);
    }

    private void fillCategoryList() {
        try {
            List<Category> categories = categoryDao.queryForAll();
            CategoryAdapter adapter = new CategoryAdapter(this, categories);
            listView.setAdapter(adapter);
        } catch (SQLException e) {}
    }

    private void initializeMap() {
        MapFragment fragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        if (fragment != null) {
            map = fragment.getMap();
            map.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onPostCreate(Bundle savedInstanceStates) {
        super.onPostCreate(savedInstanceStates);
        drawerToggle.syncState();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        drawerLayout.isDrawerOpen(listView);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            if (drawerLayout.isDrawerOpen(listView)) {
                drawerLayout.closeDrawer(listView);
            } else {
                drawerLayout.openDrawer(listView);
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
