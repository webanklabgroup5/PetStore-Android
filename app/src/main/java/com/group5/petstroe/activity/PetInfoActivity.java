package com.group5.petstroe.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.group5.petstroe.R;
import com.group5.petstroe.apis.Result;
import com.group5.petstroe.base.BaseActivity;
import com.group5.petstroe.models.Pet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.group5.petstroe.utils.ActivityUtils.CODE_PET_INFO_ACTIVITY;

public class PetInfoActivity extends BaseActivity {

    @BindView(R.id.iv_pet_image) ImageView ivPetImage;
    @BindView(R.id.tv_pet_name) TextView tvPetName;
    @BindView(R.id.tv_pet_status) TextView tvPetStatus;
    @BindView(R.id.tv_pet_species) TextView tvPetSpecies;
    @BindView(R.id.tv_pet_birthday) TextView tvPetBirthday;
    @BindView(R.id.tv_pet_owner) TextView tvPetOwner;
    @BindView(R.id.tv_pet_description) TextView tvPetDescription;
    @BindView(R.id.btn_change_pet_status) Button btnChangePetStatus;

    private boolean isFromMyPet = false;
    private Pet pet = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_info);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        isFromMyPet = intent.getBooleanExtra("isFromMyPet", false);
        pet = (Pet)intent.getExtras().get("pet");
        btnChangePetStatus.setText(isFromMyPet ? pet.status == 1 ? "上架" : "下架" : "购买");
    }

    @Override
    protected <T> void onUiThread(Result<T> result, int resultCode) { }

    @OnClick(R.id.btn_change_pet_status)
    void onClick() {
        if (isFromMyPet) {
            if (pet.status == 1) {
                // 下架
            } else {
                ChangePetStatusActivity.startActivityForResult(this, pet);
            }
        } else {
            // 购买
        }
    }

    public static void startActivityForResult(Context context, boolean isFromMyPet, Pet pet) {
        Intent intent = new Intent(context, PetInfoActivity.class);
        intent.putExtra("isFromMyPet", isFromMyPet);
        intent.putExtra("pet", pet);
        ((Activity) context).startActivityForResult(intent, CODE_PET_INFO_ACTIVITY);
    }

    private void finishActivityWithResult() {}
}
