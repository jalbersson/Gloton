package onsite.gloton.com.co.gloton.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import onsite.gloton.com.co.gloton.R;
import onsite.gloton.com.co.gloton.entity.Caracteristicas_Plato;

/**
 * Created by Prometheus on 5/06/2017.
 */

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder>{

    Context context;
    private List<Caracteristicas_Plato> caracteristicas_platos;

    public RestaurantAdapter(Context context, List<Caracteristicas_Plato> caracteristicas_platos) {
        this.context = context;
        this.caracteristicas_platos = caracteristicas_platos;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgRestaurante;
        private TextView titulorest;
        public ViewHolder(View itemView) {
            super(itemView);
            imgRestaurante = (ImageView) itemView.findViewById(R.id.imgRestaurante);
            titulorest = (TextView) itemView.findViewById(R.id.titulorest);
        }
    }




    @Override
    public RestaurantAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_restaurant,parent,false);
        //view.setOnClickListener();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RestaurantAdapter.ViewHolder holder, int position) {

        Picasso.with(context).load(caracteristicas_platos.get(position).getRestaurante().getLogo()).into(holder.imgRestaurante);
        //holder.titulorest.setText(caracteristicas_platos.get(position).getPlato().getNombre());
    }

    @Override
    public int getItemCount() {
        return caracteristicas_platos.size();
    }
}
