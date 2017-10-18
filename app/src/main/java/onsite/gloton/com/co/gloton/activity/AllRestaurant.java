package onsite.gloton.com.co.gloton.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;

import com.google.android.gms.maps.model.LatLng;

import java.util.Collections;
import java.util.List;

import onsite.gloton.com.co.gloton.R;
import onsite.gloton.com.co.gloton.adapter.AllRestaurantAdapter;
import onsite.gloton.com.co.gloton.entity.Restaurant;
import onsite.gloton.com.co.gloton.location.GPSTracker;

public class AllRestaurant extends AppCompatActivity {

    private List<Restaurant> listrestaurantesall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //// parte de codigo para poner el icono y la letra en el actionbar
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        LayoutInflater inflator = LayoutInflater.from(this);
        View v = inflator.inflate(R.layout.template_title_actionbar,null);
        getSupportActionBar().setCustomView(v);

        //listener para ir a home
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllRestaurant.this, GalleryActivity.class);
                startActivity(intent);
            }
        });
        ////fin codigo poner icono y letra en el actionbar


        setContentView(R.layout.activity_all_restaurant);
        listrestaurantesall = ordenarPorCercania();

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerviewrestall);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new AllRestaurantAdapter(this, listrestaurantesall));
    }

    public List<Restaurant> ordenarPorCercania() {
        List<Restaurant> desorden = Restaurant.listAll(Restaurant.class);
        for (Restaurant rest : desorden) {
            float distancia = 0;
            Location location = requestLocation();
            if (location != null)
            {
                Location destino = new Location("");
                destino.setLatitude(rest.getLatitud());
                destino.setLongitude(rest.getLongitud());
                distancia = location.distanceTo(destino);
            }
            rest.setDistancia(distancia);
            rest.save();
        }
        Collections.sort(desorden);

        return desorden;
    }


    public Location requestLocation() {
        GPSTracker mGPS = new GPSTracker(this);
        if (mGPS.canGetLocation()) {
            return mGPS.getLocation();
        } else {
            return null;
        }
    }

}
