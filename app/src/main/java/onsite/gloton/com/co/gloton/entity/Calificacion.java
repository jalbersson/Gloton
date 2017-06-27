package onsite.gloton.com.co.gloton.entity;

import com.orm.SugarRecord;

/**
 * Created by Prometheus on 26/06/2017.
 */

public class Calificacion extends SugarRecord
{
    int ide;
    int puntuacion;
    Caracteristicas_Plato caracteristicas;

    public Calificacion() {    }

    public Calificacion(int id, int puntuacion, Caracteristicas_Plato caracteristicas)
    {
        this.ide = ide;
        this.puntuacion = puntuacion;
        this.caracteristicas = caracteristicas;
    }

    public int getIde() {
        return ide;
    }

    public void setIde(int ide) {
        this.ide = ide;
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
}
