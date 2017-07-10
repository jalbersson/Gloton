package onsite.gloton.com.co.gloton.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import onsite.gloton.com.co.gloton.R;
import onsite.gloton.com.co.gloton.adapter.DetailAdapter;
import onsite.gloton.com.co.gloton.entity.Categoria;
import onsite.gloton.com.co.gloton.entity.Plato;

public class DetailActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private DetailAdapter detailAdapter;
    private ListView listView;
    private TextView textViewTitle;
    private List<Plato> listPlato;
    private Intent intent;
    private long optionSelected;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String foodTitle = "";
        if (getIntent().getExtras() != null) {
            foodTitle = getIntent().getExtras().getString("titleFood");
            optionSelected = getIntent().getExtras().getLong("optionSelected");
        }
        listPlato = settingPlatos();
        textViewTitle = (TextView) findViewById(R.id.txtTitle);
        textViewTitle.setText(foodTitle);
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
        Categoria categoria = Categoria.findById(Categoria.class,optionSelected);
        List<Plato> listFinal = new ArrayList<>();
        List<Plato> listPlatoTmp = Plato.listAll(Plato.class);
        for (Plato plato:listPlatoTmp) {
            Log.d("categoria.getId()",String.valueOf(categoria.getId()));
            Log.d("plato.getId()",String.valueOf(plato.getCategoria().getId()));
            if(plato.getCategoria().getId().equals(categoria.getId())){
                listFinal.add(plato);
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
