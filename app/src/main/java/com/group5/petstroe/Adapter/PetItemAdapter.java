package com.group5.petstroe.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group5.petstroe.R;
import com.group5.petstroe.models.Pet;

import java.util.ArrayList;
import java.util.List;

public class PetItemAdapter extends RecyclerView.Adapter<PetItemAdapter.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPetImage;
        TextView tvPetName;
        TextView tvPetSpecies;
        TextView tvPetPrice;
        TextView tvPetOwner;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPetImage = itemView.findViewById(R.id.iv_pet_image);
            tvPetName = itemView.findViewById(R.id.tv_pet_name);
            tvPetSpecies = itemView.findViewById(R.id.tv_pet_species);
            tvPetPrice = itemView.findViewById(R.id.tv_pet_price);
            tvPetOwner = itemView.findViewById(R.id.tv_pet_owner);
        }
    }

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    private List<Pet> petList = new ArrayList<>();
    private onItemClickListener onItemClickListener;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pet, parent, false);
        view.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick((int)v.getTag());
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pet pet = petList.get(position);
        holder.tvPetName.setText(pet.name);
        holder.tvPetSpecies.setText(pet.getSpecies());
        holder.tvPetPrice.setText(pet.price + "");
        holder.tvPetOwner.setText(pet.owner.name);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return petList == null ? 0 : petList.size();
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        if (listener != null) {
            onItemClickListener = listener;
        }
    }

    public void updateList(List<Pet> list) {
        petList = list;
        notifyDataSetChanged();
    }

    public Pet getPet(int position) {
        return petList.get(position);
    }
}
