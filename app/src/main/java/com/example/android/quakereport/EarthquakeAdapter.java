package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by krzysztof on 16.02.2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        Check if there is an existing list item that we can reuse,
//          otherwise, if convertView is null, then inflate a new list item layout
        View listItemView = convertView;
        if (listItemView==null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_layout, parent, false);
        }

        // find the earthquake at the given position in the list of earthquakes
        Earthquake currentEarthquake = getItem(position);

        // find the TextView with ID magnitude
        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
        // Display the magnitude of the current earthquake in that TextView
        magnitudeView.setText(currentEarthquake.getMagnitude());

        // find the TextView with ID location
        TextView locationView = (TextView) listItemView.findViewById(R.id.location);
        // Display the location of the earthquake in that TextView
        locationView.setText(currentEarthquake.getLocation());

        // Find the TextView with ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        // Display the date of the earthquake in that TextView
        dateView.setText(currentEarthquake.getDate());

        return listItemView;
    }
}
