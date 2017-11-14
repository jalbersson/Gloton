package onsite.gloton.com.co.gloton.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import onsite.gloton.com.co.gloton.R;
import onsite.gloton.com.co.gloton.activity.DetailActivity;
import onsite.gloton.com.co.gloton.entity.Categoria;

/**
 * Created by admin on 4/06/17.
 */

public class CoverFlowAdapter extends BaseAdapter {

    Context contexto;
    private List<Categoria> data;
    private AppCompatActivity activity;

    public CoverFlowAdapter(List<Categoria> data, AppCompatActivity activity) {
        this.data = data;
        this.activity = activity;
        this.contexto = activity.getBaseContext();
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

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_flow_view, null, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (data.get(position).getImageSource() != null && !data.get(position).getImageSource().equals(""))
            Picasso.with(contexto).load(data.get(position).getImageSource()).into(viewHolder.foodImage);
    //    viewHolder.foodImage.setImageResource(data.get(position).getImageSource());
        viewHolder.foodName.setText(data.get(position).getName());
        viewHolder.foodId.setText(String.valueOf(position+1));

        convertView.setOnClickListener(onClickListener(position));


        return convertView;
    }


    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("ItemSelected", String.valueOf(position));
                Intent intent = new Intent(activity, DetailActivity.class);
                intent.putExtra("titleFood", data.get(position).getName());
                intent.putExtra("optionSelected", data.get(position).getId());
                activity.startActivity(intent);

            }
        };
    }


    private static class ViewHolder {
        private TextView foodName;
        private TextView foodId;
        private ImageView foodImage;

        public ViewHolder(View v) {
            foodImage = (ImageView) v.findViewById(R.id.image);
            foodName = (TextView) v.findViewById(R.id.name);
            foodId = (TextView) v.findViewById(R.id.txtIdCat);
        }
    }


}
