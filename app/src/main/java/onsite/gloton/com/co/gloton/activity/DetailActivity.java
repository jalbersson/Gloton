package onsite.gloton.com.co.gloton.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import onsite.gloton.com.co.gloton.R;

import onsite.gloton.com.co.gloton.adapter.DetailAdapter;
import onsite.gloton.com.co.gloton.entity.Categoria;
import onsite.gloton.com.co.gloton.entity.Plato;

import static com.orm.SugarRecord.find;

public class DetailActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private DetailAdapter detailAdapter;
    private ListView listView;
    private Button textViewTitle;
    private ImageView imgTitleCateg;
    private ImageView imgFondo;
    private List<Plato> listPlato;
    private Intent intent;
    private long optionSelected;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //// parte de codigo para poner el icono y la letra en el actionbar
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        LayoutInflater inflator = LayoutInflater.from(this);
        View v = inflator.inflate(R.layout.template_title_actionbar,null);
        getSupportActionBar().setCustomView(v);

        //listener para ir a home
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, GalleryActivity.class);
                startActivity(intent);
            }
        });
        ////fin codigo poner icono y letra en el actionbar



        imgFondo = (ImageView) findViewById(R.id.imgFondo);
        imgTitleCateg = (ImageView)findViewById(R.id.imgTitleCateg);

        setContentView(R.layout.activity_detail);
        String foodTitle = "";
        if (getIntent().getExtras() != null) {
            foodTitle = getIntent().getExtras().getString("titleFood");
            optionSelected = getIntent().getExtras().getLong("optionSelected");
        }

        if (foodTitle.equals("Platos"))
        {
            imgFondo = (ImageView) findViewById(R.id.imgFondo);
            imgTitleCateg = (ImageView)findViewById(R.id.imgTitleCateg);
            textViewTitle = (Button) findViewById(R.id.txtTitle);
            listPlato = Plato.listAll(Plato.class);
            textViewTitle.setText("Glot-On");
            imgTitleCateg.setImageResource(R.drawable.splash4);
        }
        else
        {
            textViewTitle = (Button) findViewById(R.id.txtTitle);
            imgFondo = (ImageView) findViewById(R.id.imgFondo);
            imgTitleCateg = (ImageView)findViewById(R.id.imgTitleCateg);
            Categoria cat = Categoria.find(Categoria.class, "name = ?", foodTitle).get(0);
            listPlato = settingPlatos();

            textViewTitle.setText(foodTitle);
            imgTitleCateg.setImageResource(cat.getImageSource());

        }



        imgFondo.setMinimumHeight(textViewTitle.getHeight());

        //showOptionsMenuFood(optionSelected);
        listView = (ListView) findViewById(R.id.listView);
        detailAdapter = new DetailAdapter(this, listPlato);
        listView.setAdapter(detailAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(DetailActivity.this, RestaurantList.class);
                intent.putExtra("plato", listPlato.get(position).getNombre());
                startActivity(intent);
            }
        });

    }

    private List<Plato> settingPlatos() {
        Log.d("optionSelected",String.valueOf(optionSelected));
        Categoria categoria = null;
        List<Plato> listFinal = new ArrayList<>();
        if (optionSelected == 0) {
            categoria  = Categoria.findById(Categoria.class,3);
            List<Plato> listPlatoTmp = Plato.listAll(Plato.class);
            for (Plato plato:listPlatoTmp) {
                if(plato.getCategoria().getId().equals(categoria.getId())){
                    listFinal.add(plato);
                }
            }
        } else {
            categoria = Categoria.findById(Categoria.class,optionSelected);
            List<Plato> listPlatoTmp = Plato.listAll(Plato.class);
            for (Plato plato:listPlatoTmp) {
                if(plato.getCategoria().getId().equals(categoria.getId())){
                    listFinal.add(plato);
                }
            }

        }

        return listFinal;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu_detail, menu);
        SearchManager SManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchMenuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchMenuItem);
        searchView.setSearchableInfo(SManager
                .getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setIconified(true);
        searchView.setQuery("", false);
        searchView.setOnQueryTextListener(this);
        View searchPlate = searchView
                .findViewById(android.support.v7.appcompat.R.id.search_plate);
       // searchPlate.setBackgroundResource(R.drawable.search_view_selector);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d("newText",String.valueOf(newText));
        List<Plato> listSearchPlato = new ArrayList<>();
        for (Plato plato:listPlato) {
            if (plato.getNombre().toUpperCase().contains(newText.toUpperCase())) {
                listSearchPlato.add(plato);
            }
        }
        detailAdapter = new DetailAdapter(this, listSearchPlato);
        listView.setAdapter(detailAdapter);
        return false;
    }
}
