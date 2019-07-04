package com.group5.petstroe.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group5.petstroe.R;
import com.group5.petstroe.models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPetImage;
        TextView tvPetName;
        TextView tvPetSpecies;
        TextView tvPetPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPetImage = itemView.findViewById(R.id.iv_pet_image);
            tvPetName = itemView.findViewById(R.id.tv_pet_name);
            tvPetSpecies = itemView.findViewById(R.id.tv_pet_price);
            tvPetPrice = itemView.findViewById(R.id.tv_pet_price);
        }
    }

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    private List<Order> orderList = new ArrayList<>();
    private onItemClickListener onItemClickListener;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        view.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick((int)v.getTag());
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = orderList.get(position);
//        holder.tvPetName.setText(order.pet.name);
//        holder.tvPetSpecies.setText(order.pet.getSpecies());
//        holder.tvPetPrice.setText(order.price);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        if (listener != null) {
            onItemClickListener = listener;
        }
    }

    public void updateList(List<Order> list) {
        orderList = list;
        notifyDataSetChanged();
    }

    public Order getOrder(int position) {
        return orderList.get(position);
    }
}
