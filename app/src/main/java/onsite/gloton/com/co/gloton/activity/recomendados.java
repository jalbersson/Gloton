package onsite.gloton.com.co.gloton.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import onsite.gloton.com.co.gloton.R;
import onsite.gloton.com.co.gloton.adapter.RecomendadosAdapter;
import onsite.gloton.com.co.gloton.entity.Calificacion;

public class recomendados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendados);

        //// parte de codigo para poner el icono y la letra en el actionbar
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        LayoutInflater inflator = LayoutInflater.from(this);
        View v = inflator.inflate(R.layout.template_title_actionbar,null);
        getSupportActionBar().setCustomView(v);
        ////fin codigo poner icono y letra en el actionbar

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
