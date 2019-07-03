package com.group5.petstroe.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group5.petstroe.Adapter.PetItemAdapter;
import com.group5.petstroe.R;
import com.group5.petstroe.apis.Result;
import com.group5.petstroe.base.BaseActivity;
import com.group5.petstroe.models.Pet;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.group5.petstroe.utils.ActivityUtils.CODE_MY_PET_ACTIVITY;

public class MyPetActivity extends BaseActivity {

    @BindView(R.id.tv_pet_number) TextView tvPetNumber;
    @BindView(R.id.rv_pets_list) RecyclerView rvPetsList;
    @BindView(R.id.btn_create_pet) Button btnCreatePet;
    private PetItemAdapter petItemAdapter = new PetItemAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pet);
        ButterKnife.bind(this);
        petItemAdapter.setOnItemClickListener(new PetItemAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                PetInfoActivity.startActivityForResult(MyPetActivity.this);
            }
        });
        rvPetsList.setAdapter(petItemAdapter);
        rvPetsList.setLayoutManager(new LinearLayoutManager(this));
        onUiThread(null, 0);
    }

    @Override
    protected <T> void onUiThread(Result<T> result, int resultCode) {
        List<Pet> pets = new ArrayList<>();
        for (int i = 0; i < 10 ;i++) {
            pets.add(new Pet());
        }
        petItemAdapter.updateList(pets);
    }

    @OnClick(R.id.btn_create_pet)
    void onClick() {
        CreatePetActivity.startActivityForResult(this);
    }

    public static void startActivityForResult(Context context) {
        Intent intent = new Intent(context, MyPetActivity.class);
        ((Activity) context).startActivityForResult(intent, CODE_MY_PET_ACTIVITY);
    }
}
