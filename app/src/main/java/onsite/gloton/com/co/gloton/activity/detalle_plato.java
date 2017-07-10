package onsite.gloton.com.co.gloton.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import onsite.gloton.com.co.gloton.R;
import onsite.gloton.com.co.gloton.entity.Caracteristicas_Plato;
import onsite.gloton.com.co.gloton.entity.Categoria;
import onsite.gloton.com.co.gloton.entity.Plato;
import onsite.gloton.com.co.gloton.entity.Restaurant;

public class detalle_plato extends AppCompatActivity {

    TextView nomPlato,descri,ingre,precio;
    ImageView imgPlato, rest;
    Intent datos;
    Bundle extras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_plato);

        nomPlato = (TextView) findViewById(R.id.txtDetPlatNombre);
        descri  = (TextView) findViewById(R.id.txtDetPlaDesc);
        ingre = (TextView) findViewById(R.id.txtDetPlaIngre);
        precio = (TextView) findViewById(R.id.txtDetPlaPrecio);

        imgPlato = (ImageView) findViewById(R.id.imgDetPlatImagenPlat);
        rest = (ImageView) findViewById(R.id.imgDetPlatImagenRest);

        datos = getIntent();
        extras = datos.getExtras();
        long caracPlato;
        caracPlato = (long) extras.get("caracPlato");



        Caracteristicas_Plato caract = Caracteristicas_Plato.findById(Caracteristicas_Plato.class,caracPlato);

        Plato plato = caract.getPlato();
        Restaurant restaurant = caract.getRestaurante();

        nomPlato.setText(plato.getNombre());
        descri.setText(caract.getDescripcion());
        ingre.setText(caract.getIngredientes());
        precio.setText(String.valueOf(caract.getPrecio()));

        imgPlato.setImageResource(Integer.parseInt(plato.getImagen()));
        Picasso.with(this).load(restaurant.getLogo()).into(rest);
    }
}
