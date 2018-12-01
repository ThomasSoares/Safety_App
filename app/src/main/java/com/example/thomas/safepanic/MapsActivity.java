package com.example.thomas.safepanic;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LocalStorage retrieve=new LocalStorage(getApplicationContext());
        double longtitude= Double.parseDouble(retrieve.getStorage("longtitude"));
        double latitude= Double.parseDouble(retrieve.getStorage("latitude"));

        // Add a marker in Sydney and move the camera
        LatLng yourLocation = new LatLng(longtitude, latitude);
        mMap.addMarker(new MarkerOptions().position(yourLocation).title("Your location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(yourLocation, 14.2f));

        LatLng help1 = new LatLng(18.5245031, 73.8247716);
        mMap.addMarker(new MarkerOptions().position(help1).title("Ujwal Singhania").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        LatLng help2 = new LatLng(18.5272873, 73.8424033);
        mMap.addMarker(new MarkerOptions().position(help2).title("Suravarpu Gautam").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        LatLng help3 = new LatLng(18.5287757, 73.8294494);
        mMap.addMarker(new MarkerOptions().position(help3).title("Grishma Thapa").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        Location startPoint=new Location("locationA");
        startPoint.setLatitude(latitude);
        startPoint.setLongitude(longtitude);

        Location endPoint=new Location("locationA");
        endPoint.setLatitude(73.8294494);
        endPoint.setLongitude(18.5287757);

        double distance=startPoint.distanceTo(endPoint);
        Toast.makeText(getApplicationContext(),String.valueOf(distance),Toast.LENGTH_LONG).show();
    }
}
