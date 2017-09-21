package onsite.gloton.com.co.gloton.entity;

import com.orm.SugarRecord;

/**
 * Created by admin on 4/06/17.
 */

public class Categoria extends SugarRecord{

    private String name;
    private int imageSource;
    private int estado;

    public Categoria() {
    }

    public Categoria(String name, int imageSource, int estado) {
        this.name = name;
        this.imageSource = imageSource;
        this.estado = estado;
    }

    public String getName() {
        return name;
    }

    public int getImageSource() {
        return imageSource;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setImageSource(int imagen) {
        this.imageSource = imagen;
    }


}
