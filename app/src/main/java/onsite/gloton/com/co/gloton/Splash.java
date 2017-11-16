package onsite.gloton.com.co.gloton;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import onsite.gloton.com.co.gloton.activity.GalleryActivity;
import onsite.gloton.com.co.gloton.entity.Auxiliar;
import onsite.gloton.com.co.gloton.entity.Calificacion;
import onsite.gloton.com.co.gloton.entity.CaracteristicasPlato;
import onsite.gloton.com.co.gloton.entity.Categoria;
import onsite.gloton.com.co.gloton.entity.Plato;
import onsite.gloton.com.co.gloton.entity.Recomendados;
import onsite.gloton.com.co.gloton.entity.Restaurant;
import onsite.gloton.com.co.gloton.service.HttpAsyncTask;
import onsite.gloton.com.co.gloton.service.Response;

public class Splash extends AppCompatActivity implements HttpAsyncTask.OnHttpResponse {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Date fechaActualizacion = null;

        List<Auxiliar> auxiliars = Auxiliar.listAll(Auxiliar.class);
        if (auxiliars.size() > 0) {
            fechaActualizacion = auxiliars.get(auxiliars.size() - 1).getFechaProxima();
        }


        Log.i("fecha:  ", "fecha actualizacion: " + fechaActualizacion);

       cargarDatos(0);

//        cargarDatos();

