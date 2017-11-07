package onsite.gloton.com.co.gloton.activity;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import onsite.gloton.com.co.gloton.R;
import onsite.gloton.com.co.gloton.adapter.RestaurantAdapter;
import onsite.gloton.com.co.gloton.entity.CaracteristicasPlato;
import onsite.gloton.com.co.gloton.entity.Plato;
import onsite.gloton.com.co.gloton.entity.Restaurant;
import onsite.gloton.com.co.gloton.location.GPSTracker;

public class RestaurantList extends AppCompatActivity {


    private List<CaracteristicasPlato> listaCaracteristicasPlatoQ;
    private List<CaracteristicasPlato> listaCarac;
    Plato plat;


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
        TextView btnRestaurant = (TextView) findViewById(R.id.txtTitle);
        ImageView imagenTitulo = (ImageView) findViewById(R.id.imgTitleCateg);
        String nombreplato = "";
        int resourceImageCat;

        if (getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            nombreplato = extras.getString("plato");
            resourceImageCat = extras.getInt("categoriaImage");
            btnRestaurant.setText(nombreplato);
            imagenTitulo.setImageResource(resourceImageCat);
        }

        List<Restaurant> ordenados;
        List<Plato> listPla = Plato.listAll(Plato.class);
        for (Plato pla : listPla) {
            if (pla.getNombre().equalsIgnoreCase(nombreplato)) {
                plat = pla;
                break;
            }
        }

        if (nombreplato.equals("Restaurantes"))
        {
            listaCaracteristicasPlatoQ = null;
        }
        else
        {
            listaCaracteristicasPlatoQ = Cargar(nombreplato);
        }

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ordenados = ordenarPorCercania(listaCaracteristicasPlatoQ);
        recyclerView.setAdapter(new RestaurantAdapter(this, ordenados));

    }

    public List<Restaurant> ordenarPorCercania(List<CaracteristicasPlato> desordenada) {
        List<Restaurant> desorden = new LinkedList<>();
        Restaurant restaurant;
        Location location = requestLocation();

        if (desordenada != null)
        {
            for (CaracteristicasPlato carac : desordenada)
            {
                restaurant = carac.getRestaurante();

                float distancia = 0;
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
        }
        else {
            desorden = Restaurant.listAll(Restaurant.class);
            Restaurant.saveInTx(desorden);
            for (Restaurant restaurante : desorden)
            {
                float distancia = 0;
                if (location != null)
                {
                    Location destino = new Location("");
                    destino.setLatitude(restaurante.getLatitud());
                    destino.setLongitude(restaurante.getLongitud());
                    distancia = location.distanceTo(destino);
                }
                restaurante.setDistancia(distancia);
                Restaurant.saveInTx(desorden);
            }
        }

        //Limpiar la lista de elementos repetidos:
        List<Restaurant> listaLimpia = new ArrayList();

        Map<Integer, Restaurant> mapPersonas = new HashMap<Integer, Restaurant>(desorden.size());
        for(Restaurant p : desorden) {
            mapPersonas.put(Integer.parseInt(p.getId().toString()), p);
        }
        for(Map.Entry<Integer, Restaurant> p : mapPersonas.entrySet()) {
            listaLimpia.add(p.getValue());
        }
        Collections.sort(listaLimpia);

        return listaLimpia;
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


    public List<CaracteristicasPlato> Cargar(String nombreplato) {

        listaCaracteristicasPlatoQ = CaracteristicasPlato.listAll(CaracteristicasPlato.class);
        if(plat!=null){
            listaCarac = new ArrayList<>();
            for(CaracteristicasPlato crcplato : listaCaracteristicasPlatoQ){
                if(crcplato.getPlato().getId().equals(plat.getId())){
                    listaCarac.add(crcplato);
                }
            }

        }
        return listaCarac;

    }
}
