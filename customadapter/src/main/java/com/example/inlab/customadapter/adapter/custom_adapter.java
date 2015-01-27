package com.example.inlab.customadapter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.inlab.customadapter.R;
import com.example.inlab.customadapter.model.Restaurant;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by inlab on 27/01/2015.
 */
public class custom_adapter extends ArrayAdapter {

    private List<Restaurant> mRestaurant;
    private Context mContext;
    private int mResource;

    public custom_adapter(Context context, List<Restaurant> data) {

        super(context, R.layout.custom_item, data);
        mContext = context;
        mRestaurant = data;
        mResource = R.layout.custom_item;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflater.inflate(mResource,parent,false);

        TextView name = (TextView) rowView.findViewById(R.id.name);
        TextView rate = (TextView) rowView.findViewById(R.id.rate);

        name.setText(mRestaurant.get(position).name);
        rate.setText(mRestaurant.get(position).rate);

        return rowView;
    }
}
