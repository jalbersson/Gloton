package onsite.gloton.com.co.gloton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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
                //Intent intent = new Intent(Splash.this, GalleryActivity.class);
                Intent intent = new Intent(Splash.this, GalleryActivity.class);
                Intent intent2 = new Intent();
                intent2.setAction("onsite.gloton.com.co.firebasebroad");
                sendBroadcast(intent2);
                //intent.putExtra("idRestaurante",7);
                startActivity(intent);
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(tarea,3000);

    }

    public void cargarDatos()
    {

        final List<Categoria> categorias = new ArrayList<>();
        final List<Restaurant> listaRestaurantes = new ArrayList<>();
        final List<Plato> listaPlatos = new ArrayList<>();
        final List<Caracteristicas_Plato> listaCaracteristicas = new ArrayList<>();

        //insersión de las categorías

        if (Categoria.listAll(Categoria.class).size() == 0)
        {
            categorias.add(new Categoria("Comida China",R.drawable.food_chinesse,1));
            categorias.add(new Categoria("Postres",R.drawable.postres,1));
            categorias.add(new Categoria("Parrilla",R.drawable.parrilla,1));
            categorias.add(new Categoria("Comidas rápidas",R.drawable.food_fast,1));
            categorias.add(new Categoria("Comida mexicana",R.drawable.food_mexican,1));
            categorias.add(new Categoria("Comida Italiana",R.drawable.food_italian,1));
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




            float distancia = 0;
            listaRestaurantes.add(new Restaurant(1, "AKI MAKI","Carrera 7 # 13N35", "301 7284838","" +
                    "https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/11401475_937996492910936_8715022944863399614_n.jpg?oh=7fffed40a8fc7ef22b7d4fbd598944fb&oe=5A59B192" +
                    "", "1", 2.45226, -76.60082,distancia));

            listaRestaurantes.add(new Restaurant(9, "China Min Wah","Calle 68A N", "23244","" +
                    "http://anfitrion.com.mx/wp-content/uploads/2017/04/Restaurant-Min-Wah-Logo.jpg" +
                    "", "1", 2.481354, -76.565012, distancia));

            listaRestaurantes.add(new Restaurant(10, "Mey Chow Norte","Carrera 9 78", "+5728246060","" +
                    "https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/11147022_128586060812090_1833302662633474475_n.jpg?oh=982327562f009a477ba27dcfe179fb76&oe=5A286CD2" +
                    "", "1", 2.490493, -76.559423, distancia));

            listaRestaurantes.add(new Restaurant(2, "Mapanca", "Carrera 11 70", "23244", "https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/1794799_303176843170314_7682520423875561195_n.jpg?oh=15eb92c1c8c63427695cedf86d75c4a2&oe=5A2896C0" +
                    "", "1", 2.48387, -76.564031, distancia));

            listaRestaurantes.add(new Restaurant(3, "Chingón Bar and Mexican Food", "Calle 20 Norte 6", "8335338", "https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/15621797_1824066307877765_6771394614470824194_n.jpg?oh=9f9f97f9c9445c4bd6d6c6b74c5caa08&oe=5A128E03" +
                    "", "1", 2.453629, -76.594135, distancia));

            listaRestaurantes.add(new Restaurant(4, "Mrchilon", "Carrera 9", "23244", url4, "1", 2.481551, -76.564666, distancia));

            listaRestaurantes.add(new Restaurant(5, "Ciudad Blanca Restaurante - Bar", "Calle 72A Norte 9" +
                    "", "+573146495533", url5, "1", 2.483709, -76.562419, distancia));

            listaRestaurantes.add(new Restaurant(6, "Cort Pizza", "Calle 71 Norte 13", "+573116380607", "" +
                    "https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/14224759_1809699155933211_4294108971835015611_n.jpg?oh=be49a4c4896327afc69c22969cabdb48&oe=5A23E787" +
                    "", "1", 2.485826, -76.564971, distancia));

            listaRestaurantes.add(new Restaurant(7, "Pedro Parrilla", "Carrera 10 #14 Norte-1 a 14 Norte-137", "23244" +
                    "", url7, "1", 2.4582293f, -76.5960889f, distancia));

            listaRestaurantes.add(new Restaurant(13, "Maderos Parrilla", "Calle 70 Norte 6", "+3235259170", "" +
                    "https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/14224705_1077043299069942_7303976102058111738_n.png?oh=bda90b585d04ff913ac8fda139ba2c55&oe=5A5A4426" +
                    "", "1", 2.476689,  -76.560772, distancia));

            listaRestaurantes.add(new Restaurant(8, "Super Olafo", "Calle 71A Norte 7B", "+573175168649", "https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/14717304_1814740288803481_1366031938565540257_n.jpg?oh=304f64eae7333499ca42794771787e84&oe=5A1CF972" +
                    "", "1",2.47893, -76.560601, distancia));

            listaRestaurantes.add(new Restaurant(11, "Mr. Arroz", "Carrera 6", "+5728204744", "https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/14333128_1775321216070549_1927778484743364999_n.jpg?oh=f620ffc6727432b4ff4478a13ac48858&oe=5A5A19B6" +
                    "", "1", 2.453081, -76.594403, distancia));

            listaRestaurantes.add(new Restaurant(12, "La Valenciana", "Carrera 5A 72A", "23244", "http://www.mercerialavalenciana.com/comunes/imagenes/logo3.jpg" +
                    "" , "1", 2.476848, -76.558155, distancia));
            Restaurant.saveInTx(listaRestaurantes);
        }

        //insersión de la lista de platos

        if (Plato.listAll(Plato.class).size() == 0)
        {
            Categoria aux = Categoria.find(Categoria.class, "name = ?", "Comida China").get(0);
            listaPlatos.add(new Plato("Arroz Chino", String.valueOf(R.drawable.food_chinesse),"activo",aux));
            listaPlatos.add(new Plato("Pollo Kung Pao", String.valueOf(R.drawable.pollo_kung_pao),"activo",aux));
            listaPlatos.add(new Plato("Chop Suey", String.valueOf(R.drawable.chop_suey),"activo",aux));
            listaPlatos.add(new Plato("Chow Mein", String.valueOf(R.drawable.chow_mein),"activo",aux));

            aux = Categoria.find(Categoria.class, "name = ?", "Postres").get(0);
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
                    listaCalificaciones.add(new Calificacion(a,"",punt,cat));
                    a++;
                }
            }

            Calificacion.saveInTx(listaCalificaciones);
        }


    }
}