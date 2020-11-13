package com.example.medic.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.Holders.OrderListHolder;
import com.example.medic.R;
import com.example.medic.Responses.OrdersDto;

import java.util.ArrayList;

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
    public void onBindViewHolder(@NonNull OrderListHolder statusholder, final int i) {

        statusholder.getRefNumber().setText(orders.get(i).getReferenceNumber());
        statusholder.getCreatedAt().setText(orders.get(i).getCreatedOn());
        statusholder.getTotalItems().setText((String.valueOf(orders.get(i).getTotalItems())));
        statusholder.getTotalPrice().setText(orders.get(i).getTotalPrice().toString());
        statusholder.getStatus().setText(orders.get(i).getStatus());


    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
