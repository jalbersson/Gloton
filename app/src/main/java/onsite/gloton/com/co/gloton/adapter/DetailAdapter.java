package onsite.gloton.com.co.gloton.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import onsite.gloton.com.co.gloton.R;
import onsite.gloton.com.co.gloton.entity.Categoria;
import onsite.gloton.com.co.gloton.entity.Plato;

/**
 * Created by admin on 4/06/17.
 */

public class DetailAdapter extends BaseAdapter {

    private Context context;
    private List<Plato> data;

    public DetailAdapter(Context context, List<Plato> data) {
        this.context = context;
        this.data = data;
    }



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
        ViewHolder viewHolder;

        try {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_detail_food, null, false);

                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            Log.d("Imagen url",String.valueOf(data.get(position).getImagen()));
            //Picasso.with(context).load(data.get(position).getImagen()).into(viewHolder.foodImage);
            viewHolder.foodName.setText(data.get(position).getNombre());
            viewHolder.foodImage.setImageResource(R.drawable.platocubiertos);
            if (position % 2 == 0) {
                viewHolder.relativeLayoutPpal.setBackgroundColor(ContextCompat.getColor(context, R.color.colorTitle));
            } else {
                viewHolder.relativeLayoutPpal.setBackgroundColor(ContextCompat.getColor(context, R.color.colorTitleTransparent));
            }
            //viewHolder.imgTitleCateg.setImageResource(Integer.valueOf(data.get(position).getCategoria().getImageSource()));

        }catch (NumberFormatException e){
            Log.d("Error URL",String.valueOf(e));
        }

        return convertView;
    }

    private static class ViewHolder {
        private TextView foodName;
        private ImageView foodImage;
        private RelativeLayout relativeLayoutPpal;
        //

        public ViewHolder(View v) {
            foodImage = (ImageView) v.findViewById(R.id.image);
            foodName = (TextView) v.findViewById(R.id.title);
            relativeLayoutPpal = (RelativeLayout) v.findViewById(R.id.relativePpal);
        }
    }
}
