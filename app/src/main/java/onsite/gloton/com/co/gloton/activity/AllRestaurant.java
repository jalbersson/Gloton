package onsite.gloton.com.co.gloton.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import onsite.gloton.com.co.gloton.R;
import onsite.gloton.com.co.gloton.adapter.AllRestaurantAdapter;
import onsite.gloton.com.co.gloton.entity.Restaurant;

public class AllRestaurant extends AppCompatActivity {

    private List<Restaurant> listrestaurantesall;


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


        setContentView(R.layout.activity_all_restaurant);
        TextView titulorest = (TextView)findViewById(R.id.titulorest);
        listrestaurantesall = Restaurant.listAll(Restaurant.class);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerviewrestall);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new AllRestaurantAdapter(this,listrestaurantesall));
    }
}
