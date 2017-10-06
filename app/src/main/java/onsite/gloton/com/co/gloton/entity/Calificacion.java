package onsite.gloton.com.co.gloton.entity;

import com.orm.SugarRecord;

/**
 * Created by Prometheus on 26/06/2017.
 */

public class Calificacion extends SugarRecord  implements Comparable<Calificacion>{
    int ide;
    String usuario; //id android del celular que se usa
    int puntuacion;
    Caracteristicas_Plato caracteristicas;

    public Calificacion() {    }

    public Calificacion(int ide, String usuario, int puntuacion, Caracteristicas_Plato caracteristicas) {
        this.ide = ide;
        this.usuario = usuario;
        this.puntuacion = puntuacion;
        this.caracteristicas = caracteristicas;
    }

    public int getIde() {
        return ide;
    }

    public void setIde(int ide) {
        this.ide = ide;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Caracteristicas_Plato getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(Caracteristicas_Plato caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    @Override
    public int compareTo(Calificacion o) {
        if (puntuacion < o.puntuacion) {
            return 1;
        }
        if (puntuacion > o.puntuacion) {
            return -1;
        }
        return 0;
    }
}
