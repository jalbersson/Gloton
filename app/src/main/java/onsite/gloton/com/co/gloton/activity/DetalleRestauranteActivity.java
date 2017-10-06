package onsite.gloton.com.co.gloton.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import onsite.gloton.com.co.gloton.R;
import onsite.gloton.com.co.gloton.adapter.MenuDetalleRestauranteAdapter;
import onsite.gloton.com.co.gloton.entity.Caracteristicas_Plato;
import onsite.gloton.com.co.gloton.entity.Categoria;
import onsite.gloton.com.co.gloton.entity.Restaurant;

//import onsite.gloton.com.co.gloton.activity.MapaActivity;

public class DetalleRestauranteActivity extends AppCompatActivity {

    ImageView ubicacion, logo;
    TextView direccion, telefono;
    ListView menu;
    Intent datos;
    Bundle extras;
    List<Caracteristicas_Plato> platos;
    MenuDetalleRestauranteAdapter adapter;

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


        setContentView(R.layout.activity_detalle_restaurante);

        Caracteristicas_Plato car = new Caracteristicas_Plato();
        datos = getIntent();
        extras = datos.getExtras();
        //Log.d("categoriasize", String.valueOf(Categoria.listAll(Categoria.class).size()));
        long codigoRestaurante;
        codigoRestaurante = (long) extras.get("codigoRestaurante");

        Log.d("***********************",String.valueOf(codigoRestaurante)+"*******************************");
        //codigoRestaurante = 7;      //

        final Restaurant rest = Restaurant.findById(Restaurant.class, codigoRestaurante);

        ubicacion = (ImageView) findViewById(R.id.icoUbicacion);
        ubicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetalleRestauranteActivity.this, MapsActivity.class);
                intent.putExtra("latitud",Double.valueOf(rest.getLatitud()));
                intent.putExtra("longitud",Double.valueOf(rest.getLongitud()));
                intent.putExtra("restaurante",rest.getId());
                startActivity(intent);
            }
        });


        //nit = (TextView) findViewById(R.id.txtDetResNit);
        direccion = (TextView) findViewById(R.id.txtDetResDir);
        telefono = (TextView) findViewById(R.id.txtDetResTel);
        logo = (ImageView) findViewById(R.id.imgDetRestLogo);

        menu = (ListView) findViewById(R.id.lstDesResMenu);


        //nit.setText(String.valueOf(rest.getNit()));
        direccion.setText(rest.getDireccion());
        telefono.setText(rest.getTelefono());
        Picasso.with(this).load(rest.getLogo()).into(logo);

        platos = car.ListarMenu(rest);

        adapter = new MenuDetalleRestauranteAdapter(this, platos);

        menu.setAdapter(adapter);

        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DetalleRestauranteActivity.this,detalle_plato.class);
                intent.putExtra("caracPlato",platos.get(position).getId());
                startActivity(intent);
            }
        });

    }
}
