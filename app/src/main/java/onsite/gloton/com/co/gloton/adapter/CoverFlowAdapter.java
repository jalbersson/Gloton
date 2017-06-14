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

import java.util.List;

import onsite.gloton.com.co.gloton.R;
import onsite.gloton.com.co.gloton.activity.DetailActivity;
import onsite.gloton.com.co.gloton.entity.Categoria;

/**
 * Created by admin on 4/06/17.
 */

public class CoverFlowAdapter extends BaseAdapter {

    private List<Categoria> data;
    private AppCompatActivity activity;

    public CoverFlowAdapter(List<Categoria> data, AppCompatActivity activity) {
        this.data = data;
        this.activity = activity;
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

        viewHolder.foodImage.setImageResource(data.get(position).getImageSource());
        viewHolder.foodName.setText(data.get(position).getName());

        convertView.setOnClickListener(onClickListener(position));


        return convertView;
    }


    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("ItemSelected",String.valueOf(position));
                if (position == 3 || position == 4) {
                    Intent intent = new Intent(activity, DetailActivity.class);
                    intent.putExtra("titleFood",data.get(position).getName());
                    intent.putExtra("optionSelected",position);
                    activity.startActivity(intent);
                }
            }
        };
    }


    private static class ViewHolder {
        private TextView foodName;
        private ImageView foodImage;

        public ViewHolder(View v) {
            foodImage = (ImageView) v.findViewById(R.id.image);
            foodName = (TextView) v.findViewById(R.id.name);
        }
    }




}