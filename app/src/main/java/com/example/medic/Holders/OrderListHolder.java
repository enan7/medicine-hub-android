package com.example.medic.Holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.R;

public class OrderListHolder extends RecyclerView.ViewHolder {

    private TextView refNumber, createdAt, totalItems, totalPrice, status;

    public OrderListHolder(@NonNull View itemView) {
        super(itemView);

        refNumber = itemView.findViewById(R.id.status_ref_Number);
        createdAt = itemView.findViewById(R.id.status_created_at);
        totalItems = itemView.findViewById(R.id.status_total_items);
        totalPrice = itemView.findViewById(R.id.status_total_price);
        status = itemView.findViewById(R.id.status_status);

    }

    public TextView getRefNumber() {
        return refNumber;
    }

    public void setRefNumber(TextView refNumber) {
        this.refNumber = refNumber;
    }

    public TextView getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(TextView createdAt) {
        this.createdAt = createdAt;
    }

    public TextView getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(TextView totalItems) {
        this.totalItems = totalItems;
    }

    public TextView getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(TextView totalPrice) {
        this.totalPrice = totalPrice;
    }

    public TextView getStatus() {
        return status;
    }

    public void setStatus(TextView status) {
        this.status = status;
    }
}
