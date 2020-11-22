package com.example.medic.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.Fragments.MedicineDetailFragment;
import com.example.medic.Fragments.OrderDetailFragment;
import com.example.medic.Holders.OrderListHolder;
import com.example.medic.R;
import com.example.medic.Responses.OrdersDto;

import java.util.ArrayList;
import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListHolder> {

    Context c;

    ArrayList<OrdersDto> orders;

    public OrderListAdapter(Context c, ArrayList<OrdersDto> orders) {
        this.c = c;
        this.orders = orders;
    }

    @NonNull
    @Override

    public OrderListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_list_layout, null);
        return new OrderListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OrderListHolder statusholder, final int i) {

        statusholder.getRefNumber().setText(orders.get(i).getReferenceNumber());
        statusholder.getCreatedAt().setText(orders.get(i).getCreatedOn());
        statusholder.getTotalItems().setText((String.valueOf(orders.get(i).getTotalItems())));
        statusholder.getTotalPrice().setText(orders.get(i).getTotalPrice().toString());
        statusholder.getStatus().setText(orders.get(i).getStatus());

        statusholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bundle bundle=new Bundle();
                bundle.putLong("OrderId", orders.get(i).getOrderId());

                AppCompatActivity activity = (AppCompatActivity)v.getContext();
                OrderDetailFragment orderDetailFragment = new OrderDetailFragment();
                orderDetailFragment.setArguments(bundle);
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, orderDetailFragment)
                        .addToBackStack(null)
                        .commit();


            }
        });

    }

    public List<OrdersDto> getrOrderList(){
        return this.orders;
    }



    @Override
    public int getItemCount() {
        return orders.size();
    }
}
