package onsite.gloton.com.co.gloton.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import onsite.gloton.com.co.gloton.R;
import onsite.gloton.com.co.gloton.entity.CaracteristicasPlato;

/**
 * Created by Prometheus on 29/06/2017.
 */

public class MenuDetalleRestauranteAdapter extends BaseAdapter {

    Context contexto;
    List<CaracteristicasPlato> menu;

    public MenuDetalleRestauranteAdapter(Context contexto, List<CaracteristicasPlato> menu) {
        this.contexto = contexto;
        this.menu = menu;
    }

    @Override
    public int getCount()
    {
        return menu.size();
    }

    @Override
    public Object getItem(int position)
    {
        return menu.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return menu.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = View.inflate(contexto, R.layout.item_plato_detalle_restaurante,null);

        TextView nom = (TextView) v.findViewById(R.id.txtDetPlatoNomPlato);
        TextView prec = (TextView) v.findViewById(R.id.txtDetPlatoPrecioPlato);
        ImageView foto = (ImageView) v.findViewById(R.id.imgDetRestPlato);

        nom.setText(menu.get(position).getPlato().getNombre());
        prec.setText("$ " + String.valueOf(menu.get(position).getPrecio()));
        if (menu.get(position).getPlato().getImagen() != null)
        {
            if (!menu.get(position).getPlato().getImagen().equals(""))
                foto.setImageResource(Integer.parseInt(menu.get(position).getPlato().getImagen()));
        }
     //   Picasso.with(contexto).load(menu.get(position).getPlato().getImagen()).into(foto);

        return v;
    }
}
