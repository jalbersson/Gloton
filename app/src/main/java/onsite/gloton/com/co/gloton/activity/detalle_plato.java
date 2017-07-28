package onsite.gloton.com.co.gloton.activity;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.provider.Settings.Secure;

import com.squareup.picasso.Picasso;

import java.util.List;

import onsite.gloton.com.co.gloton.R;
import onsite.gloton.com.co.gloton.entity.Calificacion;
import onsite.gloton.com.co.gloton.entity.Caracteristicas_Plato;
import onsite.gloton.com.co.gloton.entity.Categoria;
import onsite.gloton.com.co.gloton.entity.Plato;
import onsite.gloton.com.co.gloton.entity.Restaurant;

public class detalle_plato extends AppCompatActivity {

    TextView nomPlato,descri,ingre,precio;
    ImageView imgPlato, rest;
    Intent datos;
    Bundle extras;
    RatingBar ratingBar;
    Caracteristicas_Plato caract;
    Calificacion cal;
    String id;

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

        ratingBar = (RatingBar) findViewById(R.id.ratPlato);

        datos = getIntent();
        extras = datos.getExtras();
        long caracPlato;
        caracPlato = (long) extras.get("caracPlato");
        cal = null;

        caract = Caracteristicas_Plato.findById(Caracteristicas_Plato.class,caracPlato);

        Plato plato = caract.getPlato();
        Restaurant restaurant = caract.getRestaurante();

        nomPlato.setText(plato.getNombre());
        descri.setText(caract.getDescripcion());
        ingre.setText(caract.getIngredientes());
        precio.setText(String.valueOf(caract.getPrecio()));

        imgPlato.setImageResource(Integer.parseInt(plato.getImagen()));
        Picasso.with(this).load(restaurant.getLogo()).into(rest);

        //ratingBar.setSaveEnabled(true);
        List<Calificacion> listcal = Calificacion.listAll(Calificacion.class);

        //id dispositivo android
        id = Secure.getString(getBaseContext().getContentResolver(),Secure.ANDROID_ID);

        for (Calificacion cali : listcal)
        {
            if (cali.getCaracteristicas().getId() == caract.getId())
            {
                if (cali.getUsuario().equals(id))
                    cal = cali;
            }
        }
        if (cal == null)
        {
            cal = new Calificacion();
            cal.setCaracteristicas(caract);
            cal.setPuntuacion(0);
            cal.setUsuario(id);
        }
        ratingBar.setProgress(cal.getPuntuacion());
        //ingre.setText(String.valueOf(cal.getPuntuacion()));
        ListennerRatingBar();
    }

    public void ListennerRatingBar()
    {

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                cal.setPuntuacion((int) rating);

                /*
                ingre = (TextView) findViewById(R.id.txtDetPlaIngre);
                List<Calificacion> cals = Calificacion.listAll(Calificacion.class);
                String calis = new String();
                for (Calificacion cali : cals)
                {
                    calis += ", "+String.valueOf(cali.getPuntuacion());
                }
                ingre.setText(calis);
                */
                cal.save();
            }
        });
    }
}
