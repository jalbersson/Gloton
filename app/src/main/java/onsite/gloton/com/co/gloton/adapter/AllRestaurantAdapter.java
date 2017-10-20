package onsite.gloton.com.co.gloton.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.List;

import onsite.gloton.com.co.gloton.R;
import onsite.gloton.com.co.gloton.activity.DetalleRestauranteActivity;
import onsite.gloton.com.co.gloton.entity.Restaurant;






public class AllRestaurantAdapter extends RecyclerView.Adapter<AllRestaurantAdapter.ViewHolder>{

    private Context context;
    private List<Restaurant> restaurants;

    public AllRestaurantAdapter(Context context, List<Restaurant> restaurants) {
        this.context = context;
        this.restaurants = restaurants;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgRestauranteall;
        private TextView dist;
        public ViewHolder(View itemView) {
            super(itemView);
            imgRestauranteall = (ImageView) itemView.findViewById(R.id.imgRestauranteall);
            dist = (TextView) itemView.findViewById(R.id.txtAllRestDist);
        }
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_restaurant,parent,false);
        return new ViewHolder(view);

        */

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_allrestaurant,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Picasso.with(context).load(restaurants.get(position).getLogo()).into(holder.imgRestauranteall);
        int distancia = (int) restaurants.get(position).getDistancia();
        if (distancia > 0)
        {
            holder.dist.setText("Estás a "+String.valueOf(distancia)+" mts");
        }
        else
        {
            holder.dist.setVisibility(View.INVISIBLE);
        }
        holder.imgRestauranteall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetalleRestauranteActivity.class);
                intent.putExtra("codigoRestaurante",restaurants.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }


}
