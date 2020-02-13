package com.example.satveer_c0769247_labassignment;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PlaceAdapter extends ArrayAdapter {
    Context mcontext;
    int layoutRes;
    List<Places> places;
    DatabaseHelper mDatabase;

    public PlaceAdapter( Context mcontext, int layoutRes, List<Places> places, DatabaseHelper mDatabase) {
        super(mcontext, layoutRes,places);
        this.mcontext = mcontext;
        this.layoutRes = layoutRes;
        this.places = places;
        this.mDatabase = mDatabase;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View v = inflater.inflate(layoutRes, null);
        TextView tvName = v.findViewById(R.id.tv_name);
        TextView tvLongitude = v.findViewById(R.id.tv_longitude);
        TextView tvLatitude = v.findViewById(R.id.tv_latitude);
        TextView tvDate = v.findViewById(R.id.tv_date);

        final Places place = places.get(position);
        tvName.setText(place.getName());
        tvLongitude.setText(place.getlongitude());
        tvLatitude.setText(place.getlatitude());
        tvDate.setText(place.getDate());

        return v;

    }
    private void loadPlaces() {

        Cursor cursor = mDatabase.getAllPlaces();

        if(cursor.moveToFirst()){
            places.clear();
            do{
                places.add(new Places(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                ));
            }while (cursor.moveToNext());

            cursor.close();
        }
        notifyDataSetChanged();



    }

}
