package com.example.medic.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.medic.Activity.Home;
import com.example.medic.Api_Interfaces.CartInterface;
import com.example.medic.R;
import com.example.medic.Responses.CartDeleteResponse;
import com.example.medic.RetrofitClient.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteCartDialogFragment extends DialogFragment {

    private CartDeleteResponse cartDeleteResponse;
    private RetrofitClient retrofitClient;
    private CartInterface cartInterface;





    @NonNull
    @Override
    public Dialog onCreateDialog(@NonNull Bundle savedInstanceState)
    {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Delete Cart");
        builder.setMessage("Are you sure you want to delete whole cart? ");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                Toast.makeText(getActivity(), "Cart Deleted Successfully", Toast.LENGTH_SHORT).show();

                if (getActivity()!=null)
                {
                     DeleteFullCart();
                Home activity = (Home) getActivity();
                activity.emptyCart();

                CategoryFragment categoryFragment = new CategoryFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, categoryFragment);
                transaction
                        .addToBackStack(null)
                        .commit();
                }
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(), "Cart is not deleted", Toast.LENGTH_SHORT).show();

            }
        });
        AlertDialog alertDialog = builder.create();
        return alertDialog;
    }

    private void DeleteFullCart() {

        try {
            retrofitClient = RetrofitClient.getInstance();
            cartInterface = retrofitClient.getRetrofit().create(CartInterface.class);
            Call<CartDeleteResponse> call = cartInterface.deleteCart(retrofitClient.getJwtToken());

            call.enqueue(new Callback<CartDeleteResponse>() {
                @Override
                public void onResponse(Call<CartDeleteResponse> call, Response<CartDeleteResponse> response) {

                    cartDeleteResponse = response.body();

                }

                @Override
                public void onFailure(Call<CartDeleteResponse> call, Throwable t) {
                    // loadingBar.dismiss();
                    System.out.println("Failed");
                }
            });
        } catch (Exception e) {
            System.out.println(e);
        }


    }
}
