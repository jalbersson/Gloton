package onsite.gloton.com.co.gloton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import onsite.gloton.com.co.gloton.adapter.MenuDetalleRestauranteAdapter;
import onsite.gloton.com.co.gloton.adapter.RecomendadosAdapter;
import onsite.gloton.com.co.gloton.adapter.RestaurantAdapter;
import onsite.gloton.com.co.gloton.entity.Calificacion;
import onsite.gloton.com.co.gloton.entity.Caracteristicas_Plato;

public class recomendados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendados);

        List<Calificacion> recos;

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lstRecomendados);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recos = obtenerRecomendados();
        recyclerView.setAdapter(new RecomendadosAdapter(recomendados.this, recos));
    }

    public List<Calificacion> obtenerRecomendados()
    {
        List<Calificacion> recos = new LinkedList<>();
        List<Calificacion> cals = Calificacion.listAll(Calificacion.class);
        for (Calificacion cal : cals)
        {
            if (cal.getUsuario().equals(""))
            {
                if (cal.getPuntuacion() >= 4)
                    recos.add(cal);
            }
        }
        Collections.sort(recos);
        return recos;
    }
}
