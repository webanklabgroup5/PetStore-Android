package com.group5.petstroe.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.group5.petstroe.R;
import com.group5.petstroe.apis.PetApi;
import com.group5.petstroe.apis.Result;
import com.group5.petstroe.apis.StoreApi;
import com.group5.petstroe.base.BaseActivity;
import com.group5.petstroe.models.Pet;
import com.group5.petstroe.models.Status;
import com.group5.petstroe.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.group5.petstroe.apis.Constans.CODE_PET_CHANGE_PET_STATUS_API;
import static com.group5.petstroe.utils.ActivityUtils.CODE_CHANGE_PET_STATUS_ACTIVITY;
import static com.group5.petstroe.utils.ActivityUtils.CODE_PET_INFO_ACTIVITY;
import static com.group5.petstroe.apis.Constans.CODE_STORE_BUY_PET_API;

public class PetInfoActivity extends BaseActivity {

    @BindView(R.id.iv_pet_image) ImageView ivPetImage;
    @BindView(R.id.tv_pet_name) TextView tvPetName;
    @BindView(R.id.tv_pet_status) TextView tvPetStatus;
    @BindView(R.id.tv_pet_species) TextView tvPetSpecies;
    @BindView(R.id.tv_pet_birthday) TextView tvPetBirthday;
    @BindView(R.id.tv_pet_owner) TextView tvPetOwner;
    @BindView(R.id.tv_pet_description) TextView tvPetDescription;
    @BindView(R.id.btn_change_pet_status) Button btnChangePetStatus;

    /**
     * 是否从“我的宠物”页面跳转来的（默认从市场页面跳转）
     */
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
        tvPetStatus.setText(isFromMyPet ? pet.status == 1 ? "上架" : "下架" : "上架");
        btnChangePetStatus.setText(isFromMyPet ? pet.status == 1 ? "下架" : "上架" : "购买");
        ImageUtils.loadImageFromUrl(this, ivPetImage, pet.url);
        tvPetName.setText(pet.name);
        tvPetSpecies.setText(pet.getSpecies());
        tvPetBirthday.setText(pet.birthday);
        tvPetOwner.setText(pet.owner.name);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case CODE_CHANGE_PET_STATUS_ACTIVITY:
                /**
                 * 宠物成功上架则关闭宠物信息页面
                 */
                boolean status = data.getBooleanExtra("status", false);
                if (status) {
                    zlog("成拉成啦");
                    finishActivityWithResult(true);
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected <T> void onUiThread(Result<T> result, int resultCode) {
        switch (resultCode) {
            /**
             * 购买宠物回调
             */
            case CODE_STORE_BUY_PET_API:
                if (result.isOk()) {
                    Status status = (Status) result.get();
                    finishActivityWithResult(status.status == 1);
                }
                break;
            /**
             * 上/下架宠物回调
             */
            case CODE_PET_CHANGE_PET_STATUS_API:
                if (result.isOk()) {
                    zlog("宠物下架 ok");
                    Status status = (Status) result.get();
                    finishActivityWithResult(status.status == 1);
                } else {
                    zlog("宠物下架 error");
                }
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.btn_change_pet_status)
    void onClick() {
        if (isFromMyPet) {
            if (pet.status == 1) { // 下架宠物
                PetApi.INSTANCE.changePetStatus(pet.id, 0, pet.remark, pet.price, this);
            } else { // 上架宠物
                ChangePetStatusActivity.startActivityForResult(this, pet);
            }
        } else { // 购买宠物
            StoreApi.INSTANCE.buyPet(pet.id, this);
        }
    }

    public static void startActivityForResult(Context context, boolean isFromMyPet, Pet pet) {
        Intent intent = new Intent(context, PetInfoActivity.class);
        intent.putExtra("isFromMyPet", isFromMyPet);
        intent.putExtra("pet", pet);
        ((Activity) context).startActivityForResult(intent, CODE_PET_INFO_ACTIVITY);
    }

    private void finishActivityWithResult(boolean status) {
        Intent intent = new Intent();
        intent.putExtra("status", status);
        setResult(RESULT_OK, intent);
        finish();
    }
}
