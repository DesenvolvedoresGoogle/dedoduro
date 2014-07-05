package ro.dedodu.dedoduro.mobile.activities;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import ro.dedodu.dedoduro.mobile.R;

public class MainActivity extends SherlockActivity {

    private GoogleMap map;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeMap();
    }

    private void initializeMap() {
        MapFragment fragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        if (fragment != null) {
            map = fragment.getMap();
            map.setMyLocationEnabled(true);
        }
    }
}
