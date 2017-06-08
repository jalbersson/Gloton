package onsite.gloton.com.co.gloton.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import onsite.gloton.com.co.gloton.R;
import onsite.gloton.com.co.gloton.adapter.RestaurantAdapter;
import onsite.gloton.com.co.gloton.entity.Restaurant;

public class RestaurantList extends AppCompatActivity {

    ArrayList<Restaurant> restaurants;
    ListView list;
    RestaurantAdapter adapter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        list = (ListView) findViewById(R.id.lstRest);

        Cargar();

        adapter = new RestaurantAdapter(this,restaurants);

        list.setAdapter(adapter);
    }

    public void Cargar()
    {
        restaurants = new ArrayList<Restaurant>();
        restaurants.add(new Restaurant("Tequila's","Calle 5 # 9-25","345345","320 989 8276", "Juan Gonzalez",R.drawable.restaurantetequilas));
        restaurants.add(new Restaurant("Chile's","Carrera 14 # 7-40","345588","311 321 1212", "Bernardo Alvarez",R.drawable.restaurantechiles));
        restaurants.add(new Restaurant("Chingon's","Calle 9 # 8-05","125432","316 432 5476", "Camilo Gutierrez",R.drawable.restaurantechingon));
    }
}
