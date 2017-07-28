package onsite.gloton.com.co.gloton.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import onsite.gloton.com.co.gloton.R;
import onsite.gloton.com.co.gloton.location.DirectionFinder;
import onsite.gloton.com.co.gloton.location.DirectionFinderListener;
import onsite.gloton.com.co.gloton.location.GPSTracker;
import onsite.gloton.com.co.gloton.location.Route;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, DirectionFinderListener {

    private GoogleMap mMap;
    private double latitud;
    private double longitud;

    private double latitudRestaurante;
    private double longitudRestaurante;

    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        if (getIntent().getExtras() != null) {
            latitudRestaurante = getIntent().getExtras().getDouble("latitud");
            longitudRestaurante = getIntent().getExtras().getDouble("longitud");
        }

        GPSTracker mGPS = new GPSTracker(this);
        if (mGPS.canGetLocation()) {
            mGPS.getLocation();
            latitud = mGPS.getLatitude();
            longitud = mGPS.getLongitude();
        } else {
            Log.d("Unable", "location");
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng myLocation = new LatLng(latitud, longitud);

        LatLng locationRestaurante = new LatLng(latitudRestaurante, longitudRestaurante);

        mMap.setMaxZoomPreference(20);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }

        sendRequest(myLocation,locationRestaurante);
    }


    private void sendRequest(LatLng origin, LatLng target) {

        try {
            new DirectionFinder(this, String.valueOf(origin.latitude + ","  + origin.longitude), String.valueOf(target.latitude + "," + target.longitude)).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onDirectionFinderStart() {

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline : polylinePaths) {
                polyline.remove();
            }
        }
    }

    @Override
    public void onDirectionFinderSuccess(List<Route> route) {
        polylinePaths = new ArrayList<>();
        destinationMarkers = new ArrayList<>();

        for (Route routeLocation : route) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(routeLocation.startLocation, 16));
            ((TextView) findViewById(R.id.txtDistance)).setText("Distancia: " + routeLocation.distance.text);
            ((TextView) findViewById(R.id.txtTime)).setText("Tiempo: " + routeLocation.duration.text);

            destinationMarkers.add(mMap.addMarker(new MarkerOptions()
                    .title(routeLocation.endAddress)
                    .position(routeLocation.endLocation)));

            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(Color.BLUE).
                    width(10);

            for (int i = 0; i < routeLocation.points.size(); i++)
                polylineOptions.add(routeLocation.points.get(i));

            polylinePaths.add(mMap.addPolyline(polylineOptions));
        }
    }


}
