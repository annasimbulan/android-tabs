package simbulan.androidtabs.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import simbulan.androidtabs.R;
import simbulan.androidtabs.model.Capital;
import simbulan.androidtabs.util.CapitalsData;

/**
 * A {@link Fragment} that displays a map view of the provincial / territorial capitals of Canada
 */
public class MapFragment extends Fragment {
    private final static String CURRENT_LOCATION_KEY = "CURRENT_LOCATION_KEY";
    private final static String ZOOM_LEVEL_KEY = "ZOOM_LEVEL_KEY";

    // This may be null if the Google Play services APK is not available
    private GoogleMap mMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return container == null ? null : inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LatLng position = null;
        Float zoomLevel = null;
        if (savedInstanceState != null) {
            position = savedInstanceState.getParcelable(CURRENT_LOCATION_KEY);
            zoomLevel = savedInstanceState.getFloat(ZOOM_LEVEL_KEY);
        }

        FragmentManager fm = getChildFragmentManager();
        SupportMapFragment fragment = (SupportMapFragment) fm.findFragmentById(R.id.location_map);
        if (fragment == null) {
            fragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.location_map, fragment).commit();
        }

        setUpMapIfNeeded(position, zoomLevel);
    }

    @Override
    public void onSaveInstanceState(Bundle outstate) {
        super.onSaveInstanceState(outstate);

        CameraPosition pos = mMap.getCameraPosition();
        outstate.putParcelable(CURRENT_LOCATION_KEY, pos.target);
        outstate.putFloat(ZOOM_LEVEL_KEY, pos.zoom);
    }

    /**
     * Sets up the map if it has not been previously done
     */
    public void setUpMapIfNeeded(LatLng position, Float zoomLevel) {
        if (mMap == null) {
            mMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.location_map)).getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null)
                setUpMap(position, zoomLevel);
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the
     * camera.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap}
     * is not null.
     */
    private void setUpMap(LatLng position, Float zoomLevel) {
        mMap.setMyLocationEnabled(true);
        addMarkersOfCapitals();

        // Ottawa coordinates
        double latitude = 45.424880;
        double longitude = -75.697260;

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(position == null ? new LatLng(latitude, longitude) : position)
                .zoom(zoomLevel == null ? 5F : zoomLevel)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private void addMarkersOfCapitals() {
        CapitalsData data = CapitalsData.getInstance();
        HashMap<String, List<Capital>> capitals = data.getCapitals();
        Iterator iterator = capitals.keySet().iterator();
        while (iterator.hasNext()) {
            String region = (String)iterator.next();
            List<Capital> capitalsInRegion = capitals.get(region);
            for (Capital c : capitalsInRegion) {
                mMap.addMarker(new MarkerOptions().position(c.getCoordinates()).title(c.getName()).snippet(c.getProvince()));
            }
        }
    }
}
