package com.georgekortsaridis.boosterelitefitness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by george.kortsaridis on 01/04/2017.
 */

public class WODListViewAdapter extends BaseAdapter {

    ArrayList<WOD> wods;
    private Context context;


    public WODListViewAdapter(ArrayList<WOD> wods, Context context){
        this.wods = wods;
        this.context = context;
    }

    @Override
    public int getCount() {
        return wods.size();
    }

    @Override
    public Object getItem(int position) { return position; }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View wod;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        wod = layoutInflater.inflate(R.layout.wod_listview_item, null);

        TextView date = (TextView) wod.findViewById(R.id.dateTV);
        TextView desc = (TextView) wod.findViewById(R.id.wodTV);

        date.setText(wods.get(position).getDate());
        desc.setText(wods.get(position).getWod());

        return wod;
    }
}
