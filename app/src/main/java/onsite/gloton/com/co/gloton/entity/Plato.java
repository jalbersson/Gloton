package onsite.gloton.com.co.gloton.entity;

import com.orm.SugarRecord;

/**
 * Created by Prometheus on 26/06/2017.
 */

public class Plato extends SugarRecord{
    String nombre;
    String imagen;
    Categoria categoria;

    public Plato() {    }

    public Plato(String nombre, String imagen, String estado, Categoria categoria)
    {
        this.nombre = nombre;
        this.imagen = imagen;
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }



    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
