package onsite.gloton.com.co.gloton.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;
import onsite.gloton.com.co.gloton.R;
import onsite.gloton.com.co.gloton.adapter.CoverFlowAdapter;
import onsite.gloton.com.co.gloton.entity.Gallery;

public class GalleryActivity extends AppCompatActivity {


    private FeatureCoverFlow coverFlow;
    private CoverFlowAdapter adapter;
    private ArrayList<Gallery> gallery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        coverFlow = (FeatureCoverFlow) findViewById(R.id.coverflow);

        settingDummyData();
        adapter = new CoverFlowAdapter(gallery,this);
        coverFlow.setAdapter(adapter);
        coverFlow.setOnScrollPositionListener(onScrollListener());
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

    private void settingDummyData() {
        gallery = new ArrayList<>();
        gallery.add(new Gallery("Arroz chino",R.drawable.arrozchino));
        gallery.add(new Gallery("Arroz turco",R.drawable.arrozturco));
        gallery.add(new Gallery("Azados al cárbon",R.drawable.azadosalcarbon));
        gallery.add(new Gallery("Comidas rápidas",R.drawable.comidarapida));
        gallery.add(new Gallery("Comida mexicana",R.drawable.mexicana));
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
