package com.group5.petstroe.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.group5.petstroe.R;
import com.group5.petstroe.apis.Result;
import com.group5.petstroe.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.group5.petstroe.utils.ActivityUtils.CODE_CREATE_PET_ACTIVITY;

public class CreatePetActivity extends BaseActivity {

    @BindView(R.id.iv_pet_image) ImageView ivPetImage;
    @BindView(R.id.et_pet_name) EditText etPetName;
    @BindView(R.id.et_pet_birthday) EditText etPetBirthday;
    @BindView(R.id.sp_pet_species) Spinner spSpecies;
    @BindView(R.id.et_pet_description) EditText etPetDescription;
    @BindView(R.id.btn_create) Button btnCreatePet;

    private static final String[] speciesArray = {"其他", "狗", "猫", "仓鼠", "兔", "恐龙"};
    private int species = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pet);
        ButterKnife.bind(this);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.item_species, speciesArray);
        spSpecies.setAdapter(arrayAdapter);
        spSpecies.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                species = i;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
    }

    @Override
    protected <T> void onUiThread(Result<T> result, int resultCode) {}

    @OnClick(R.id.btn_create)
    void onClick() {
        if (isInfoOk()) {
            shortToast("clicked");
        }
    }

    private boolean isInfoOk() {
        return true;
    }

    public static void startActivityForResult(Context context) {
        Intent intent = new Intent(context, CreatePetActivity.class);
        ((Activity) context).startActivityForResult(intent, CODE_CREATE_PET_ACTIVITY);
    }
}
