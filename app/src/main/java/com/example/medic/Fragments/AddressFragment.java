package com.example.medic.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medic.Activity.CheckOutActivity;
import com.example.medic.R;
import com.example.medic.Responses.CartDetailResponse;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;
import java.util.List;


public class AddressFragment extends Fragment {

    TextInputEditText receiverName, receiverPhone, nearbyLocation;
    List <Integer> indexesToRender = Arrays.asList(0,2,3,4);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_address, container, false);


        CartDetailResponse cartResponse = (CartDetailResponse) getArguments().getSerializable("cartResponse");

        String apiKey = getString(R.string.api_key);

        if (!Places.isInitialized()) {
            Places.initialize(getContext(), apiKey);
        }

        PlacesClient placesClient = Places.createClient(getContext());

        receiverName = (TextInputEditText) view.findViewById(R.id.receiver_name);
        receiverPhone = (TextInputEditText) view.findViewById(R.id.receiver_phone);
        nearbyLocation = (TextInputEditText) view.findViewById(R.id.nearby_location);

        receiverName.setText(cartResponse.getUserName());
        receiverPhone.setText(cartResponse.getPhoneNumber());



        //Set EditText non focusable

        nearbyLocation.setFocusable(false);

        nearbyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initialize place field list
                List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS
                        ,Place.Field.LAT_LNG,Place.Field.NAME,Place.Field.ADDRESS_COMPONENTS,Place.Field.ADDRESS);
                //Create Intent

                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY
                        ,fieldList).setCountry("PK")
                        .build(getContext());
                // Start Activity Result
                startActivityForResult(intent,100);
            }
        });

       return view;

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 )
        {
            String address = "";
            // When success
            // initialize place
            Place place = Autocomplete.getPlaceFromIntent(data);
            //Set Address on Location
//            place.getLatLng().latitude;
//            place.getLatLng().longitude;
            if(!place.getName().equals(place.getAddressComponents().asList().get(0).getName())){
                address+=place.getName();
                address+=", ";
            }

            for(Integer index:indexesToRender){

                address+=place.getAddressComponents().asList().get(index).getName();
                address+=", ";
            }
            address = address.trim();
            nearbyLocation.setText(address);
           /* //Set locality name
            TV1.setText(String.format("Locality Name : %s",place.getName()));
            //Set Latitude longitude
            TV2.setText(String.valueOf(place.getLatLng()));*/
        }
        else if (resultCode == AutocompleteActivity.RESULT_ERROR)
        {
            //When error
            //Initialize status
            Status status = Autocomplete.getStatusFromIntent(data);
            //Display toast
            Toast.makeText(getContext(),status.getStatusMessage(), Toast.LENGTH_SHORT).show();

        }
    }
}