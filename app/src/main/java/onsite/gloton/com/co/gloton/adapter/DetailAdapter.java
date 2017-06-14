package onsite.gloton.com.co.gloton.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import onsite.gloton.com.co.gloton.R;
import onsite.gloton.com.co.gloton.entity.Categoria;

/**
 * Created by admin on 4/06/17.
 */

public class DetailAdapter extends BaseAdapter {

    public DetailAdapter(Context context, ArrayList<Categoria> data) {
        this.context = context;
        this.data = data;
    }

    private Context context;
    private ArrayList<Categoria> data;

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
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_detail_food, null, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.foodImage.setImageResource(data.get(position).getImageSource());
        viewHolder.foodName.setText(data.get(position).getName());

        return convertView;
    }

    private static class ViewHolder {
        private TextView foodName;
        private ImageView foodImage;

        public ViewHolder(View v) {
            foodImage = (ImageView) v.findViewById(R.id.image);
            foodName = (TextView) v.findViewById(R.id.title);
        }
    }
}
