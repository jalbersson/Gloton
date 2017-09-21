package onsite.gloton.com.co.gloton.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;
import onsite.gloton.com.co.gloton.R;
import onsite.gloton.com.co.gloton.adapter.CoverFlowAdapter;
import onsite.gloton.com.co.gloton.entity.Categoria;

public class GalleryActivity extends AppCompatActivity {


    private FeatureCoverFlow coverFlow;
    private CoverFlowAdapter adapter;
    private List<Categoria> categoria;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);


        coverFlow = (FeatureCoverFlow) findViewById(R.id.coverflow);

        categoria = Categoria.listAll(Categoria.class);
        Log.d("categoriaSize",String.valueOf(categoria.size()));
        if (categoria.size() >0)
        {
            adapter = new CoverFlowAdapter(categoria,this);
            coverFlow.setAdapter(adapter);
            coverFlow.setOnScrollPositionListener(onScrollListener());
        }


        //// parte de codigo para poner el icono y la letra en el actionbar
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        LayoutInflater inflator = LayoutInflater.from(this);
        View v = inflator.inflate(R.layout.template_title_actionbar,null);
        getSupportActionBar().setCustomView(v);
        ////fin codigo poner icono y letra en el actionbar



    }

    private FeatureCoverFlow.OnScrollPositionListener onScrollListener() {
        return new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                Log.v("MainActiivty", "position: " + position);
            }

            @Override
            public void onScrolling() {
                Log.i("MainActivity", "scrolling");
            }
        };
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.suggestions_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.suggestion:
                Intent intent = new Intent(this, DetailActivity.class);
                intent.putExtra("titleFood","Recomendados");
                intent.putExtra("optionSelected",5);

                startActivity(intent);
                return true;
            case R.id.allrestaurantes:
                Intent intent1 = new Intent(this, AllRestaurant.class);
                //intent.putExtra("titleFood","Recomendados");
                //intent.putExtra("optionSelected",5);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
