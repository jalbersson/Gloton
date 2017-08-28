package onsite.gloton.com.co.gloton.entity;

import com.orm.SugarRecord;

/**
 * Created by Prometheus on 5/06/2017.
 */

public class Restaurant extends SugarRecord implements Comparable<Restaurant>{

    int nit;
    String nombre;
    String direccion;
    String telefono;
    String logo;
    double latitud;
    double longitud;
    float distancia;

    public Restaurant() {
    }

    public Restaurant(int nit, String nombre, String direccion, String telefono, String logo, String estado, double latitud, double longitud, float distancia) {
        this.nit = nit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.logo = logo;
        this.latitud = latitud;
        this.longitud = longitud;
        this.distancia = distancia;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }


    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public float getDistancia() {
        return distancia;
    }

    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

    @Override
    public int compareTo(Restaurant o) {
        if (distancia < o.distancia) {
            return -1;
        }
        if (distancia > o.distancia) {
            return 1;
        }
        return 0;
    }
}
