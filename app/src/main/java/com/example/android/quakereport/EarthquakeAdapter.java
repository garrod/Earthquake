package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        magnitudeView.setText(decimalFormatter(currentEarthquake.getMagnitude()));

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location);
        TextView secondaryLocationView = (TextView) listItemView.findViewById(R.id.secondary_location);

        String location = currentEarthquake.getLocation();
        int index = location.indexOf("of");
        String secondaryLocation = location.substring(0,index+2);
        String primaryLocation = location.substring(index+2);

        secondaryLocationView.setText(secondaryLocation);
        primaryLocationView.setText(primaryLocation);

        // Find the TextView with ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        // Display the date of the earthquake in that TextView

        Date dateObject = new Date(currentEarthquake.getDate());

        String dateToDisplay = formatDate(dateObject);
        dateView.setText(dateToDisplay);

        TextView timeView = (TextView) listItemView.findViewById(R.id.time);

        String timeToDisplay = formatTime(dateObject);
        timeView.setText(timeToDisplay);

        return listItemView;
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
        return dateFormatter.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("h:mm a");
        return dateFormatter.format(dateObject);
    }

    private String decimalFormatter(double decimal) {
        DecimalFormat decFormatter = new DecimalFormat("0.0");
        return decFormatter.format(decimal);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFlor = (int) Math.floor(magnitude);

        switch (magnitudeFlor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(),magnitudeColorResourceId);
    }
}
