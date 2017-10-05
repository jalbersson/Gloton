package onsite.gloton.com.co.gloton.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import onsite.gloton.com.co.gloton.R;
import onsite.gloton.com.co.gloton.activity.detalle_plato;
import onsite.gloton.com.co.gloton.entity.Calificacion;
import onsite.gloton.com.co.gloton.entity.Caracteristicas_Plato;

/**
 * Created by Apollo on 2/10/2017.
 */

public class RecomendadosAdapter extends RecyclerView.Adapter<RecomendadosAdapter.ViewHolder> {

    Context contexto;
    List<Calificacion> menu;

    public RecomendadosAdapter(Context contexto, List<Calificacion> menu) {
        this.contexto = contexto;
        this.menu = menu;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_recomendado,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Caracteristicas_Plato plato = menu.get(position).getCaracteristicas();
        String descrip = menu.get(position).getPuntuacion()+"\n"+plato.getRestaurante().getNombre()+"\n"+ String.valueOf(plato.getPrecio());

        holder.nom.setText(plato.getPlato().getNombre());
        holder.desc.setText(descrip);
        Picasso.with(contexto).load(R.drawable.platocubiertos).into(holder.foto);

        holder.foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(contexto, detalle_plato.class);
                intent.putExtra("caracPlato",plato.getId());
                contexto.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return menu.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView foto;
        private TextView nom;
        private TextView desc;

        public ViewHolder(View itemView) {
            super(itemView);



             nom = (TextView) itemView.findViewById(R.id.txtRecNomPlato);
             desc = (TextView) itemView.findViewById(R.id.txtRecPrecioPlato);
             foto = (ImageView) itemView.findViewById(R.id.imgRecPlato);
        }




    }

/*    @Override
    public int getCount() {
        return menu.size();
    }

    @Override
    public Object getItem(int position) {
        return menu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return menu.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Caracteristicas_Plato plato = menu.get(position).getCaracteristicas();

        View v = View.inflate(contexto, R.layout.item_recomendado,null);

        TextView nom = (TextView) v.findViewById(R.id.txtRecNomPlato);
        TextView desc = (TextView) v.findViewById(R.id.txtRecPrecioPlato);
        ImageView foto = (ImageView) v.findViewById(R.id.imgRecPlato);

        String descrip = menu.get(position).getPuntuacion()+"\n"+plato.getRestaurante().getNombre()+"\n"+ String.valueOf(plato.getPrecio());

        nom.setText(plato.getPlato().getNombre());
        desc.setText(descrip);
        foto.setImageResource(Integer.parseInt(plato.getPlato().getImagen()));

        return v;
    }*/
}
