package onsite.gloton.com.co.gloton.entity;

import com.orm.SugarRecord;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Prometheus on 26/06/2017.
 */

public class CaracteristicasPlato extends SugarRecord{
    String ingredientes;
    String descripcion;
    int idUniversal;
    int precio;
    String estado;
    Restaurant restaurante;
    Plato plato;


    public CaracteristicasPlato(String ingredientes, String descripcion, int idUniversal, int precio, String estado, Restaurant restaurante, Plato plato) {
        this.ingredientes = ingredientes;
        this.descripcion = descripcion;
        this.idUniversal = idUniversal;
        this.precio = precio;
        this.estado = estado;
        this.restaurante = restaurante;
        this.plato = plato;
    }

    public CaracteristicasPlato() {
    }

    public List<CaracteristicasPlato> ListarMenu(Restaurant rest)
    {

        List<CaracteristicasPlato> platos = new LinkedList<CaracteristicasPlato>();
        List<CaracteristicasPlato> todos = CaracteristicasPlato.listAll(CaracteristicasPlato.class);

        for (CaracteristicasPlato car : todos)
        {
            if (car.getRestaurante().getId().equals(rest.getId()))
                platos.add(car);
        }

        return  platos;
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

    public int getIdUniversal() {
        return idUniversal;
    }

    public void setIdUniversal(int idUniversal) {
        this.idUniversal = idUniversal;
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
