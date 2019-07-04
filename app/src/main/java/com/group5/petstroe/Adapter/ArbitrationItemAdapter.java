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

public class ArbitrationItemAdapter extends RecyclerView.Adapter<ArbitrationItemAdapter.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPetImage;
        TextView tvPetName;
        TextView tvPetSpecies;
        TextView tvPetPrice;
        TextView tvStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPetImage = itemView.findViewById(R.id.iv_pet_image);
            tvPetName = itemView.findViewById(R.id.tv_pet_name);
            tvPetSpecies = itemView.findViewById(R.id.tv_pet_price);
            tvPetPrice = itemView.findViewById(R.id.tv_pet_price);
            tvStatus = itemView.findViewById(R.id.tv_arbitration_status);
        }
    }

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    private List<Order> arbitrationList = new ArrayList<>();
    private onItemClickListener onItemClickListener;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_arbitration, parent, false);
        view.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick((int)v.getTag());
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = arbitrationList.get(position);
        holder.tvPetName.setText(order.pet.name);
        holder.tvPetSpecies.setText(order.pet.getSpecies());
        holder.tvPetPrice.setText(order.price +"");
        holder.tvStatus.setText(order.getStatus());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return arbitrationList == null ? 0 : arbitrationList.size();
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        if (listener != null) {
            onItemClickListener = listener;
        }
    }

    public void updateList(List<Order> list) {
        arbitrationList = list;
        notifyDataSetChanged();
    }
}
