package com.group5.petstroe.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.group5.petstroe.R;
import com.group5.petstroe.apis.PetApi;
import com.group5.petstroe.apis.Result;
import com.group5.petstroe.base.BaseActivity;
import com.group5.petstroe.models.Pet;
import com.group5.petstroe.models.Status;
import com.group5.petstroe.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.group5.petstroe.utils.ActivityUtils.CODE_CHANGE_PET_STATUS_ACTIVITY;
import static com.group5.petstroe.apis.Constans.CODE_PET_CHANGE_PET_STATUS_API;

public class ChangePetStatusActivity extends BaseActivity {

    @BindView(R.id.iv_pet_image) ImageView ivPetImage;
    @BindView(R.id.et_pet_price) EditText etPetPrice;
    @BindView(R.id.et_pet_remark) EditText etPetRemark;
    @BindView(R.id.btn_sale) Button btnSale;

    private Pet pet = null;
    private String petPrice;
    private String petRemark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pet_status);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        pet = (Pet)intent.getExtras().get("pet");
        etPetPrice.setText(pet.price);
    }

    @Override
    protected <T> void onUiThread(Result<T> result, int resultCode) {
        switch (resultCode) {
            case CODE_PET_CHANGE_PET_STATUS_API:
                if (result.isOk()) {
                    Status status = (Status) result.get();
                    finishActivityWithResult(status.status == 1);
                }
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.btn_sale)
    void onClick() {
        if (isInfoOk()) {
            PetApi.INSTANCE.changePetStatus(pet.id, 1, pet.remark, pet.price, this);
        }
    }

    private boolean isInfoOk() {
        petPrice = etPetPrice.getText().toString().trim();
        if (StringUtils.isNullOrEmpty(petPrice)) {
            shortToast("请输入宠物价格");
            return false;
        }
        petRemark = etPetRemark.getText().toString().trim();
        if (StringUtils.isNullOrEmpty(petRemark)) {
            petRemark = "暂无备注";
        }
        return true;
    }

    public static void startActivityForResult(Context context, Pet pet) {
        Intent intent = new Intent(context, ChangePetStatusActivity.class);
        intent.putExtra("pet", pet);
        ((Activity) context).startActivityForResult(intent, CODE_CHANGE_PET_STATUS_ACTIVITY);
    }

    private void finishActivityWithResult(boolean status) {
        Intent intent = new Intent();
        intent.putExtra("status", status);
        setResult(RESULT_OK, intent);
        finish();
    }
}
