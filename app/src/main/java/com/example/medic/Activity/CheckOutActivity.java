package com.example.medic.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medic.R;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class CheckOutActivity extends AppCompatActivity {

    EditText location;
    TextView TV1, TV2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        location = findViewById(R.id.location);
        TV1 = findViewById(R.id.Text_view);
        TV2 = findViewById(R.id.Text_view2);

        // Initialize Places
        Places.initialize(getApplicationContext(),"AIzaSyBn1zCF2UAprbI_LpQ8H6rl8sMzjvwRaXU");

        //Set EditText non focusable

        location.setFocusable(false);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initialize place field list
                List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS
                ,Place.Field.LAT_LNG,Place.Field.NAME);
                //Create Intent
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY
                        ,fieldList).build(CheckOutActivity.this);
                // Start Activity Result
                startActivityForResult(intent,100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && requestCode == RESULT_OK)
        {
            // When success
            // initialize place
            Place place = Autocomplete.getPlaceFromIntent(data);
            //Set Address on Location
            location.setText(place.getAddress());
            //Set locality name
            TV1.setText(String.format("Locality Name : %s",place.getName()));
            //Set Latitude longitude
            TV2.setText(String.valueOf(place.getLatLng()));
        }
        else if (resultCode == AutocompleteActivity.RESULT_ERROR)
            {
                //When error
                //Initialize status
                Status status = Autocomplete.getStatusFromIntent(data);
                //Display toast
                Toast.makeText(getApplicationContext(),status.getStatusMessage(), Toast.LENGTH_SHORT).show();

            }
    }
}