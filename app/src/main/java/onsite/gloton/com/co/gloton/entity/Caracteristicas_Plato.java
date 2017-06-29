package onsite.gloton.com.co.gloton.entity;

import com.orm.SugarRecord;

/**
 * Created by Prometheus on 26/06/2017.
 */

public class Caracteristicas_Plato extends SugarRecord{
    String ingredientes;
    String descripcion;
    int precio;
    String estado;
    Restaurant restaurante;
    Plato plato;



    public Caracteristicas_Plato(String ingredientes, String descripcion, int precio, String estado, Restaurant restaurante, Plato plato)
    {
        this.ingredientes = ingredientes;
        this.descripcion = descripcion;
        this.precio = precio;
        this.estado = estado;
        this.restaurante = restaurante;
        this.plato = plato;
    }
    public Caracteristicas_Plato() {
    }


    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Restaurant getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurant restaurante) {
        this.restaurante = restaurante;
    }

    public Plato getPlato() {
        return plato;
    }

    public void setPlato(Plato plato) {
        this.plato = plato;
    }
}
