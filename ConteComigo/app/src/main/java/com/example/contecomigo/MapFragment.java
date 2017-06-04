package com.example.contecomigo;


import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A fragment that launches other parts of the demo application.
 */
public class MapFragment extends Fragment {

    private static final String[] INITIAL_PERMS = {
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
    };

    private static final int INITIAL_REQUEST = 1337;

    MapView mMapView;
    private GoogleMap googleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        if (!canAccessLocation()) {
            requestPermissions(INITIAL_PERMS, INITIAL_REQUEST);
        }

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map
                LatLng salvador = new LatLng(-12.9539186, -38.4514766);

                BitmapDrawable bitmapdraw= (BitmapDrawable) getResources().getDrawable(R.drawable.logo);
                Bitmap b = bitmapdraw.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, 100, 100, false);

                // MarkerOptions markerOne = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.logo));
                MarkerOptions markerOne = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                markerOne.position(salvador);//.title("Marker Title").snippet("Marker Description");

                googleMap.addMarker(markerOne);

                LatLng institutoMat = new LatLng(-13.001414, -38.5073218);
                MarkerOptions markerTwo = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                markerTwo.position(institutoMat);//.title("Marker Title").snippet("Marker Description");
                googleMap.addMarker(markerTwo);

                LatLng barra = new LatLng(-13.0098166, -38.5326198);
                MarkerOptions markerThree = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                markerThree.position(barra);
                googleMap.addMarker(markerThree);

                LatLng itapua = new LatLng(-12.9569491, -38.3539674);
                MarkerOptions markerFour = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                markerFour.position(itapua);
                googleMap.addMarker(markerFour);

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(salvador).zoom(11).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        return rootView;
    }

    private boolean canAccessLocation() {
        return(hasPermission(android.Manifest.permission.ACCESS_FINE_LOCATION));
    }

    private boolean hasPermission(String perm) {
        return(PackageManager.PERMISSION_GRANTED ==  ContextCompat.checkSelfPermission(getActivity(), perm));
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}