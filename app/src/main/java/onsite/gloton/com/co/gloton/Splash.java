package onsite.gloton.com.co.gloton;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import onsite.gloton.com.co.gloton.activity.DetalleRestauranteActivity;
import onsite.gloton.com.co.gloton.activity.GalleryActivity;
import onsite.gloton.com.co.gloton.entity.Calificacion;
import onsite.gloton.com.co.gloton.entity.Caracteristicas_Plato;
import onsite.gloton.com.co.gloton.entity.Categoria;
import onsite.gloton.com.co.gloton.entity.Plato;
import onsite.gloton.com.co.gloton.entity.Restaurant;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        cargarDatos();

        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
    //            Intent intent = new Intent(Splash.this, GalleryActivity.class);
                Intent intent = new Intent(Splash.this, GalleryActivity.class);
                startActivity(intent);
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(tarea,3000);

    }

    public void cargarDatos()
    {

        //insersión de las categorías

        if (Categoria.listAll(Categoria.class).size() == 0)
        {
            List<Categoria> categorias = new ArrayList<>();;
            categorias.add(new Categoria("Comida China",R.drawable.arrozchino,1));
            categorias.add(new Categoria("Arroz especial",R.drawable.arrozturco,1));
            categorias.add(new Categoria("Parrilla",R.drawable.azadosalcarbon,1));
            categorias.add(new Categoria("Comidas rápidas",R.drawable.comidarapida,1));
            categorias.add(new Categoria("Comida mexicana",R.drawable.mexicana,1));
            Categoria.saveInTx(categorias);
        }

        //insersión de la lista de restaurantes

        if (Restaurant.listAll(Restaurant.class).size() == 0)
        {
            String url1 = "http://2.bp.blogspot.com/-4AfwZbNkgUs/TZ5HeXyU1YI/AAAAAAAAAEg/vgddSArgGXQ/s1600/final%2Bazteca%2B1%2Bpro.jpg";
            String url2 = "https://cdn3.f-cdn.com/contestentries/46029/2473637/52819d98de441_thumb900.jpg";
            String url3 = "https://s3-sa-east-1.amazonaws.com/projetos-artes/fullsize%2F2011%2F06%2F09%2F01%2FWDL-Logo-4691_16653_035959023_63473633.jpg";
            String url4 = "http://logospararestaurantes.com/wp-content/uploads/2015/05/mr-chilon.jpg";
            String url5 = "http://www.shoppinginformer.com/images/Retailerlogos/McDonalds-logo.png";
            String url6 = "https://static-s.aa-cdn.net/img/ios/925838466/75e615a4d974b5b6e4a57926d9123a0b?v=1";
            String url7 = "https://lh5.googleusercontent.com/-9cJxhvzsOZM/U1vUzW8MIiI/AAAAAAAAAHY/9mYrobEvC8E/w991-h336/logo%2BPedro%2BParrilla%2Bsin%2Bfondo.PNG";
            String url8 = "http://www.chipichape.com.co/new/wp-content/uploads/2015/04/logo_s_qbano.jpg.PNG";


            List<Restaurant> listaRestaurantes = new ArrayList<>();
            listaRestaurantes.add(new Restaurant(1, "Tao Cheng","calle falsa 123", "23244",url1, "1", -2.3f, 2.3f));
            listaRestaurantes.add(new Restaurant(9, "Chau Mein","calle falsa 123", "23244",url1, "1", -2.3f, 2.3f));
            listaRestaurantes.add(new Restaurant(10, "Feng Huang","calle falsa 123", "23244",url1, "1", -2.3f, 2.3f));
            listaRestaurantes.add(new Restaurant(2, "Casa mexicana", "calle falsa 123", "23244", url2, "1", -2.3f, 2.3f));
            listaRestaurantes.add(new Restaurant(3, "La Frontera", "calle falsa 123", "23244", url3, "1", -2.3f, 2.3f));
            listaRestaurantes.add(new Restaurant(4, "Mrchilon", "calle falsa 123", "23244", url4, "1", -2.3f, 2.3f));
            listaRestaurantes.add(new Restaurant(5, "Mcdonlas", "calle falsa 123", "23244", url5, "1", -2.3f, 2.3f));
            listaRestaurantes.add(new Restaurant(6, "Burguer King", "calle falsa 123", "23244", url6, "1", -2.3f, 2.3f));
            listaRestaurantes.add(new Restaurant(7, "Pedro Parrilla", "calle falsa 123", "23244", url7, "1", -2.3f, 2.3f));
            listaRestaurantes.add(new Restaurant(13, "La cosecha", "calle falsa 123", "23244", url7, "1", -2.3f, 2.3f));
            listaRestaurantes.add(new Restaurant(8, "Sandwich Cubano", "calle falsa 123", "23244", url8, "1", -2.3f, 2.3f));
            listaRestaurantes.add(new Restaurant(11, "Mr. Arroz", "calle falsa 123", "23244", url8, "1", -2.3f, 2.3f));
            listaRestaurantes.add(new Restaurant(12, "La Valenciana", "calle falsa 123", "23244", url8, "1", -2.3f, 2.3f));
            Restaurant.saveInTx(listaRestaurantes);
        }

        //insersión de la lista de platos

        if (Plato.listAll(Plato.class).size() == 0)
        {
            List<Plato> listaPlatos = new ArrayList<>();
            Categoria aux = Categoria.find(Categoria.class, "name = ?", "Comida China").get(0);
            listaPlatos.add(new Plato("Arroz Chino", String.valueOf(R.drawable.arrozchino),"activo",aux));
            listaPlatos.add(new Plato("Pollo Kung Pao", String.valueOf(R.drawable.pollo_kung_pao),"activo",aux));
            listaPlatos.add(new Plato("Chop Suey", String.valueOf(R.drawable.chop_suey),"activo",aux));
            listaPlatos.add(new Plato("Chow Mein", String.valueOf(R.drawable.chow_mein),"activo",aux));

            aux = Categoria.find(Categoria.class, "name = ?", "Arroz especial").get(0);
            listaPlatos.add(new Plato("Arroz Ranchero", String.valueOf(R.drawable.ranchero),"activo",aux));
            listaPlatos.add(new Plato("Arroz con pollo", String.valueOf(R.drawable.pollo),"activo",aux));
            listaPlatos.add(new Plato("Arroz Paisa", String.valueOf(R.drawable.paisa),"activo",aux));

            aux = Categoria.find(Categoria.class, "name = ?", "Parrilla").get(0);
            listaPlatos.add(new Plato("Churrasco", String.valueOf(R.drawable.churrasco),"activo",aux));
            listaPlatos.add(new Plato("Costillas a la parrilla", String.valueOf(R.drawable.costilla),"activo",aux));
            listaPlatos.add(new Plato("Baby beef", String.valueOf(R.drawable.baby_beef),"activo",aux));

            aux = Categoria.find(Categoria.class, "name = ?", "Comidas rápidas").get(0);
            listaPlatos.add(new Plato("Hamburguesa", String.valueOf(R.drawable.hamburguesa),"activo",aux));
            listaPlatos.add(new Plato("Perro Caliente", String.valueOf(R.drawable.perros_calientes),"activo",aux));
            listaPlatos.add(new Plato("Emparedado", String.valueOf(R.drawable.emparedado),"activo",aux));

            aux = Categoria.find(Categoria.class, "name = ?", "Comida mexicana").get(0);
            listaPlatos.add(new Plato("Tacos", String.valueOf(R.drawable.tacos),"activo",aux));
            listaPlatos.add(new Plato("Burritos", String.valueOf(R.drawable.burritos),"activo",aux));
            listaPlatos.add(new Plato("Nachos", String.valueOf(R.drawable.nachos),"activo",aux));

            Plato.saveInTx(listaPlatos);
        }

        //insersión de la lista de las características de los platos

        if (Caracteristicas_Plato.listAll(Caracteristicas_Plato.class).size() == 0)
        {
            List<Caracteristicas_Plato> listaCaracteristicas = new ArrayList<>();

            //Restaurantes que venden comida china:
            Plato plato = Plato.find(Plato.class, "nombre = ?", "Arroz Chino").get(0);
            Restaurant restaurante = Restaurant.find(Restaurant.class, "nit = ?", "1").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa especial","Arroz chino tracicional",14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "9").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa agridulce","Arroz chino tracicional",14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "10").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa especial","Arroz chino tracicional",14000,"activo",restaurante,plato));

            plato = Plato.find(Plato.class, "nombre = ?", "Pollo Kung Pao").get(0);
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "10").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa especial","Arroz chino tracicional",14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "1").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa agridulce","Arroz chino tracicional",14000,"activo",restaurante,plato));


            plato = Plato.find(Plato.class, "nombre = ?", "Chop Suey").get(0);
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "1").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa especial","Arroz chino tracicional",14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "10").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa especial","Arroz chino tracicional",14000,"activo",restaurante,plato));

            plato = Plato.find(Plato.class, "nombre = ?", "Chow Mein").get(0);
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "9").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa agridulce","Arroz chino tracicional",14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "10").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa especial","Arroz chino tracicional",14000,"activo",restaurante,plato));

            //restaurantes que venden Arroz especial
            plato = Plato.find(Plato.class, "nombre = ?", "Arroz Ranchero").get(0);
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "11").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa agridulce","Arroz chino tracicional",14000,"activo",restaurante,plato));

            plato = Plato.find(Plato.class, "nombre = ?", "Arroz con pollo").get(0);
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "11").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa agridulce","Arroz chino tracicional",14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "12").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa especial","Arroz chino tracicional",14000,"activo",restaurante,plato));

            plato = Plato.find(Plato.class, "nombre = ?", "Arroz Paisa").get(0);
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "11").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa agridulce","Arroz chino tracicional",14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "12").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa especial","Arroz chino tracicional",14000,"activo",restaurante,plato));

            //restaurantes que venden parrilla
            plato = Plato.find(Plato.class, "nombre = ?", "Churrasco").get(0);
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "7").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa agridulce","Arroz chino tracicional",14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "13").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa especial","Arroz chino tracicional",14000,"activo",restaurante,plato));

            plato = Plato.find(Plato.class, "nombre = ?", "Costillas a la parrilla").get(0);
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "7").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa agridulce","Arroz chino tracicional",14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "13").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa especial","Arroz chino tracicional",14000,"activo",restaurante,plato));

            plato = Plato.find(Plato.class, "nombre = ?", "Baby beef").get(0);
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "13").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa agridulce","Arroz chino tracicional",14000,"activo",restaurante,plato));

            //restaurantes que venden comidas rápidas
            plato = Plato.find(Plato.class, "nombre = ?", "Hamburguesa").get(0);
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "5").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa agridulce","Arroz chino tracicional",14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "6").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa agridulce","Arroz chino tracicional",14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "7").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa agridulce","Arroz chino tracicional",14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "8").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa especial","Arroz chino tracicional",14000,"activo",restaurante,plato));

            plato = Plato.find(Plato.class, "nombre = ?", "Perro Caliente").get(0);
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "6").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa agridulce","Arroz chino tracicional",14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "7").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa agridulce","Arroz chino tracicional",14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "8").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa especial","Arroz chino tracicional",14000,"activo",restaurante,plato));

            plato = Plato.find(Plato.class, "nombre = ?", "Emparedado").get(0);
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "5").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa agridulce","Arroz chino tracicional",14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "6").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa agridulce","Arroz chino tracicional",14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "8").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa especial","Arroz chino tracicional",14000,"activo",restaurante,plato));

            //restaurantes que venden comida mexicana

            plato = Plato.find(Plato.class, "nombre = ?", "Tacos").get(0);
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "2").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa agridulce","Arroz chino tracicional",14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "4").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa especial","Arroz chino tracicional",14000,"activo",restaurante,plato));

            plato = Plato.find(Plato.class, "nombre = ?", "Burritos").get(0);
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "3").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa agridulce","Arroz chino tracicional",14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "4").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa especial","Arroz chino tracicional",14000,"activo",restaurante,plato));

            plato = Plato.find(Plato.class, "nombre = ?", "Nachos").get(0);
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "2").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa agridulce","Arroz chino tracicional",14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "3").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa agridulce","Arroz chino tracicional",14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "4").get(0);
            listaCaracteristicas.add(new Caracteristicas_Plato("carnes, vegetales, salsa especial","Arroz chino tracicional",14000,"activo",restaurante,plato));

            Caracteristicas_Plato.saveInTx(listaCaracteristicas);
        }

        //insersión de la lista de calificaciones

        if (Calificacion.listAll(Calificacion.class).size() == 0)
        {
            List<Calificacion> listaCalificaciones = new ArrayList<>();

            List<Caracteristicas_Plato> caracteristicas = Caracteristicas_Plato.listAll(Caracteristicas_Plato.class);
            int a = 0;
            for (Caracteristicas_Plato cat : caracteristicas)
            {
                Random rnd = new Random();
                int cant = (int)(rnd.nextDouble() * 10 + 0);
                for (int i = 0; i<cant; i++)
                {
                    int punt = (int)(rnd.nextDouble() * 5 + 1);
                    listaCalificaciones.add(new Calificacion(a,punt,cat));
                    a++;
                }
            }

            Calificacion.saveInTx(listaCalificaciones);
        }

    }
}
