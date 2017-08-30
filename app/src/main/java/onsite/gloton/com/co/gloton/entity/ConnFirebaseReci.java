package onsite.gloton.com.co.gloton.entity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import onsite.gloton.com.co.gloton.R;

public class ConnFirebaseReci extends BroadcastReceiver {


    public ConnFirebaseReci() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("GlotOn");

        final DatabaseReference categFB = myRef.child("Categoria");
        final DatabaseReference cartaFB = myRef.child("Carta");
        final DatabaseReference platoFB = myRef.child("Plato");
        final DatabaseReference restFB = myRef.child("Restaurante");

        final List<Categoria> categorias = new ArrayList<>();
        final List<Restaurant> listaRestaurantes = new ArrayList<>();
        final List<Plato> listaPlatos = new ArrayList<>();
        final List<Caracteristicas_Plato> listaCaracteristicas = new ArrayList<>();

        //insersión de las categorías
        
        if(categFB!=null)

        {
            Categoria.deleteAll(Categoria.class);
            categFB.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snap : dataSnapshot.getChildren()) {
                        Categoria categoria;
                        if (snap.child("cat_Estado").getValue().toString().equals("Activo")) {
                            categoria = new Categoria();
                            categoria.setName(snap.child("cat_Nombre").getValue().toString());
                            categoria.setImageSource(R.drawable.arrozchino);
                            categorias.add(categoria);
                            System.out.println("***categoria adicionada: "+categoria.getName());
                        }
                    }
                    Categoria.saveInTx(categorias);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        //insersión de la lista de restaurantes

        if(restFB!=null)

        {
            Restaurant.deleteAll(Restaurant.class);
            restFB.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snap : dataSnapshot.getChildren()) {
                        Restaurant restaurante;
                        if (snap.child("Estado").getValue().toString().equals("Activo")) {
                            restaurante = new Restaurant();
                            restaurante.setNombre(snap.child("Nombre").getValue().toString());
                            restaurante.setDireccion(snap.child("Direccion").getValue().toString());
                            restaurante.setLatitud(Float.parseFloat(snap.child("Latitud").getValue().toString()));
                            restaurante.setLogo(snap.child("Logo").getValue().toString());
                            restaurante.setLongitud(Float.parseFloat(snap.child("Longitud").getValue().toString()));
                            restaurante.setNit(Integer.parseInt(snap.child("Nit").getValue().toString()));
                            restaurante.setTelefono(snap.child("Telefono").getValue().toString());
                            listaRestaurantes.add(restaurante);
                        }
                    }
                    Restaurant.saveInTx(listaRestaurantes);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        //insersión de la lista de platos
        if(platoFB!=null)

        {
            Plato.deleteAll(Plato.class);
            platoFB.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snap : dataSnapshot.getChildren()) {
                        Plato plato;
                        if (snap.child("Estado").getValue().toString().equals("Activo")) {
                            plato = new Plato();
                            plato.setNombre(snap.child("Nombre").getValue().toString());
                            plato.setImagen(snap.child("Imagen").getValue().toString());
                            Categoria cate = Categoria.find(Categoria.class, "name = ?", snap.child("Categoria").getValue().toString()).get(0);
                            plato.setCategoria(cate);
                            System.out.println("**********plato: " + plato.getNombre());
                            listaPlatos.add(plato);
                        }
                    }
                    Plato.saveInTx(listaPlatos);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        //insersión de la lista de las características de los platos

        if(cartaFB!=null&&listaPlatos.size()>0&&listaRestaurantes.size()>0)

        {
            Caracteristicas_Plato.deleteAll(Caracteristicas_Plato.class);
            cartaFB.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snap : dataSnapshot.getChildren()) {
                        Caracteristicas_Plato carac;
                        if (snap.child("Estado").getValue().toString().equals("Activo")) {
                            carac = new Caracteristicas_Plato();
                            carac.setDescripcion(snap.child("Descripcion").getValue().toString());
                            carac.setIngredientes(snap.child("Ingredientes").getValue().toString());
                            carac.setPrecio(Integer.parseInt(snap.child("Precio").getValue().toString()));
                            Plato plato;
                            System.out.println("***************---------- " + snap.child("Plato").getValue().toString());
                            plato = Plato.find(Plato.class, "nombre = ?", snap.child("Plato").getValue().toString()).get(0);
                            carac.setPlato(plato);
                            Restaurant rest;
                            rest = Restaurant.find(Restaurant.class, "nombre = ?", snap.child("Restaurante").getValue().toString()).get(0);
                            carac.setRestaurante(rest);
                            listaCaracteristicas.add(carac);

                        }
                    }
                    Caracteristicas_Plato.saveInTx(listaCaracteristicas);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        if(listaCaracteristicas.size()>0&&listaPlatos.size()>0&&listaRestaurantes.size()>0&&categorias.size()>0)

        {
            Categoria.saveInTx(categorias);
            Plato.saveInTx(listaPlatos);
            Restaurant.saveInTx(listaRestaurantes);
            Caracteristicas_Plato.saveInTx(listaCaracteristicas);
        }

        //insersión de la lista de calificaciones

        if(Calificacion.listAll(Calificacion.class).size() == 0)

        {
            List<Calificacion> listaCal = new ArrayList<>();

            List<Caracteristicas_Plato> caracteristicas = Caracteristicas_Plato.listAll(Caracteristicas_Plato.class);
            int a = 0;
            for (Caracteristicas_Plato cat : caracteristicas) {
                Random rnd = new Random();
                int cant = (int) (rnd.nextDouble() * 10 + 0);
                for (int i = 0; i < cant; i++) {
                    int punt = (int) (rnd.nextDouble() * 5 + 1);
                    listaCal.add(new Calificacion(a, "", punt, cat));
                    a++;
                }
            }

            Calificacion.saveInTx(listaCal);


        }

    }
}
