package onsite.gloton.com.co.gloton.entity;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by Apollo on 26/10/2017.
 */

public class Auxiliar extends SugarRecord
{
    private Date fechaUltima;
    private Date fechaProxima;

    public Auxiliar() {
    }

    public Auxiliar(Date fechaUltima, Date fechaProxima) {
        this.fechaUltima = fechaUltima;
        this.fechaProxima = fechaProxima;
    }

    public Date getFechaUltima() {
        return fechaUltima;
    }

    public void setFechaUltima(Date fechaUltima) {
        this.fechaUltima = fechaUltima;
    }

    public Date getFechaProxima() {
        return fechaProxima;
    }

    public void setFechaProxima(Date fechaProxima) {
        this.fechaProxima = fechaProxima;
    }
}
