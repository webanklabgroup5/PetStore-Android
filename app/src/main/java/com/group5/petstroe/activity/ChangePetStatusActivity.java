package com.group5.petstroe.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.group5.petstroe.R;
import com.group5.petstroe.apis.Result;
import com.group5.petstroe.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.group5.petstroe.utils.ActivityUtils.CODE_CHANGE_PET_STATUS_ACTIVITY;

public class ChangePetStatusActivity extends BaseActivity {

    @BindView(R.id.iv_pet_image) ImageView ivPetImage;
    @BindView(R.id.et_pet_price) EditText etPetPrice;
    @BindView(R.id.et_pet_remark) EditText etPetRemark;
    @BindView(R.id.btn_sale) Button btnSale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pet_status);
        ButterKnife.bind(this);
    }

    @Override
    protected <T> void onUiThread(Result<T> result, int resultCode) { }

    @OnClick(R.id.btn_sale)
    void onClick() {
        if (isInfoOk()) {
            shortToast("clicked");
        }
    }

    private boolean isInfoOk() {
        return true;
    }

    public static void startActivityForResult(Context context) {
        Intent intent = new Intent(context, ChangePetStatusActivity.class);
        ((Activity) context).startActivityForResult(intent, CODE_CHANGE_PET_STATUS_ACTIVITY);
    }
}
