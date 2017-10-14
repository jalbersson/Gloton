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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import onsite.gloton.com.co.gloton.R;
import onsite.gloton.com.co.gloton.adapter.RestaurantAdapter;
import onsite.gloton.com.co.gloton.entity.Caracteristicas_Plato;
import onsite.gloton.com.co.gloton.entity.Categoria;
import onsite.gloton.com.co.gloton.entity.Plato;
import onsite.gloton.com.co.gloton.entity.Restaurant;
import onsite.gloton.com.co.gloton.location.GPSTracker;
import onsite.gloton.com.co.gloton.location.Route;

public class RestaurantList extends AppCompatActivity {


    private List<Categoria> listaCategorias;
    private List<Caracteristicas_Plato> listaCaracteristicasPlato;
    private List<Caracteristicas_Plato> listaCaracteristicasPlatoQ;
    private List<Caracteristicas_Plato> listaCarac;
    private List<Restaurant> listaRestaurantes;
    private List<Plato> listaPlatos;
    Plato plat=null;


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
                Intent intent = new Intent(RestaurantList.this, GalleryActivity.class);
                startActivity(intent);
            }
        });
        ////fin codigo poner icono y letra en el actionbar



        setContentView(R.layout.activity_restaurant_list);
        TextView titulorest = (TextView) findViewById(R.id.titulorest);
        ImageView imagenTitulo = (ImageView) findViewById(R.id.imgTitlePlato);
        String nombreplato;

        Bundle extras = getIntent().getExtras();
        nombreplato = extras.getString("plato");

        titulorest.setText(nombreplato);

        List<Restaurant> ordenados;
        List<Plato> listPla = Plato.listAll(Plato.class);
        for (Plato pla : listPla) {

            if (pla.getNombre().equalsIgnoreCase(nombreplato)) {
                plat = pla;
                Picasso.with(this).load(plat.getImagen()).into(imagenTitulo);
            }
        }

        listaCaracteristicasPlatoQ = Cargar(nombreplato);



        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ordenados = ordenarPorCercania(listaCaracteristicasPlatoQ);
        recyclerView.setAdapter(new RestaurantAdapter(this, ordenados));

    }

    public List<Restaurant> ordenarPorCercania(List<Caracteristicas_Plato> desordenada) {
        List<Restaurant> desorden = new LinkedList<>();
        Restaurant restaurant;
        Location location = requestLocation();

        for (Caracteristicas_Plato carac : desordenada)
        {
            restaurant = carac.getRestaurante();

            float distancia = 0;
            Log.d("location",String.valueOf(location));
            if (location != null)
            {
                Location destino = new Location("");
                destino.setLatitude(restaurant.getLatitud());
                destino.setLongitude(restaurant.getLongitud());
                distancia = location.distanceTo(destino);
            }
            restaurant.setDistancia(distancia);
            restaurant.save();

            desorden.add(restaurant);
        }
        Collections.sort(desorden);

        /* Location origen;
        GPSTracker tracker;
        Location destino = new Location("");
        Float distancia;
        Float menor;
        for (Caracteristicas_Plato carac : desordenada)
        {
            origen = tracker.getLocation();

            distancia = origen.distanceTo(destino);

        }
*/
        return desorden;
    }

    public Location requestLocation() {
        GPSTracker mGPS = new GPSTracker(this);
        if (mGPS.canGetLocation()) {
            return mGPS.getLocation();
        } else {
            Log.d("Unable", "location");
            return null;
        }
    }


    public List<Caracteristicas_Plato> Cargar(String nombreplato) {

        listaCaracteristicasPlatoQ = Caracteristicas_Plato.listAll(Caracteristicas_Plato.class);
        if(plat!=null){
            listaCarac = new ArrayList<>();
            for(Caracteristicas_Plato crcplato : listaCaracteristicasPlatoQ){
                if(crcplato.getPlato().getId().equals(plat.getId())){
                    listaCarac.add(crcplato);
                }
            }

        }
        return listaCarac;

    }
}
