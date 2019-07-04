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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_info);
        ButterKnife.bind(this);
    }

    @Override
    protected <T> void onUiThread(Result<T> result, int resultCode) { }

    @OnClick(R.id.btn_change_pet_status)
    void onClick() {
        ChangePetStatusActivity.startActivityForResult(this);
    }

    public static void startActivityForResult(Context context) {
        Intent intent = new Intent(context, PetInfoActivity.class);
        ((Activity) context).startActivityForResult(intent, CODE_PET_INFO_ACTIVITY);
    }
}
