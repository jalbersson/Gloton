package onsite.gloton.com.co.gloton.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import onsite.gloton.com.co.gloton.R;
import onsite.gloton.com.co.gloton.adapter.DetailAdapter;
import onsite.gloton.com.co.gloton.entity.Gallery;

public class DetailActivity extends AppCompatActivity {

    private DetailAdapter detailAdapter;
    private ListView listView;
    private TextView textViewTitle;
    private ArrayList<Gallery> listGallery;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String foodTitle = "";
        int optionSelected = 0;
        if (getIntent().getExtras() != null) {
            foodTitle = getIntent().getExtras().getString("titleFood");
            optionSelected = getIntent().getExtras().getInt("optionSelected");
        }
        textViewTitle = (TextView) findViewById(R.id.txtTitle);
        textViewTitle.setText(foodTitle);
        showOptionsMenuFood(optionSelected);
        listView = (ListView) findViewById(R.id.listView);
        detailAdapter = new DetailAdapter(this,listGallery);
        listView.setAdapter(detailAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(DetailActivity.this,RestaurantList.class);
                intent.putExtra("plato",listGallery.get(position).getName());
                startActivity(intent);
            }
        });

    }

    public void showOptionsMenuFood(int optionSelected) {
        switch (optionSelected) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                settingDummyDataFastFood();
                break;
            case 4:
                settingDummyDataMexican();
                break;
            case 5:
                configureDataSuggestions();
                break;
            default:
                break;
        }
    }

    private ArrayList<Gallery> settingDummyDataMexican() {
        listGallery = new ArrayList<>();
        listGallery.add(new Gallery("Enchiladas",R.drawable.enchiladaa));
        listGallery.add(new Gallery("Tacos",R.drawable.tacos));
        listGallery.add(new Gallery("Burritos",R.drawable.burritos));
        return listGallery;
    }

    private ArrayList<Gallery> settingDummyDataFastFood() {
        listGallery = new ArrayList<>();
        listGallery.add(new Gallery("Hot Dog Hawaiano",R.drawable.hotdog));
        listGallery.add(new Gallery("hamburguesa",R.drawable.hamburguesa));
        listGallery.add(new Gallery("Pizza",R.drawable.pizza));
        return listGallery;
    }

    public void configureDataSuggestions() {
        listGallery = new ArrayList<>();
        listGallery.add(new Gallery("Super Hot Dog",R.drawable.superhotdog));
        listGallery.add(new Gallery("Combo hambuerguesa + Papas y Gaseosa",R.drawable.hamburguesacombo));
        listGallery.add(new Gallery("Pinchos",R.drawable.pinchos));
        listGallery.add(new Gallery("Lomo de cerdo",R.drawable.lomocerdo));
        listGallery.add(new Gallery("Arroz chino",R.drawable.arrozchino));
    }
}
