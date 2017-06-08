package onsite.gloton.com.co.gloton.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import onsite.gloton.com.co.gloton.R;
import onsite.gloton.com.co.gloton.entity.Restaurant;

/**
 * Created by Prometheus on 5/06/2017.
 */

public class RestaurantAdapter extends BaseAdapter
{
    public RestaurantAdapter(Context context, ArrayList<Restaurant> data) {
        this.context = context;
        this.data = data;
    }

    private Context context;
    private ArrayList<Restaurant> data;

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.item_restaurant,null);

        TextView nombre = (TextView) v.findViewById(R.id.txtNomRest);
        TextView dir = (TextView) v.findViewById(R.id.txtDirRest);
        TextView tel = (TextView) v.findViewById(R.id.txtTelRest);
        ImageView foto = (ImageView) v.findViewById(R.id.img_item_rest);

        nombre.setText(data.get(position).getName());
        dir.setText(data.get(position).getAddress());
        tel.setText(data.get(position).getTelephone());
        foto.setImageResource(data.get(position).getLogo());


        return v;
    }
}
