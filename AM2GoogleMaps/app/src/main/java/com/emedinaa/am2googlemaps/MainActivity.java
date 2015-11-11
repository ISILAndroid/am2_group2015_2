package com.emedinaa.am2googlemaps;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity  implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG="MainActivity";
    private static final int ZOOM =15;

    private GoogleMap map;
    private Marker marker;
    private boolean selected=false;
    private double userLat=0;
    private double userLng=0;

    private double defaultLat= -12.046363;
    private double defaultLng= -77.042052;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMap();
        buildGoogleApiClient();
    }

    private void initMap() {

        try {
            if (map == null)
            {
                // above API 11
                map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

                map.getUiSettings().setAllGesturesEnabled(true);
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                map.setMyLocationEnabled(true);
                map.getUiSettings().setZoomControlsEnabled(false);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(defaultLat, defaultLng), ZOOM));

                map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng point)
                    {
                        selected=true;
                        if (marker != null) {
                            marker.remove();
                        }

                        marker = map.addMarker(new MarkerOptions()
                                .position(point)
                                .title("Mi ubicación")
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                        marker.showInfoWindow();
                        userLat=point.latitude;
                        userLng = point.longitude;
                        Toast.makeText(MainActivity.this,"Lat & Lng "+userLat+" "+userLng,Toast.LENGTH_LONG).show();
                    }
                });

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null)
        {
            userLat=mLastLocation.getLatitude();
            userLng=mLastLocation.getLongitude();
            userMarker(userLat, userLng);
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(userLat,userLng), ZOOM));
        }
    }

    private void userMarker(double lat, double lng)
    {
        if (marker != null) {
            marker.remove();
        }
        marker = map.addMarker(new MarkerOptions()
                .position(new LatLng(lat, lng))
                .title("Mi Ubicación")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
