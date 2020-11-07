package com.example.medic.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.Activity.Home;
import com.example.medic.Adapters.CartDetailAdapter;
import com.example.medic.Api_Interfaces.CartInterface;
import com.example.medic.R;
import com.example.medic.Responses.CartDetailDTO;
import com.example.medic.Responses.CartDetailResponse;
import com.example.medic.RetrofitClient.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartDetailFragment extends Fragment {


    private CartDetailAdapter cartDetailAdapter;
    private RetrofitClient retrofitClient;
    private CartInterface cartInterface;
    private CartDetailResponse cartDetailResponse;
    RecyclerView cartRecyclerView;
    ProgressBar progressBar;
    private static TextView totalPrice;
    public  TextView      deleteCart;
    private Button checkOut;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart_detail, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        totalPrice = (TextView) view.findViewById(R.id.total_price);
        checkOut = (Button)view.findViewById(R.id.checkout_btn);

        deleteCart = (TextView) view.findViewById(R.id.delete_cart);

        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  Intent i = new Intent(getActivity(), CheckOutActivity.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0, 0);*/

                Bundle bundle = new Bundle();
                bundle.putSerializable("cartResponse", (cartDetailResponse));

                AddressFragment addressFragment = new AddressFragment();
                addressFragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, addressFragment, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        deleteCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    FragmentManager manager = getFragmentManager();
                    DeleteCartDialogFragment myDialogFragment = new DeleteCartDialogFragment();
                    myDialogFragment.show(manager,"MyDialogFragment");


            }
        });




/*
        progressBar = (RelativeLayout) view.findViewById(R.id.progressbar);
*/

        cartRecyclerView = (RecyclerView) view.findViewById(R.id.cart_item_recyclerview);

        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        cartRecyclerView.setHasFixedSize(true);

        renderCartDetailList();

        return view;
    }







    private void renderCartDetailList() {


        try {
            retrofitClient = RetrofitClient.getInstance();
            cartInterface = retrofitClient.getRetrofit().create(CartInterface.class);
            Call<CartDetailResponse> call = cartInterface.getCart(retrofitClient.getJwtToken());
            progressBar.setVisibility(View.VISIBLE);

            call.enqueue(new Callback<CartDetailResponse>() {
                @Override
                public void onResponse(Call<CartDetailResponse> call, Response<CartDetailResponse> response) {

                    // loadingBar.dismiss();

                    cartDetailResponse = response.body();
                    cartDetailAdapter = new CartDetailAdapter(getActivity(), (ArrayList<CartDetailDTO>) cartDetailResponse.getCartItems(),cartRecyclerView);

//                    LinearLayoutManager llm = new LinearLayoutManager(getActivity());
//                    cartRecyclerView.setLayoutManager(llm);
                    //  categoryAdapter = new CategoryAdapter(data);

//                    cartDetailAdapter = new CategoryAdapter();
//                    cartRecyclerView.setAdapter(adapter);



                    cartRecyclerView.setAdapter(cartDetailAdapter);
                    progressBar.setVisibility(View.GONE);

                    setTotalPrice(cartDetailResponse.getTotalPrice());
                    cartDetailAdapter.notifyDataSetChanged();


/*
                    progressBar.setVisibility(View.GONE);
*/


                    //   Toast.makeText(SignUp.this,registerUserResponse.getResponseMessage(),Toast.LENGTH_LONG).show();


                }

                @Override
                public void onFailure(Call<CartDetailResponse> call, Throwable t) {
                    System.out.println("Failed");
                }
            });
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        Home activity = (Home) getActivity();
     /*   activity.setCartCount(0);*/

        if (activity != null) {
            activity.showBackButton();
            activity.hideDrawerButton();
        }

    }


    public static TextView getTotalPrice() {
        return totalPrice;
    }

    public static void setTotalPrice(Double price) {
        totalPrice.setText("Total Price= Rs. "+String.valueOf(price));

    }
}