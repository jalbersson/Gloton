package onsite.gloton.com.co.gloton.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;

import java.util.Collections;
import java.util.List;

import onsite.gloton.com.co.gloton.R;
import onsite.gloton.com.co.gloton.adapter.AllRestaurantAdapter;
import onsite.gloton.com.co.gloton.entity.Restaurant;

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
        ////fin codigo poner icono y letra en el actionbar


        setContentView(R.layout.activity_all_restaurant);
        TextView titulorest = (TextView) findViewById(R.id.titulorest);
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
            LocationManager loma = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            Location origen = loma.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (origen != null)
            {
                Location destino = new Location("");
                destino.setLatitude(rest.getLatitud());
                destino.setLongitude(rest.getLongitud());
                distancia = origen.distanceTo(destino);
            }
            rest.setDistancia(distancia);
            rest.save();
        }
        Collections.sort(desorden);

        return desorden;
    }

}
