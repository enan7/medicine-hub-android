package com.example.medic.Fragments;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.medic.Api_Interfaces.OrderInterface;
import com.example.medic.R;
import com.example.medic.Requests.OrderAdddressDto;
import com.example.medic.Requests.OrderRequest;
import com.example.medic.Responses.CartDetailResponse;
import com.example.medic.Responses.OrderResponse;
import com.example.medic.RetrofitClient.RetrofitClient;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddressFragment extends Fragment {

    TextInputEditText receiverName, receiverPhone, nearbyLocation;
    List<Integer> indexesToRender = Arrays.asList(0, 2, 3, 4,8);
    Button submitBtn;
    private RetrofitClient retrofitClient;
    private Double lattitude;
    private Double longitude;
    private String city;
    private String country;
    private   final  int cityIndex  = 4;
    private  final int countryIndex = 8;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_address, container, false);


        final CartDetailResponse cartResponse = (CartDetailResponse) getArguments().getSerializable("cartResponse");

        String apiKey = getString(R.string.api_key);

        if (!Places.isInitialized()) {
            Places.initialize(getContext(), apiKey);
        }

        PlacesClient placesClient = Places.createClient(getContext());

        receiverName = (TextInputEditText) view.findViewById(R.id.receiver_name);
        receiverPhone = (TextInputEditText) view.findViewById(R.id.receiver_phone);
        nearbyLocation = (TextInputEditText) view.findViewById(R.id.nearby_location);
        submitBtn = (Button) view.findViewById(R.id.submit_address);

        /*receiverName.setText(cartResponse.getUserName());*/
        receiverPhone.setText(cartResponse.getPhoneNumber());

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone = receiverPhone.getText().toString().trim();
                String address = nearbyLocation.getText().toString().trim();
                String userName = receiverName.getText().toString().trim();

                OrderRequest orderRequest = new OrderRequest();
                OrderAdddressDto orderAdddressDto = new OrderAdddressDto();
                orderAdddressDto.setPhoneNumber(phone);
                orderAdddressDto.setNearByLocation(address);
                orderAdddressDto.setReceiverName(userName);
               orderAdddressDto.setLattitude(lattitude);
               orderAdddressDto.setLongitude(longitude);
                orderAdddressDto.setCity(city);
                orderAdddressDto.setCountry(country);
                orderRequest.setAddress(orderAdddressDto);
                orderRequest.setCartId(cartResponse.getCartId());

                placeOrder(orderRequest);


            }
        });


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
                startActivityForResult(intent, 100);
            }
        });

        return view;

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
            String address = "";
            // When success
            // initialize place
            Place place = Autocomplete.getPlaceFromIntent(data);
            //Set Address on Location
            this.lattitude = place.getLatLng().latitude;
            this.longitude =  place.getLatLng().longitude;
            if (!place.getName().equals(place.getAddressComponents().asList().get(0).getName())) {
                address += place.getName();
                address += ", ";
            }

            for (Integer index : indexesToRender) {

                address += place.getAddressComponents().asList().get(index).getName();
                address += ", ";
            }
            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
            try {
                List<Address> addressList = geocoder.getFromLocation(
                        lattitude, longitude, 1);
                Address addressInstance = addressList.get(0);
                city  = addressInstance.getLocality();
                country  = addressInstance.getCountryName();

            } catch (IOException e) {
                e.printStackTrace();
            }

            address = address.trim();
            nearbyLocation.setText(address);
           /* //Set locality name
            TV1.setText(String.format("Locality Name : %s",place.getName()));
            //Set Latitude longitude
            TV2.setText(String.valueOf(place.getLatLng()));*/
        } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
            //When error
            //Initialize status
            Status status = Autocomplete.getStatusFromIntent(data);
            //Display toast
            Toast.makeText(getContext(), status.getStatusMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    private void placeOrder(OrderRequest request) {

        try {

            retrofitClient = RetrofitClient.getInstance();
            OrderInterface orderInterface = retrofitClient.getRetrofit().create(OrderInterface.class);
            Call<OrderResponse> call = orderInterface.placeOrder(retrofitClient.getJwtToken(), request);

            call.enqueue(new Callback<OrderResponse>() {
                @Override
                public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {

                    // loadingBar.dismiss();

                    OrderResponse orderResponse = response.body();
/*
                    cartDetailAdapter = new CartDetailAdapter(getActivity(), (ArrayList<CartDetailDTO>) cartDetailResponse.getCartItems(),cartRecyclerView);
*/

//                    LinearLayoutManager llm = new LinearLayoutManager(getActivity());
//                    cartRecyclerView.setLayoutManager(llm);
                    //  categoryAdapter = new CategoryAdapter(data);

//                    cartDetailAdapter = new CategoryAdapter();
//                    cartRecyclerView.setAdapter(adapter);




                }

                @Override
                public void onFailure(Call<OrderResponse> call, Throwable t) {
                    System.out.println("Failed");
                }

            });

        } catch (Exception e) {
            System.out.println(e);

        }
    }



}