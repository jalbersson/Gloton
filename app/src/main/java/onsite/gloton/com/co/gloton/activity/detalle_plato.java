package onsite.gloton.com.co.gloton.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.provider.Settings.Secure;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import onsite.gloton.com.co.gloton.R;
import onsite.gloton.com.co.gloton.entity.Calificacion;
import onsite.gloton.com.co.gloton.entity.CaracteristicasPlato;
import onsite.gloton.com.co.gloton.entity.Plato;
import onsite.gloton.com.co.gloton.entity.Restaurant;

public class detalle_plato extends AppCompatActivity {

    TextView nomPlato,descri,ingre,precio;
    ImageView imgPlato;
    Intent datos;
    Bundle extras;
    RatingBar ratingBar;
    CaracteristicasPlato caract;
    Calificacion cal;
    String id;

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


        setContentView(R.layout.activity_detalle_plato);

        nomPlato = (TextView) findViewById(R.id.txtDetPlatNombre);
     //   descri  = (TextView) findViewById(R.id.txtDetPlaDesc);
        ingre = (TextView) findViewById(R.id.txtDetPlaIngre);
        precio = (TextView) findViewById(R.id.txtDetPlaPrecio);

        imgPlato = (ImageView) findViewById(R.id.imgDetPlatImagenPlat);

        ratingBar = (RatingBar) findViewById(R.id.ratPlato);

        datos = getIntent();
        extras = datos.getExtras();
        long caracPlato;
        caracPlato = (long) extras.get("caracPlato");
        cal = null;

        caract = CaracteristicasPlato.findById(CaracteristicasPlato.class,caracPlato);

        Plato plato = caract.getPlato();
        Restaurant restaurant = caract.getRestaurante();

        String platonombre = plato.getNombre();
        if (caract.getDescripcion() != null && !caract.getDescripcion().equals(""))
        {
            platonombre += " - "+caract.getDescripcion();
        }

        nomPlato.setText(platonombre);
        ingre.setText(caract.getIngredientes());
        precio.setText("Precio:"+String.valueOf(caract.getPrecio()));

        if (plato.getImagen() != null && !plato.getImagen().equals(""))
            Picasso.with(this).load(plato.getImagen()).into(imgPlato);


        /*
        if (plato.getImagen() != null)
        {
            if (!plato.getImagen().equals(""))
                imgPlato.setImageResource(Integer.parseInt(plato.getImagen()));
        }
/**/

        //ratingBar.setSaveEnabled(true);
        List<Calificacion> listcal = Calificacion.listAll(Calificacion.class);

        //id dispositivo android
        id = Secure.getString(getBaseContext().getContentResolver(),Secure.ANDROID_ID);

        Log.i("id carac act",String.valueOf(caract.getId()));

        for (Calificacion cali : listcal)
        {
            Log.i("id carac list",String.valueOf(cali.getCaracteristicas().getId()));

            if (cali.getCaracteristicas().getIdUniversal() == caract.getIdUniversal())
            {
                if (cali.getUsuario().equals(id))
                    cal = cali;
            }
        }

        if (cal == null)
        {
            cal = new Calificacion();
            cal.setCaracteristicas(caract);
            cal.setIdUniversalPlato(caract.getIdUniversal());
            cal.setPuntuacion(0);
            cal.setUsuario(id);
        }
        ratingBar.setProgress(cal.getPuntuacion());
        //ingre.setText(String.valueOf(cal.getPuntuacion()));
        ListennerRatingBar();

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(detalle_plato.this, GalleryActivity.class);
                startActivity(intent);
            }
        });

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
                if (cal.getId() == null)
                {
                    Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Calificación registrada", Toast.LENGTH_SHORT);
                    toast1.show();
                }
                else
                {
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Calificación modificada", Toast.LENGTH_SHORT);
                    toast1.show();
                }
                cal.save();
            }
        });
    }
}
