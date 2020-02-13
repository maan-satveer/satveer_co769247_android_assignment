package com.example.satveer_c0769247_labassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PlacesActivity extends AppCompatActivity {
    DatabaseHelper mDataBase;
    List<Places> placesList;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
        listView = findViewById(R.id.lvplaces);
        placesList = new ArrayList<>();

        mDataBase = new DatabaseHelper(this);
        loadPlaces();
    }
    private void loadPlaces(){
        Cursor cursor = mDataBase.getAllPlaces();
        if(cursor.moveToFirst()){
            do {
                placesList.add(new Places(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                ));


            }while (cursor.moveToNext());
            cursor.close();
            //show item in a listView
            //we use a custom adapter to show employees

            PlaceAdapter employeeAdapter = new PlaceAdapter(this, R.layout.list_places, placesList, mDataBase);
            listView.setAdapter(employeeAdapter);

    }
}
}
