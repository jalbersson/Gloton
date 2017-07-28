package onsite.gloton.com.co.gloton.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        setContentView(R.layout.activity_all_restaurant);
        TextView titulorest = (TextView)findViewById(R.id.titulorest);
        listrestaurantesall = Restaurant.listAll(Restaurant.class);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerviewrestall);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new AllRestaurantAdapter(this,listrestaurantesall));
    }
}
