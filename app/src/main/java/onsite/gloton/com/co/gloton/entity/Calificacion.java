package onsite.gloton.com.co.gloton.entity;

import com.orm.SugarRecord;

/**
 * Created by Prometheus on 26/06/2017.
 */

public class Calificacion extends SugarRecord  implements Comparable<Calificacion>{
    int ide;
    String usuario; //id android del celular que se usa
    int puntuacion;
    int idUniversalPlato;
    CaracteristicasPlato caracteristicas;

    public Calificacion(int ide, String usuario, int puntuacion, int idUniversalPlato, CaracteristicasPlato caracteristicas) {
        this.ide = ide;
        this.usuario = usuario;
        this.puntuacion = puntuacion;
        this.idUniversalPlato = idUniversalPlato;
        this.caracteristicas = caracteristicas;
    }

    public Calificacion() {
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

    public int getIdUniversalPlato() {
        return idUniversalPlato;
    }

    public void setIdUniversalPlato(int idUniversalPlato) {
        this.idUniversalPlato = idUniversalPlato;
    }

    public CaracteristicasPlato getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(CaracteristicasPlato caracteristicas) {
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
