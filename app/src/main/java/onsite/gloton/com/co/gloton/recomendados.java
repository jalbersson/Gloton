package onsite.gloton.com.co.gloton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import onsite.gloton.com.co.gloton.entity.Caracteristicas_Plato;

public class recomendados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendados);

        List<Caracteristicas_Plato> recomendados;

        final RecyclerViewiew recyclerView = (RecyclerView) findViewById(R.id.lstRecomendados);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recomendados = obtenerRecomendados();
        recyclerView.setAdapter(new RestaurantAdapter(this, ordenados));
    }

    public List<Caracteristicas_Plato> obtenerRecomendados()
    {
        List<Caracteristicas_Plato> recos = null;

        return recos;
    }
}
