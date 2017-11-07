package onsite.gloton.com.co.gloton.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import onsite.gloton.com.co.gloton.R;
import onsite.gloton.com.co.gloton.adapter.MenuDetalleRestauranteAdapter;
import onsite.gloton.com.co.gloton.entity.CaracteristicasPlato;
import onsite.gloton.com.co.gloton.entity.Restaurant;

//import onsite.gloton.com.co.gloton.activity.MapaActivity;

public class DetalleRestauranteActivity extends AppCompatActivity {

    ImageView ubicacion, logo;
    TextView direccion, telefono;
    private GridView gridViewMenu;
    Intent datos;
    Bundle extras;
    List<CaracteristicasPlato> platos;
    MenuDetalleRestauranteAdapter adapter;
    ImageView phone;
    private static final int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //// parte de codigo para poner el icono y la letra en el actionbar
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        LayoutInflater inflator = LayoutInflater.from(this);
        View v = inflator.inflate(R.layout.template_title_actionbar, null);
        getSupportActionBar().setCustomView(v);
        //listener para ir a home
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetalleRestauranteActivity.this, GalleryActivity.class);
                startActivity(intent);
            }
        });
        ////fin codigo poner icono y letra en el actionbar

        phone = (ImageView) findViewById(R.id.imgPhoneRest);

        setContentView(R.layout.activity_detalle_restaurante);

        CaracteristicasPlato car = new CaracteristicasPlato();
        datos = getIntent();
        extras = datos.getExtras();
        //Log.d("categoriasize", String.valueOf(Categoria.listAll(Categoria.class).size()));
        long codigoRestaurante;
        codigoRestaurante = (long) extras.get("codigoRestaurante");

        Log.d("***********************", String.valueOf(codigoRestaurante) + "*******************************");
        //codigoRestaurante = 7;      //

        final Restaurant rest = Restaurant.findById(Restaurant.class, codigoRestaurante);

        ubicacion = (ImageView) findViewById(R.id.icoUbicacion);
        ubicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetalleRestauranteActivity.this, MapsActivity.class);
                intent.putExtra("latitud", Double.valueOf(rest.getLatitud()));
                intent.putExtra("longitud", Double.valueOf(rest.getLongitud()));
                intent.putExtra("restaurante", rest.getId());
                startActivity(intent);
            }
        });


        //nit = (TextView) findViewById(R.id.txtDetResNit);
        direccion = (TextView) findViewById(R.id.txtDetResDir);
        telefono = (TextView) findViewById(R.id.txtDetResTel);
        logo = (ImageView) findViewById(R.id.imgDetRestLogo);

        gridViewMenu = (GridView) findViewById(R.id.gvMenu);


        //nit.setText(String.valueOf(rest.getNit()));
        direccion.setText(rest.getDireccion());
        telefono.setText(rest.getTelefono());
        if (rest.getLogo() != null && !rest.getLogo().equals(""))
            Picasso.with(this).load(rest.getLogo()).into(logo);

        platos = car.ListarMenu(rest);

        adapter = new MenuDetalleRestauranteAdapter(this, platos);

        gridViewMenu.setAdapter(adapter);

        gridViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DetalleRestauranteActivity.this, detalle_plato.class);
                intent.putExtra("caracPlato", platos.get(position).getId());
                startActivity(intent);
            }
        });

        telefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(DetalleRestauranteActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + rest.getTelefono()));
                    startActivity(intent);
                }
                else
                {
                    requestPermission();
                }
            }
        });

    }


    @TargetApi(Build.VERSION_CODES.M)
    private boolean addPermission(List<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            // Check for Rationale Option
            if (!shouldShowRequestPermissionRationale(permission))
                return false;
        }
        return true;
    }



    @TargetApi(Build.VERSION_CODES.M)
    private void requestPermission() {
        List<String> permissionsNeeded = new ArrayList<>();

        final List<String> permissionsList = new ArrayList<String>();

        if (!addPermission(permissionsList, Manifest.permission.CALL_PHONE)) {
            permissionsNeeded.add("CALL_PHONE");
        }

        if (permissionsList.size() > 0) {
            if (permissionsNeeded.size() > 0) {
                // Need Rationale
                String message = "You need to grant access to " + permissionsNeeded.get(0);
                Log.d("permisos ", message);
            }
            requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                    REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            return;
        }

    }
}
