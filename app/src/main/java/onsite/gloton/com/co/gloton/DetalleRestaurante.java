package onsite.gloton.com.co.gloton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

//import onsite.gloton.com.co.gloton.activity.MapaActivity;

public class DetalleRestaurante extends AppCompatActivity {

    ImageView ubicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_restaurante);
        ubicacion = (ImageView) findViewById(R.id.icoUbicacion);
        ubicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(DetalleRestaurante.this, MapaActivity.class);
                startActivity(intent);*/
            }
        });
    }
}
