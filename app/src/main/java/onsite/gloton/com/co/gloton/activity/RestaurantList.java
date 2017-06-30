package onsite.gloton.com.co.gloton.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import onsite.gloton.com.co.gloton.R;
import onsite.gloton.com.co.gloton.adapter.RestaurantAdapter;
import onsite.gloton.com.co.gloton.entity.Caracteristicas_Plato;
import onsite.gloton.com.co.gloton.entity.Categoria;
import onsite.gloton.com.co.gloton.entity.Plato;
import onsite.gloton.com.co.gloton.entity.Restaurant;

public class RestaurantList extends AppCompatActivity {


    private List<Categoria> listaCategorias;
    private List<Caracteristicas_Plato> listaCaracteristicasPlato;
    private List<Caracteristicas_Plato> listaCaracteristicasPlatoQ;
    private List<Caracteristicas_Plato> listaCarac;
    private List<Restaurant> listaRestaurantes;
    private List<Plato> listaPlatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);
        TextView titulorest = (TextView)findViewById(R.id.titulorest);

        String nombreplato;

        Bundle extras = getIntent().getExtras();
        nombreplato = extras.getString("plato");

        titulorest.setText(nombreplato);



        listaCaracteristicasPlatoQ = Cargar(nombreplato);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RestaurantAdapter(this, listaCaracteristicasPlatoQ));

    }


    public List<Caracteristicas_Plato> Cargar(String nombreplato) {
        Plato plat=null;
        List<Plato> listPla = Plato.listAll(Plato.class);
        nombreplato = "Hamburguesa";
        Log.d(" NOMBREEEEEEE PLAATO ", nombreplato + listPla.size() + "\n\n");

        for (Plato pla : listPla) {
            //Log.d(" PLAATO ",pla.getNombre());

            if (pla.getNombre().equalsIgnoreCase(nombreplato)) {
                plat = pla;
                Log.d(" PLAATO ", plat.getEstado());
            }
        }
        Log.d(" NOMBREEEEEEE PLAATO ", "\n\n" + nombreplato + listPla.size());

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
