package com.example.medic.Fragments;

import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.medic.Activity.Home;
import com.example.medic.Api_Interfaces.AddToCartInterface;
import com.example.medic.R;
import com.example.medic.Requests.AddToCartRequest;
import com.example.medic.Responses.AddToCartResponse;
import com.example.medic.Responses.MedicineResponse;
import com.example.medic.RetrofitClient.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MedicineDetailFragment extends Fragment {

    private TextView medicineName, medicinePrice, medicineDiscount, medicineOldprice,
            medicineDescription, medicineQuantity, medicineUnit, medicineManufacturer;
    private ImageView medicineImage;
    private LinearLayout priceDiscount;
    private Button AddToCart;
    private Long medicineId;
    private RetrofitClient retrofitClient;
    private AddToCartInterface addToCartInterface;
    private ElegantNumberButton qtyButton;
    RelativeLayout progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medicine_detail, container, false);

        /*MedicineResponse medicines = (MedicineResponse) getIntent().getSerializableExtra("Med");*/
        MedicineResponse medicines = (MedicineResponse) getArguments().getSerializable("Med");

        medicineName = (TextView) view.findViewById(R.id.detail_title);
        medicineDescription = (TextView) view.findViewById(R.id.detail_description);
        medicineDiscount =  (TextView) view.findViewById(R.id.detail_dicount);
        medicineOldprice = (TextView) view.findViewById(R.id.detail_oldprice);
        medicinePrice = (TextView) view.findViewById(R.id.detail_newprice);
        medicineImage = (ImageView) view.findViewById(R.id.detail_image);
        medicineQuantity = (TextView) view.findViewById(R.id.detail_qty);
        medicineUnit = (TextView) view.findViewById(R.id.detail_unit);
        medicineManufacturer = (TextView) view.findViewById(R.id.detail_manufacture);
        priceDiscount = (LinearLayout) view.findViewById(R.id.price_discount);
        AddToCart = (Button) view.findViewById(R.id.add_to_cart);
        qtyButton = (ElegantNumberButton)view.findViewById(R.id.elegant_btn);
        progressBar = (RelativeLayout) view.findViewById(R.id.progressbar);

        medicineName.setText(medicines.getMedicineName());
        medicinePrice.setText("Rs. "+medicines.getCalculatedPrice().toString());
        medicineDiscount.setText("Discount: "+(String.valueOf(medicines.getDiscount())+"%"));
        medicineDescription.setText("Description: "+(medicines.getDescription()));
        medicineImage.setImageBitmap(medicines.getImage());
        medicineOldprice.setText("Old Price: "+(medicines.getPrice().toString()));
        medicineOldprice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        medicineQuantity.setText(("("+medicines.getQuantity().toString()+")"));
        medicineUnit.setText(medicines.getUnit());
        medicineManufacturer.setText("By ("+(medicines.getManufacturer())+")");
        medicineId = medicines.getMedicineId();



        AddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddToCart.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                AddToCartRequest addToCartRequest = new AddToCartRequest();
                addToCartRequest.setMedicineId(medicineId);
                addToCartRequest.setQuantity(qtyButton.getNumber());

                try {
                    retrofitClient = RetrofitClient.getInstance();
                    addToCartInterface = retrofitClient.getRetrofit().create(AddToCartInterface.class);
                    Call<AddToCartResponse> call = addToCartInterface.addToCart(retrofitClient.getJwtToken(),addToCartRequest);

                    call.enqueue(new Callback<AddToCartResponse>() {
                        @Override
                        public void onResponse(Call<AddToCartResponse> call, Response<AddToCartResponse> response) {
                            // loadingBar.dismiss();
                            AddToCartResponse addToCartResponse = response.body();
                            //   Toast.makeText(SignUp.this,registerUserResponse.getResponseMessage(),Toast.LENGTH_LONG).show();
                            System.out.println(addToCartResponse.getResponseCode());


                            if (addToCartResponse.getResponseCode().equals("00")) {
                                progressBar.setVisibility(View.GONE);
                                AddToCart.setVisibility(View.VISIBLE);
                                Toast.makeText(getActivity(), "Item Added successfully!", Toast.LENGTH_SHORT).show();
                                doIncrease();

                                /*loadingBar.dismiss();*/
                               /* Intent intent = new Intent(getApplicationContext(), Home.class);
                                startActivity(intent);
                                finish();*/
                            }

                            else {
                                /*loadingBar.dismiss();*/
                                progressBar.setVisibility(View.GONE);
                                AddToCart.setVisibility(View.VISIBLE);
                                Toast.makeText(getActivity(), addToCartResponse.getResponseMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<AddToCartResponse> call, Throwable t) {
                            // loadingBar.dismiss();
                            System.out.println("Failed");
                        }
                    });
                } catch (Exception e){
                    System.out.println(e);
                }


            }
        });



        if (String.valueOf(medicines.getDiscount()) .equals("0") )
        {
            priceDiscount.setVisibility(View.GONE);
        }
        return view;
    }

    private void doIncrease() {
        Home toolbar = new Home();
        toolbar.increaseCount();
    }

    @Override
    public void onResume() {
        super.onResume();
        Home activity = (Home) getActivity();
        if (activity != null) {
            activity.showBackButton();
            activity.hideDrawerButton();
        }

    }
}