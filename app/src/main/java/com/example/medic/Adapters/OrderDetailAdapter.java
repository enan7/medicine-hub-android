package com.example.medic.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.Holders.OrderDetailHolder;
import com.example.medic.Holders.OrderListHolder;
import com.example.medic.R;
import com.example.medic.Responses.OrderedItemsDto;
import com.example.medic.Responses.OrdersDto;

import java.util.ArrayList;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailHolder> {

    Context c;
    ArrayList<OrderedItemsDto> orders;

    public OrderDetailAdapter(Context c, ArrayList<OrderedItemsDto> orders)
    {
        this.c = c;
        this.orders = orders;
    }


    @NonNull
    @Override
    public OrderDetailHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_medicine_layout, null);
        return new OrderDetailHolder(view);
    }





    @Override
    public void onBindViewHolder(@NonNull final OrderDetailHolder orderDetailHolder, final int i) {


      /*  if (orders.get(i). != null) {

            orderDetailHolder.getMedicineImage().setImageBitmap(medicines.get(i).getImage());
        }*/

      orderDetailHolder.getMedicineName().setText(orders.get(i).getMedicineName());
      orderDetailHolder.getMedicineQuantity().setText("Qty: "+String.valueOf(orders.get(i).getNoOfOrders()));
      orderDetailHolder.getMedicinePrice().setText("Price: "+orders.get(i).getMedicinePrice().toString());



    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
