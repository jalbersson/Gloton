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
import onsite.gloton.com.co.gloton.entity.Categoria;

public class DetailActivity extends AppCompatActivity {

    private DetailAdapter detailAdapter;
    private ListView listView;
    private TextView textViewTitle;
    private ArrayList<Categoria> listCategoria;
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
        detailAdapter = new DetailAdapter(this, listCategoria);
        listView.setAdapter(detailAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(DetailActivity.this,RestaurantList.class);
                intent.putExtra("plato", listCategoria.get(position).getName());
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

    private ArrayList<Categoria> settingDummyDataMexican() {
        listCategoria = new ArrayList<>();
        listCategoria.add(new Categoria("Enchiladas",R.drawable.enchiladaa,1));
        listCategoria.add(new Categoria("Tacos",R.drawable.tacos,1));
        listCategoria.add(new Categoria("Burritos",R.drawable.burritos,1));
        return listCategoria;
    }

    private ArrayList<Categoria> settingDummyDataFastFood() {
        listCategoria = new ArrayList<>();
        listCategoria.add(new Categoria("Hot Dog Hawaiano",R.drawable.hotdog,1));
        listCategoria.add(new Categoria("hamburguesa",R.drawable.hamburguesa,1));
        listCategoria.add(new Categoria("Pizza",R.drawable.pizza,1));
        return listCategoria;
    }

    public void configureDataSuggestions() {
        listCategoria = new ArrayList<>();
        listCategoria.add(new Categoria("Super Hot Dog",R.drawable.superhotdog,1));
        listCategoria.add(new Categoria("Combo hambuerguesa + Papas y Gaseosa",R.drawable.hamburguesacombo,1));
        listCategoria.add(new Categoria("Pinchos",R.drawable.pinchos,1));
        listCategoria.add(new Categoria("Lomo de cerdo",R.drawable.lomocerdo,1));
        listCategoria.add(new Categoria("Arroz chino",R.drawable.arrozchino,1));
    }
}