        // carga de datos programada por una fecha en específico
/*
        if (fechaActualizacion != null) {
            if (fechaActualizacion.compareTo(Calendar.getInstance().getTime()) <= 0) {
                Log.i("servicio:  ", "Actualizando servicio");
                cargarDatos();
            }
        } else {
            Log.i("servicio:  ", "creando datos");
            cargarDatos();
        }
/**/

    }


    public void subirDatos() {
        try {
            //llenar JSON
            JSONArray jsonArray = new JSONArray();
            for (Calificacion cal : Calificacion.listAll(Calificacion.class)) {
                JSONObject j = new JSONObject();
                j.put("calificacion", cal.getPuntuacion());
                j.put("plato", cal.getIdUniversalPlato());
                jsonArray.put(j);
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("calificaciones", jsonArray);
            Log.i("calis",jsonObject.toString());
            HttpAsyncTask httpAsyncTask = new HttpAsyncTask(HttpAsyncTask.POST, this, this);
            httpAsyncTask.execute(getResources().getString(R.string.servicioWeb), jsonObject.toString());
    //       httpAsyncTask.execute("http://192.168.0.25:8080/GlotonPrimefaces/webresources/webservice", jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void cargarDatos() {
        HttpAsyncTask httpAsyncTask = new HttpAsyncTask(HttpAsyncTask.GET, this, this);
        //httpAsyncTask.execute("http://wmyserver.sytes.net:8080/GlotonPrimefaces/webresources/webservice");
        httpAsyncTask.execute(getResources().getString(R.string.servicioWeb));
        subirDatos();
    }


    @Override
    public void onResponse(Response response) {
        try {
        if (response.getMsg() != null && response.getMsg() != "")
        {
            CaracteristicasPlato.deleteAll(CaracteristicasPlato.class);
            Restaurant.deleteAll(Restaurant.class);
            Plato.deleteAll(Plato.class);
            Categoria.deleteAll(Categoria.class);
            Recomendados.deleteAll(Recomendados.class);

            //Log.i("json", response.getMsg());
            JSONObject jsonObject = new JSONObject(response.getMsg());
            final List<Categoria> categorias = new ArrayList<>();
            final List<Restaurant> listaRestaurantes = new ArrayList<>();
            final List<Plato> listaPlatos = new ArrayList<>();
            final List<CaracteristicasPlato> listaCaracteristicas = new ArrayList<>();
            final List<Recomendados> listaRecomendados = new ArrayList<>();

            Calendar calendar = Calendar.getInstance();
            Date hoy = calendar.getTime();
            calendar.add(calendar.DAY_OF_YEAR, 7);
            Date prox = calendar.getTime();


            String fileServerURL = "http://wmyserver.sytes.net:8080/GlotonPrimefaces/resources/imagenes/";

            Log.i("cantidad ", "size " + categorias.size());

            Auxiliar auxiliar = new Auxiliar();
            auxiliar.setFechaUltima(hoy);
            auxiliar.setFechaProxima(prox);
            auxiliar.save();

            JSONArray jsonArrayCategoria = jsonObject.getJSONArray("categoria");
            JSONArray jsonArrayRestaurante = jsonObject.getJSONArray("restaurante");
            JSONArray jsonArrayPlato = jsonObject.getJSONArray("plato");
            JSONArray jsonArrayCaracteristicasPlato = jsonObject.getJSONArray("caracteristicasPlato");
            JSONArray jsonArrayRecomendados = jsonObject.getJSONArray("recomendados");

//insersión de las categorías
            for (int i = 0; i < jsonArrayCategoria.length(); i++) {
                JSONObject j = jsonArrayCategoria.getJSONObject(i);
                Categoria categoria = new Categoria();
                categoria.setImageSource(fileServerURL+"categoria/"+j.get("imagen").toString());
                categoria.setName(j.get("nombre").toString());
                categorias.add(categoria);
            }

            Categoria.saveInTx(categorias);
            /*
            Categoria aux = Categoria.find(Categoria.class, "name = ?", "China").get(0);
            aux.setImageSource(R.drawable.food_chinesse);
            aux.save();
            aux = Categoria.find(Categoria.class, "name = ?", "Postres").get(0);
            aux.setImageSource(R.drawable.postres);
            aux.save();
            aux = Categoria.find(Categoria.class, "name = ?", "Parrilla").get(0);
            aux.setImageSource(R.drawable.parrilla);
            aux.save();
            aux = Categoria.find(Categoria.class, "name = ?", "Rapida").get(0);
            aux.setImageSource(R.drawable.food_fast);
            aux.save();
            aux = Categoria.find(Categoria.class, "name = ?", "Mexicana").get(0);
            aux.setImageSource(R.drawable.food_mexican);
            aux.save();
            aux = Categoria.find(Categoria.class, "name = ?", "Italiana").get(0);
            aux.setImageSource(R.drawable.food_italian);
            aux.save();
*/
        //    Log.i("categorias:", "" + categorias.size());
//insersión de las Platos
            for (int i = 0; i < jsonArrayPlato.length(); i++) {
                JSONObject j = jsonArrayPlato.getJSONObject(i);
                Categoria auxCate = Categoria.find(Categoria.class, "name = ?", j.get("categoria").toString()).get(0);
                Plato plato = new Plato();
                plato.setNombre(j.get("nombre").toString());
                plato.setImagen(fileServerURL+"plato/"+j.get("imagen").toString());
                plato.setCategoria(auxCate);
                listaPlatos.add(plato);
            }
            Plato.saveInTx(listaPlatos);

//insersión de las Restaurantes
            for (int i = 0; i < jsonArrayRestaurante.length(); i++) {
                JSONObject j = jsonArrayRestaurante.getJSONObject(i);
                Restaurant restaurante = new Restaurant();
                restaurante.setNit(Integer.parseInt(j.get("Nit").toString()));
                restaurante.setNombre(j.get("nombre").toString());
                restaurante.setDireccion(j.get("direccion").toString());
                restaurante.setTelefono(j.get("telefono").toString());
                restaurante.setLogo(fileServerURL+"restaurante/"+j.get("logo").toString());
                restaurante.setLatitud(Double.parseDouble(j.get("latitud").toString()));
                restaurante.setLongitud(Double.parseDouble(j.get("longitud").toString()));
                listaRestaurantes.add(restaurante);
            }
            Restaurant.saveInTx(listaRestaurantes);
//insersión de las CatacteristicasPlato
            for (int i = 0; i < jsonArrayCaracteristicasPlato.length(); i++) {
                JSONObject j = jsonArrayCaracteristicasPlato.getJSONObject(i);
                Plato plato = Plato.find(Plato.class, "nombre = ?", j.get("plato").toString()).get(0);
                Restaurant restaurante = Restaurant.find(Restaurant.class, "nit = ?", j.get("restaurante").toString()).get(0);

                CaracteristicasPlato caracteristicas = new CaracteristicasPlato();
                caracteristicas.setIngredientes(j.get("ingredientes").toString());
                caracteristicas.setDescripcion(j.get("descripcion").toString());
                caracteristicas.setPrecio(Integer.parseInt(j.get("precio").toString()));
                caracteristicas.setIdUniversal(Integer.parseInt(j.get("id").toString()));
                caracteristicas.setRestaurante(restaurante);
                caracteristicas.setPlato(plato);
                listaCaracteristicas.add(caracteristicas);
            }
            CaracteristicasPlato.saveInTx(listaCaracteristicas);

//insersión de los recomendados
            for (int i = 0; i < jsonArrayRecomendados.length(); i++) {
                JSONObject j = jsonArrayRecomendados.getJSONObject(i);
                CaracteristicasPlato caraux = null;
                List<CaracteristicasPlato> liscar = CaracteristicasPlato.listAll(CaracteristicasPlato.class);
                for (CaracteristicasPlato cara : liscar)
                {
                    if (cara.getIdUniversal() == Integer.parseInt(j.get("caracteristica").toString()))
                    {
                        caraux = cara;
                    }
                }
                if (caraux != null)
                {
                    Recomendados rec = new Recomendados();
                    rec.setCaracteristicas(caraux);
                    rec.setPuntuacion(Integer.parseInt(j.get("puntuacion").toString()));
                    listaRecomendados.add(rec);
                }
            }
            Recomendados.saveInTx(listaRecomendados);

            //Actualizar calificaciones a los nuevos platos
            for (Calificacion cal : Calificacion.listAll(Calificacion.class))
            {

                Log.i("calAct"," Calificacion "+cal.getPuntuacion()+" id "+cal.getIdUniversalPlato());
                CaracteristicasPlato caraux = null;
                List<CaracteristicasPlato> liscar = CaracteristicasPlato.listAll(CaracteristicasPlato.class);
                for (CaracteristicasPlato cara : liscar)
                {
                    if (cara.getIdUniversal() == cal.getIdUniversalPlato())
                    {
                        caraux = cara;
                    }
                }
                if (caraux != null)
                {
                    cal.setCaracteristicas(caraux);
                    cal.save();
                }
                else
                    cal.delete();
            }

        }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(Splash.this, GalleryActivity.class);
        startActivity(intent);
        finish();
    }

    public void cargarDatos( int a)
    {

        final List<Categoria> categorias = new ArrayList<>();
        final List<Restaurant> listaRestaurantes = new ArrayList<>();
        final List<Plato> listaPlatos = new ArrayList<>();
        final List<CaracteristicasPlato> listaCaracteristicas = new ArrayList<>();

        //insersión de las categorías

        if (Categoria.listAll(Categoria.class).size() == 0)
        {
            categorias.add(new Categoria("Comida China", ""+Uri.parse("android.resource://" + getPackageName() +"/"+R.drawable.food_chinesse),1));
            categorias.add(new Categoria("Postres",""+Uri.parse("android.resource://" + getPackageName() +"/"+R.drawable.postres),1));
            categorias.add(new Categoria("Parrilla",""+Uri.parse("android.resource://" + getPackageName() +"/"+R.drawable.parrilla),1));
            categorias.add(new Categoria("Comidas rápidas",""+Uri.parse("android.resource://" + getPackageName() +"/"+R.drawable.food_fast),1));
            categorias.add(new Categoria("Comida mexicana",""+Uri.parse("android.resource://" + getPackageName() +"/"+R.drawable.food_mexican),1));
            categorias.add(new Categoria("Comida Italiana",""+Uri.parse("android.resource://" + getPackageName() +"/"+R.drawable.food_italian),1));
          /* */ Categoria.saveInTx(categorias);
        }

        //insersión de la lista de restaurantes

        if (Restaurant.listAll(Restaurant.class).size() == 0)
        {
            float distancia = 0;
            listaRestaurantes.add(new Restaurant(1, "AKI MAKI","Carrera 7 # 13N35", "3017284838","" +
                    "https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/11401475_937996492910936_8715022944863399614_n.jpg?oh=7fffed40a8fc7ef22b7d4fbd598944fb&oe=5A59B192" +
                    "", "1", 2.452194, -76.600850,distancia));

            listaRestaurantes.add(new Restaurant(9, "Sr Wok","Centro comercial Campanario", "0328368090","" +
                    "http://www.ccviva.com/villavicencio/PublishingImages/Logos%20locales%20comerciales/sr-wok.jpg" +
                    "", "1", 2.460445, -76.594574, distancia));

            listaRestaurantes.add(new Restaurant(10, "Mey Chow Norte","Cra. 9 #79N -80", "0328246060","" +
                    "https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/11147022_128586060812090_1833302662633474475_n.jpg?oh=982327562f009a477ba27dcfe179fb76&oe=5A286CD2" +
                    "", "1", 2.491422, -76.559292, distancia));

            listaRestaurantes.add(new Restaurant(2, "Mapanca", " Cra. 11 #18 - 70", "0328372628", "https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/1794799_303176843170314_7682520423875561195_n.jpg?oh=15eb92c1c8c63427695cedf86d75c4a2&oe=5A2896C0" +
                    "", "1", 2.453515, -76.604879, distancia));

            listaRestaurantes.add(new Restaurant(3, "Chingón Bar and Mexican Food", "Cl. 20 Nte. #610", "0328335338", "https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/15621797_1824066307877765_6771394614470824194_n.jpg?oh=9f9f97f9c9445c4bd6d6c6b74c5caa08&oe=5A128E03" +
                    "", "1", 2.453397, -76.594173, distancia));

            listaRestaurantes.add(new Restaurant(4, "Celio's Restaurante", "Cl. 60 Nte. #9a20", "311 7591767", "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/13512037_895230653956394_4654221007584781545_n.jpg" +
                    "", "1", 2.483219, -76.578129, distancia));

            listaRestaurantes.add(new Restaurant(5, "Ciudad Blanca Restaurante - Bar", "Calle 72A Norte 9" +
                    "", "3146495533", "https://lh3.googleusercontent.com/p/AF1QipPJZQJ43A2S9LR9mTYd2l4Qb_qR_ezb-lFfLqz8=s1600-w400", "1", 2.483594, -76.562295, distancia));

            listaRestaurantes.add(new Restaurant(6, "Cort Pizza", "Calle 70b Norte #5a45", "3116380607", "" +
                    "https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/14224759_1809699155933211_4294108971835015611_n.jpg?oh=be49a4c4896327afc69c22969cabdb48&oe=5A23E787" +
                    "", "1", 2.476441, -76.559636, distancia));

            listaRestaurantes.add(new Restaurant(7, "Pedro Parrilla", "Carrera 10 #14 Norte-1 a 14 Norte-137", "0328230909" +
                    "", "https://lh5.googleusercontent.com/-9cJxhvzsOZM/U1vUzW8MIiI/AAAAAAAAAHY/9mYrobEvC8E/w991-h336/logo%2BPedro%2BParrilla%2Bsin%2Bfondo.PNG", "1", 2.453770, -76.600525, distancia));

            listaRestaurantes.add(new Restaurant(13, "Maderos Parrilla", " Cl. 70b Nte. #6-1", "3235259170", "" +
                    "https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/14224705_1077043299069942_7303976102058111738_n.png?oh=bda90b585d04ff913ac8fda139ba2c55&oe=5A5A4426" +
                    "", "1", 2.476872, -76.560024, distancia));

            listaRestaurantes.add(new Restaurant(8, "Super Olafo", "Carrera 8 # 10N - 37", "3175168649", "https://scontent.feoh2-1.fna.fbcdn.net/v/t1.0-9/14717304_1814740288803481_1366031938565540257_n.jpg?oh=304f64eae7333499ca42794771787e84&oe=5A1CF972" +
                    "", "1",2.478869, -76.560739, distancia));

            listaRestaurantes.add(new Restaurant(11, "San Carlo Pastelería Artesanal", "Cra. 9 Nte. #79-105", "3127784603", "https://i.pinimg.com/originals/48/0d/dc/480ddc5c8f3f392ce47d0cb061a4999a.jpg" +
                    "", "1", 2.491482, -76.559350, distancia));

            listaRestaurantes.add(new Restaurant(12, "Mi vaquita", "Cra. 9 Nte. #79-105", "0328339555", "https://img.pystatic.com/restaurants/mi-vaquita.jpg" +
                    "" , "1", 2.469482, -76.577619, distancia));
            listaRestaurantes.add(new Restaurant(11, "Il Gallo", "Cl. 35 Nte. #21-215", "304 6669014", "https://scontent-mia3-1.xx.fbcdn.net/v/t1.0-9/18198736_659698350884847_2314255150908576837_n.jpg" +
                    "", "1", 2.478723, -76.590720, distancia));


            Restaurant.saveInTx(listaRestaurantes);
        }

        //insersión de la lista de platos

        if (Plato.listAll(Plato.class).size() == 0)
        {
            Categoria aux = Categoria.find(Categoria.class, "name = ?", "Comida China").get(0);
            listaPlatos.add(new Plato("Arroz Chino", ""+Uri.parse("android.resource://" + getPackageName() +"/"+R.drawable.arroz_chino),"activo",aux));
            listaPlatos.add(new Plato("Pollo Kung Pao", ""+Uri.parse("android.resource://" + getPackageName() +"/"+R.drawable.pollo_kung_pao),"activo",aux));
            listaPlatos.add(new Plato("Chop Suey", ""+Uri.parse("android.resource://" + getPackageName() +"/"+R.drawable.chop_suey),"activo",aux));
            listaPlatos.add(new Plato("Chow Mein", ""+Uri.parse("android.resource://" + getPackageName() +"/"+R.drawable.chow_mein),"activo",aux));

            aux = Categoria.find(Categoria.class, "name = ?", "Postres").get(0);
            /*listaPlatos.add(new Plato("Arroz Ranchero", String.valueOf(R.drawable.ranchero),"activo",aux));
            listaPlatos.add(new Plato("Arroz con pollo", String.valueOf(R.drawable.pollo),"activo",aux));
            listaPlatos.add(new Plato("Arroz Paisa", String.valueOf(R.drawable.paisa),"activo",aux));*/
            listaPlatos.add(new Plato("Banana Split", ""+Uri.parse("android.resource://" + getPackageName() +"/"+R.drawable.bananasplit),"activo",aux));
            listaPlatos.add(new Plato("Cheese Cake", ""+Uri.parse("android.resource://" + getPackageName() +"/"+R.drawable.cheesecake),"activo",aux));
            listaPlatos.add(new Plato("Tres Leches", ""+Uri.parse("android.resource://" + getPackageName() +"/"+R.drawable.tresleches),"activo",aux));

            aux = Categoria.find(Categoria.class, "name = ?", "Parrilla").get(0);
            listaPlatos.add(new Plato("Churrasco", ""+Uri.parse("android.resource://" + getPackageName() +"/"+R.drawable.churrasco),"activo",aux));
            listaPlatos.add(new Plato("Costillas a la parrilla", ""+Uri.parse("android.resource://" + getPackageName() +"/"+R.drawable.costilla),"activo",aux));
            listaPlatos.add(new Plato("Baby beef", ""+Uri.parse("android.resource://" + getPackageName() +"/"+R.drawable.baby_beef),"activo",aux));

            aux = Categoria.find(Categoria.class, "name = ?", "Comidas rápidas").get(0);
            listaPlatos.add(new Plato("Hamburguesa", ""+Uri.parse("android.resource://" + getPackageName() +"/"+R.drawable.hamburguesa),"activo",aux));
            listaPlatos.add(new Plato("Perro Caliente", ""+Uri.parse("android.resource://" + getPackageName() +"/"+R.drawable.perros_calientes),"activo",aux));
            listaPlatos.add(new Plato("Emparedado", ""+Uri.parse("android.resource://" + getPackageName() +"/"+R.drawable.emparedado),"activo",aux));

            aux = Categoria.find(Categoria.class, "name = ?", "Comida mexicana").get(0);
            listaPlatos.add(new Plato("Tacos", ""+Uri.parse("android.resource://" + getPackageName() +"/"+R.drawable.tacos),"activo",aux));
            listaPlatos.add(new Plato("Burritos", ""+Uri.parse("android.resource://" + getPackageName() +"/"+R.drawable.burritos),"activo",aux));
            listaPlatos.add(new Plato("Nachos", ""+Uri.parse("android.resource://" + getPackageName() +"/"+R.drawable.nachos),"activo",aux));

            Plato.saveInTx(listaPlatos);
        }

        //insersión de la lista de las características de los platos

        if (CaracteristicasPlato.listAll(CaracteristicasPlato.class).size() == 0)
        {

            //Restaurantes que venden comida china:
            Plato plato = Plato.find(Plato.class, "nombre = ?", "Arroz Chino").get(0);
            Restaurant restaurante = Restaurant.find(Restaurant.class, "nit = ?", "1").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("carnes, vegetales, salsa especial","tracicional",0,14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "9").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("carnes, vegetales, salsa agridulce","Especial",0,14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "10").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("carnes, vegetales, salsa especial","Tradicional",0,14000,"activo",restaurante,plato));

            plato = Plato.find(Plato.class, "nombre = ?", "Pollo Kung Pao").get(0);
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "10").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("pollo en salsa especial","",0,14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "1").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("pollo especial","Especial de la casa",0,14000,"activo",restaurante,plato));


            plato = Plato.find(Plato.class, "nombre = ?", "Chop Suey").get(0);
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "1").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("carnes, vegetales, salsa especial","con carne",0,14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "10").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("carnes, vegetales, salsa especial","Arroz chino tracicional",0,14000,"activo",restaurante,plato));

            plato = Plato.find(Plato.class, "nombre = ?", "Chow Mein").get(0);
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "9").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("carnes, vegetales, salsa agridulce","Arroz chino tracicional",0,14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "10").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("carnes, vegetales, salsa especial","Arroz chino tracicional",0,14000,"activo",restaurante,plato));

            //restaurantes que venden Postres
            plato = Plato.find(Plato.class, "nombre = ?", "Banana Split").get(0);
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "11").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("Banano, Helado, gomitas, salsas dulces","del tio Tom",0,14000,"activo",restaurante,plato));

            plato = Plato.find(Plato.class, "nombre = ?", "Cheese Cake").get(0);
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "11").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("queso, fruta, chocolate y salsas dulces","de distintos tipos",0,14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "12").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("queso, fruta, cremas, y más","Oreo, frutas, chicle",0,14000,"activo",restaurante,plato));

            plato = Plato.find(Plato.class, "nombre = ?", "Tres Leches").get(0);
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "11").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("ingredientes secretos","clásico",0,14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "12").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("leche, leche condensada, arequipe, galleta","",0,14000,"activo",restaurante,plato));

            //restaurantes que venden parrilla
            plato = Plato.find(Plato.class, "nombre = ?", "Churrasco").get(0);
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "7").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("carnes, vegetales, salsa agridulce","",0,14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "13").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("carnes, vegetales, salsa especial","",0,14000,"activo",restaurante,plato));

            plato = Plato.find(Plato.class, "nombre = ?", "Costillas a la parrilla").get(0);
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "7").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("carnes, vegetales, salsa agridulce","",0,14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "13").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("carnes, vegetales, salsa especial","",0,14000,"activo",restaurante,plato));

            plato = Plato.find(Plato.class, "nombre = ?", "Baby beef").get(0);
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "13").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("carnes, vegetales, salsa agridulce","",0,14000,"activo",restaurante,plato));

            //restaurantes que venden comidas rápidas
            plato = Plato.find(Plato.class, "nombre = ?", "Hamburguesa").get(0);
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "5").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("pollo, doble jamon, doble queso","Especial",0,14000,"activo",restaurante,plato));
            listaCaracteristicas.add(new CaracteristicasPlato("carne, queso, tomate salsas","Cásica",0,14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "6").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("carnes, vegetales, salsa agridulce","",0,14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "7").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("carnes, vegetales, salsa agridulce","",0,14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "8").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("carnes, vegetales, salsa especial","",0,14000,"activo",restaurante,plato));

            plato = Plato.find(Plato.class, "nombre = ?", "Perro Caliente").get(0);
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "6").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("carnes, vegetales, salsa agridulce","",0,14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "7").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("carnes, vegetales, salsa agridulce","",0,14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "8").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("carnes, vegetales, salsa especial","",0,14000,"activo",restaurante,plato));

            plato = Plato.find(Plato.class, "nombre = ?", "Emparedado").get(0);
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "5").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("carnes, vegetales, salsa agridulce","",0,14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "6").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("carnes, vegetales, salsa agridulce","",0,14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "8").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("carnes, vegetales, salsa especial","",0,14000,"activo",restaurante,plato));

            //restaurantes que venden comida mexicana

            plato = Plato.find(Plato.class, "nombre = ?", "Tacos").get(0);
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "2").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("carnes, vegetales, salsa agridulce","",0,14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "4").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("carnes, vegetales, salsa especial","",0,14000,"activo",restaurante,plato));

            plato = Plato.find(Plato.class, "nombre = ?", "Burritos").get(0);
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "3").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("carnes, vegetales, salsa agridulce","",0,14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "4").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("carnes, vegetales, salsa especial","",0,14000,"activo",restaurante,plato));

            plato = Plato.find(Plato.class, "nombre = ?", "Nachos").get(0);
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "2").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("carnes, vegetales, salsa agridulce","",0,14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "3").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("carnes, vegetales, salsa agridulce","",0,14000,"activo",restaurante,plato));
            restaurante = Restaurant.find(Restaurant.class, "nit = ?", "4").get(0);
            listaCaracteristicas.add(new CaracteristicasPlato("carnes, vegetales, salsa especial","",0,14000,"activo",restaurante,plato));

            CaracteristicasPlato.saveInTx(listaCaracteristicas);
        }

        //insersión de la lista de calificaciones

        List<Calificacion> listaCalificaciones = new ArrayList<>();

        List<CaracteristicasPlato> caracteristicas = CaracteristicasPlato.listAll(CaracteristicasPlato.class);
        int aa = 0;
        for (CaracteristicasPlato cat : caracteristicas)
        {
            Random rnd = new Random();
            int punt = (int)(rnd.nextDouble() * 5 + 1);
            listaCalificaciones.add(new Calificacion(aa,"",punt,0,cat));
            aa++;
        }
        Calificacion.saveInTx(listaCalificaciones);


        Intent intent = new Intent(Splash.this, GalleryActivity.class);
        startActivity(intent);
        finish();

    }

}