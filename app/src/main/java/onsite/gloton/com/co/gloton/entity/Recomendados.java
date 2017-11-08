package onsite.gloton.com.co.gloton.entity;

import com.orm.SugarRecord;

/**
 * Created by Apollo on 27/10/2017.
 */

public class Recomendados extends SugarRecord implements Comparable<Recomendados>
{
    int puntuacion;
    int ide;
    String usuario;
    CaracteristicasPlato caracteristicas;

    public Recomendados() {
    }

    public Recomendados(int puntuacion, int ide, String usuario, CaracteristicasPlato caracteristicas) {
        this.puntuacion = puntuacion;
        this.ide = ide;
        this.usuario = usuario;
        this.caracteristicas = caracteristicas;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
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

    public CaracteristicasPlato getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(CaracteristicasPlato caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    @Override
    public int compareTo(Recomendados o) {
        if (puntuacion < o.puntuacion) {
            return 1;
        }
        if (puntuacion > o.puntuacion) {
            return -1;
        }
        return 0;
    }
}
