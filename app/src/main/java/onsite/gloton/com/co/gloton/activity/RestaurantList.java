package onsite.gloton.com.co.gloton.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import onsite.gloton.com.co.gloton.R;
import onsite.gloton.com.co.gloton.adapter.RestaurantAdapter;
import onsite.gloton.com.co.gloton.entity.Caracteristicas_Plato;
import onsite.gloton.com.co.gloton.entity.Categoria;
import onsite.gloton.com.co.gloton.entity.Plato;
import onsite.gloton.com.co.gloton.entity.Restaurant;

public class RestaurantList extends AppCompatActivity {





    private List<Categoria> listaCategorias;
    private List<Caracteristicas_Plato> listaCaracteristicasPlato;
    private List<Caracteristicas_Plato> listaCaracteristicasPlatoQ;
    private List<Restaurant> listaRestaurantes;
    private List<Plato> listaPlatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        String nombreplato;

        Bundle extras = getIntent().getExtras();
        nombreplato = extras.getString("plato");

        insertarDBRestaurantes();

        listaCaracteristicasPlatoQ = Cargar(nombreplato);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RestaurantAdapter(this, listaCaracteristicasPlatoQ));

    }


    public List<Caracteristicas_Plato> Cargar(String nombreplato)
    {



        listaCaracteristicasPlatoQ = Caracteristicas_Plato.listAll(Caracteristicas_Plato.class);



        //listaCaracteristicasPlatoQ = Caracteristicas_Plato.find(Caracteristicas_Plato.class,"plato = ?",new String{plato.getNombre()});




        return listaCaracteristicasPlatoQ;

    }


    public void insertarDBRestaurantes() {


        listaCategorias = Categoria.listAll(Categoria.class);

        listaPlatos = new ArrayList<>();
        listaPlatos.add(new Plato("fdfdfd", "dfdfdfdfd", "dfdfdfdfd", listaCategorias.get(0)));
        listaPlatos.add(new Plato("fdfdfd", "dfdfdfdfd", "dfdfdfdfd", listaCategorias.get(1)));
        listaPlatos.add(new Plato("fdfdfd", "dfdfdfdfd", "dfdfdfdfd", listaCategorias.get(2)));
        listaPlatos.add(new Plato("fdfdfd", "dfdfdfdfd", "dfdfdfdfd", listaCategorias.get(3)));
        listaPlatos.add(new Plato("fdfdfd", "dfdfdfdfd", "dfdfdfdfd", listaCategorias.get(4)));
        Plato.saveInTx(listaPlatos);


        String url1 = "http://2.bp.blogspot.com/-4AfwZbNkgUs/TZ5HeXyU1YI/AAAAAAAAAEg/vgddSArgGXQ/s1600/final%2Bazteca%2B1%2Bpro.jpg";
        String url2 = "https://cdn3.f-cdn.com/contestentries/46029/2473637/52819d98de441_thumb900.jpg";
        String url3 = "https://s3-sa-east-1.amazonaws.com/projetos-artes/fullsize%2F2011%2F06%2F09%2F01%2FWDL-Logo-4691_16653_035959023_63473633.jpg";
        String url4 = "http://logospararestaurantes.com/wp-content/uploads/2015/05/mr-chilon.jpg";

        String url5 = "http://www.shoppinginformer.com/images/Retailerlogos/McDonalds-logo.png";
        String url6 = "https://static-s.aa-cdn.net/img/ios/925838466/75e615a4d974b5b6e4a57926d9123a0b?v=1";
        String url7 = "https://lh5.googleusercontent.com/-9cJxhvzsOZM/U1vUzW8MIiI/AAAAAAAAAHY/9mYrobEvC8E/w991-h336/logo%2BPedro%2BParrilla%2Bsin%2Bfondo.PNG";
        String url8 = "http://www.chipichape.com.co/new/wp-content/uploads/2015/04/logo_s_qbano.jpg.PNG";



        listaRestaurantes = new ArrayList<>();
        listaRestaurantes.add(new Restaurant(1,"Aztlan","calle falsa 123", "23244",url1, "1", -2.3f, 2.3f));
        listaRestaurantes.add(new Restaurant(2, "Casa mexicana", "calle falsa 123", "23244", url2, "1", -2.3f, 2.3f));
        listaRestaurantes.add(new Restaurant(3, "La Frontera", "calle falsa 123", "23244", url3, "1", -2.3f, 2.3f));
        listaRestaurantes.add(new Restaurant(4, "Mrchilon", "calle falsa 123", "23244", url4, "1", -2.3f, 2.3f));

        listaRestaurantes.add(new Restaurant(5, "Mcdonlas", "calle falsa 123", "23244", url5, "1", -2.3f, 2.3f));
        listaRestaurantes.add(new Restaurant(6, "Burguer King", "calle falsa 123", "23244", url6, "1", -2.3f, 2.3f));
        listaRestaurantes.add(new Restaurant(7, "Pedro Parrilla", "calle falsa 123", "23244", url7, "1", -2.3f, 2.3f));
        listaRestaurantes.add(new Restaurant(8, "Sandwich Cubano", "calle falsa 123", "23244", url8, "1", -2.3f, 2.3f));

        Restaurant.saveInTx(listaRestaurantes);


        listaCaracteristicasPlato = new ArrayList<>();





        listaCaracteristicasPlato.add(new Caracteristicas_Plato("fdsfdf", "dererere", 30, "dfdfdfd", listaRestaurantes.get(0), listaPlatos.get(0)));
        listaCaracteristicasPlato.add(new Caracteristicas_Plato("fdsfdf", "dererere", 45, "dfdfdfd", listaRestaurantes.get(1), listaPlatos.get(1)));
        listaCaracteristicasPlato.add(new Caracteristicas_Plato("fdsfdf", "dererere", 11, "dfdfdfd", listaRestaurantes.get(2), listaPlatos.get(2)));
        listaCaracteristicasPlato.add(new Caracteristicas_Plato("fdsfdf", "dererere", 65, "dfdfdfd", listaRestaurantes.get(3), listaPlatos.get(3)));
        listaCaracteristicasPlato.add(new Caracteristicas_Plato("fdsfdf", "dererere", 21, "dfdfdfd", listaRestaurantes.get(4), listaPlatos.get(4)));
        listaCaracteristicasPlato.add(new Caracteristicas_Plato("fdsfdf", "dererere", 56, "dfdfdfd", listaRestaurantes.get(5), listaPlatos.get(0)));
        listaCaracteristicasPlato.add(new Caracteristicas_Plato("fdsfdf", "dererere", 33, "dfdfdfd", listaRestaurantes.get(6), listaPlatos.get(1)));
        listaCaracteristicasPlato.add(new Caracteristicas_Plato("fdsfdf", "dererere", 27, "dfdfdfd", listaRestaurantes.get(7), listaPlatos.get(2)));

        Caracteristicas_Plato.saveInTx(listaCaracteristicasPlato);
    }

}
