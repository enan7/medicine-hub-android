package com.example.medic.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.Activity.Home;
import com.example.medic.Adapters.CartDetailAdapter;
import com.example.medic.Api_Interfaces.CartInterface;
import com.example.medic.R;
import com.example.medic.Responses.CartDetailDTO;
import com.example.medic.Responses.CartItemDeleteResponse;
import com.example.medic.RetrofitClient.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteCartItemDialogFragment extends DialogFragment {

    private CartItemDeleteResponse cartItemDeleteResponse;
    private RetrofitClient retrofitClient;
    private CartInterface cartInterface;
    private List<CartDetailDTO> cartItems;
    private CartDetailAdapter mAdapter;
    private  RecyclerView cartRecyclerView;
//    public DeleteCartItemDialogFragment(){
//
//    }
    public DeleteCartItemDialogFragment(List<CartDetailDTO> cartItems, CartDetailAdapter mAdapter, RecyclerView cartRecyclerView){
        this.cartItems = cartItems;
        this.mAdapter = mAdapter;
        this.cartRecyclerView = cartRecyclerView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@NonNull Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Delete Cart Item");
        builder.setMessage("Are you sure you want to delete this item from cart? ");
        builder.setCancelable(false);


        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                if (getActivity() != null) {
                    Long itemId = (Long) getArguments().getSerializable("ItemID");

                    DeleteCartItem(itemId);

                    Home activity = (Home) getActivity();
                    activity.deleteCartItem();



//


//                    CartDetailFragment cartDetailFragment = new CartDetailFragment();
//                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                    transaction.replace(R.id.fragment_container, cartDetailFragment);
//                    transaction
//                            .addToBackStack(null)
//                            .commit();
//                    Toast.makeText(getActivity(), "Item Deleted Successfully", Toast.LENGTH_SHORT).show();


                }

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(), "Item is not deleted", Toast.LENGTH_SHORT).show();

            }
        });
        AlertDialog alertDialog = builder.create();
        return alertDialog;
    }

    private void DeleteCartItem(final long itemId) {

        try {
            retrofitClient = RetrofitClient.getInstance();
            cartInterface = retrofitClient.getRetrofit().create(CartInterface.class);
            Call<CartItemDeleteResponse> call = cartInterface.deleteCartItem(retrofitClient.getJwtToken(), itemId);

            call.enqueue(new Callback<CartItemDeleteResponse>() {
                @Override
                public void onResponse(Call<CartItemDeleteResponse> call, Response<CartItemDeleteResponse> response) {

                    // loadingBar.dismiss();

                    cartItemDeleteResponse = response.body();

                    if(cartItemDeleteResponse.getResponseCode().equals("00")){
                        int index = 0;
                        for(CartDetailDTO cartDetail:cartItems){

                            if(cartDetail.getItemId() == itemId){
                                cartItems.remove(cartDetail);
                                cartRecyclerView.removeViewAt(index);
                                mAdapter.notifyItemRemoved(index);
                                mAdapter.notifyItemRangeChanged(index,cartItems.size());
                                break;
                            }
                            index++;
                        }
                    }


                }

                @Override
                public void onFailure(Call<CartItemDeleteResponse> call, Throwable t) {
                    // loadingBar.dismiss();
                    System.out.println("Failed");
                }
            });
        } catch (Exception e) {
            System.out.println(e);
        }


    }
}
