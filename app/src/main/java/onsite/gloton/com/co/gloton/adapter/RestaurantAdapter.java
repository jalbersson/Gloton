package onsite.gloton.com.co.gloton.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import onsite.gloton.com.co.gloton.R;
import onsite.gloton.com.co.gloton.activity.DetalleRestauranteActivity;
import onsite.gloton.com.co.gloton.entity.Caracteristicas_Plato;
import onsite.gloton.com.co.gloton.entity.Restaurant;

/**
 * Created by Prometheus on 5/06/2017.
 */

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder>{

    Context context;
    private List<Restaurant> restaurantes;



    public RestaurantAdapter(Context context, List<Restaurant> restaurantes) {
        this.context = context;
        this.restaurantes = restaurantes;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgRestaurante;
        private TextView titulorest;
        private TextView distRest;
        public ViewHolder(View itemView) {
            super(itemView);
            imgRestaurante = (ImageView) itemView.findViewById(R.id.imgRestaurante);
            titulorest = (TextView) itemView.findViewById(R.id.txtNombreResta);
            distRest = (TextView) itemView.findViewById(R.id.txtItemRestDistancia);
        }
    }



    @Override
    public RestaurantAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_restaurant,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RestaurantAdapter.ViewHolder holder, final int position) {

        Picasso.with(context).load(restaurantes.get(position).getLogo()).into(holder.imgRestaurante);
        holder.distRest.setText("Estás a "+String.valueOf((int) restaurantes.get(position).getDistancia())+" mts");
        holder.titulorest.setText(restaurantes.get(position).getNombre());
        holder.imgRestaurante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetalleRestauranteActivity.class);
                intent.putExtra("codigoRestaurante",restaurantes.get(position).getId());
               context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantes.size();
    }
}
